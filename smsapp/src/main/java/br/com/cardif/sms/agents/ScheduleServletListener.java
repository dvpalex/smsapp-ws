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

			scheduler();
			ServletContext servletContext = context.getServletContext();
			servletContext.setAttribute(QUARTZ_FACTORY_KEY, factory);
	}
	
	
	@SuppressWarnings("unchecked")
	public void scheduler(){
		
		JobDetail job;
		factory = new StdSchedulerFactory();
		
		
		try {
			scheduler = factory.getScheduler();
			scheduler.start();
		
		
		AgentDataMock agent = new AgentDataMock();
		
		for(Agent obj : agent.agentsFake()){
			
			@SuppressWarnings("unchecked")
			Class<? extends Job> c;
			try {
				c = (Class<? extends Job>) Class.forName(getClassName(obj.getName()));
			
			Trigger trigger = null;
			
			job = JobBuilder.newJob(c)
					.withIdentity(obj.getName(), "agents").build();
			
			Set<Integer> lstDays = daysOfWeeks(obj);
			Integer[] daysOfWeek = new Integer[lstDays.size()];
			lstDays.toArray(daysOfWeek);
			
			if(obj.isFlagFrequencyOnce() && !obj.isFlagFrequencyEach()){
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(obj.getFlagFrequencyOnceValue());
				
				trigger = TriggerBuilder
						  .newTrigger()
						  .withIdentity(obj.getName()  +"-Trigger", "agents")
						  .withSchedule(atHourAndMinuteOnGivenDaysOfWeek(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),daysOfWeek))	
						  .build();
				
			}else if(!obj.isFlagFrequencyOnce() && obj.isFlagFrequencyEach()){
				
				trigger = TriggerBuilder.newTrigger()
						.withSchedule(
						dailyTimeIntervalSchedule()
						.startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(obj.getFlagFrequencyEachBegin(), 0, 0))
						.endingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(obj.getFlagFrequencyEachEnd(), 0, 0))
						.onDaysOfTheWeek(daysOfWeeks(obj))
						.withInterval(obj.getFlagFrequencyEachValue(), IntervalUnit.MINUTE))
						.startNow()
						.build();	
			}
			
			
			
			try {
			
				scheduler.scheduleJob(job, trigger);
			
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		} catch (SchedulerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (Exception e2){
			e2.printStackTrace();
		}
	}
	
public Set<Integer> daysOfWeeks(Agent agent){
		
		Set<Integer>  days = new HashSet<Integer>();
		
		if (agent.isFlagWeekDaySunday()){
			days.add(1);
		}
		if(agent.isFlagWeekDayMonday()){
			days.add(2);
		}
		if(agent.isFlagWeekDayTuesday()){
			days.add(3);
		}
		if(agent.isFlagWeekDayWednesday()){
			days.add(4);
		}
		if(agent.isFlagWeekDayThursday()){
			days.add(5);
		}
		if(agent.isFlagWeekDayFriday()){
			days.add(6);
		}if(agent.isFlagWeekDaySaturday()){
			days.add(7);
		}
		return days;
	}
	
	@SuppressWarnings("unused")
	private String getClassName(String name){
		
		Reflections reflections = new Reflections("br.com.cardif.sms.agents");

		 Set<Class<? extends Job>> allClasses = 
		     reflections.getSubTypesOf(Job.class);
		
		 for(Class<? extends Job> cls : allClasses){
			 if(cls.getName().toUpperCase().endsWith(name.toUpperCase())){
				 return cls.getName();
			 }
		 }
		 return "";
	}
	
		
}
