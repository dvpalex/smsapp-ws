package br.com.cardif.sms.process;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import br.com.cardif.sms.model.Schedule;
import br.com.cardif.sms.services.ScheduleService;


@Configurable
@Component("SmsScheduleServletListener")
public class SmsScheduleServletListener  {

	//implements ServletContextListener
	
	/*
	@Autowired
	protected ScheduleService scheduleService; //= new ScheduleService();
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
		
				
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		
		
		List<Schedule> list = scheduleService.listEnabled(); 
		
		System.out.println("Load Schedules");
		
		for(Schedule schedule : list){
			
		System.out.println("Schedule: " + schedule.getScheduleId());
		}
		
		JobDetail job = JobBuilder.newJob(SmsLoadFileJob.class)
				.withIdentity("SmsLoadFile", "load").build();
	 
			try {
	 
				Trigger trigger = TriggerBuilder
				  .newTrigger()
				  .withIdentity("anyTriggerName", "group1")
				  .withSchedule(
				     CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
				  .build();
	 
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
	 
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
	}
	*/
}
