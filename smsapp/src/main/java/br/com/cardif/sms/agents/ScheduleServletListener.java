package br.com.cardif.sms.agents;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.service.jta.platform.internal.JOnASJtaPlatform;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.cardif.sms.model.Agent;

//import br.com.cardif.sms.model.Schedule;
//import br.com.cardif.sms.services.ScheduleService;
//import br.com.cardif.sms.services.TestService;


import java.lang.reflect.*;


public class ScheduleServletListener implements ServletContextListener  {
	
	
	private Scheduler scheduler;
	
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
	
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		try {
			init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected void init() throws ClassNotFoundException{
		
		
			
		try {
			
			scheduler = new StdSchedulerFactory().getScheduler();
			
			for(Agent obj : loadAgentFake()){
				
				//Class<Job> c = (Class<Job>) Class.forName("br.com.cardif.sms.agents." + obj.getName());
				JobDetail job = JobBuilder.newJob(SmsLoadFileAgent.class)
					.withIdentity(obj.getName(), "agents").build();
			
				Trigger trigger = TriggerBuilder
						  .newTrigger()
						  .withIdentity(obj.getName() +"Trigger", "agents")
						  .withSchedule(
						     CronScheduleBuilder.cronSchedule("0 0/1,17-21 * * ?"))
						  .build();
				
				scheduler.scheduleJob(job, trigger);
			}
				
			scheduler.start();
					
	 
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
	}
	
	
	private Set<Agent> loadAgentFake(){
		
		Set<Agent> agents = new HashSet<Agent>();
	
		/*
		
		Agent smsLoadAgent = new Agent();
		smsLoadAgent.setName("SmsLoadFileAgent");
		smsLoadAgent.setAgentId(1L);
		*/
		
		Agent smsSendAgent = new Agent();
		smsSendAgent.setName("smsSendAgent");
		smsSendAgent.setAgentId(2L);
		
		//agents.add(smsLoadAgent);
		agents.add(smsSendAgent);
		
		return agents;
		
	}
	
	
	
}
