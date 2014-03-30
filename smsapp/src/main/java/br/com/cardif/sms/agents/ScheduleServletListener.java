package br.com.cardif.sms.agents;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Job;
import org.quartz.JobBuilder;

import static org.quartz.CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek;

import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.reflections.Reflections;

import br.com.cardif.sms.model.Agent;
import static org.quartz.DailyTimeIntervalScheduleBuilder.*;

public class ScheduleServletListener implements ServletContextListener  {
	
	
	private static Scheduler scheduler;
	public static final String QUARTZ_FACTORY_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";
	public StdSchedulerFactory factory;
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
		try {
			scheduler.shutdown(true);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
			AgentDataMock agent = new AgentDataMock(false);
			scheduler(agent.agentsFake());
			ServletContext servletContext = context.getServletContext();
			servletContext.setAttribute(QUARTZ_FACTORY_KEY, factory);
	}
	

	public void scheduler(Set<Agent> agents){
		factory = new StdSchedulerFactory();
		AgentSchedulerUtil sUtil = new AgentSchedulerUtil(factory);
		sUtil.scheduler(agents);
	}
		
}
