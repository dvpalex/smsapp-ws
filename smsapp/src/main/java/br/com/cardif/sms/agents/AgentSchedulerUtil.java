package br.com.cardif.sms.agents;

import static org.quartz.CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek;
import static org.quartz.DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Scheduler;
import org.reflections.Reflections;

import br.com.cardif.sms.model.Agent;

public class AgentSchedulerUtil {

	private  StdSchedulerFactory factory;
	
	public AgentSchedulerUtil(StdSchedulerFactory factory){
		this.factory = factory;
	}
	
	@SuppressWarnings("unchecked")
	public void scheduler(Set<Agent> agents ){
		
		JobDetail job;
		
		try {
			Scheduler scheduler = factory.getScheduler();
			scheduler.start();
		
		for(Agent obj : agents){
			
			@SuppressWarnings("unchecked")
			Class<? extends Job> c;
			try {
				c = getClassAgent(obj.getName());
			
			Trigger trigger = null;
			
			job = JobBuilder.newJob(c)
					.withIdentity(obj.getAgentId() + "AGENT", "agents").build();
			
			Set<Integer> lstDays = daysOfWeeks(obj);
			Integer[] daysOfWeek = new Integer[lstDays.size()];
			lstDays.toArray(daysOfWeek);
			
			if(obj.isFlagFrequencyOnce() && !obj.isFlagFrequencyEach()){
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(obj.getFlagFrequencyOnceValue());
				
				trigger = TriggerBuilder
						  .newTrigger()
						  .withIdentity(obj.getAgentId() + "TRIGGER", "agents")
						  .withSchedule(atHourAndMinuteOnGivenDaysOfWeek(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),daysOfWeek))	
						  .build();
				
			}else if(!obj.isFlagFrequencyOnce() && obj.isFlagFrequencyEach()){
				
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(obj.getAgentId() + "TRIGGER", "agents")
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

	
	@SuppressWarnings("unused")
	private String getClassName(String name){
		
		Reflections reflections = new Reflections("br.com.cardif.sms.agents");

		 Set<Class<? extends Job>> allClasses = 
		     reflections.getSubTypesOf(Job.class);
		
		 for(Class<? extends Job> cls : allClasses){
			 if(cls.getName().toUpperCase().contains(name.toUpperCase())){
				 return cls.getName();
			 }
		 }
		 return "";
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends Job> getClassAgent(String name) throws ClassNotFoundException{
		
		return (Class<? extends Job>) Class.forName(getClassName(name));
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
	
	
	public void execute(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, JobExecutionException{
		
		Class<? extends Job> cl =  getClassAgent(getClassName(name));
		Job obj = (Job)cl.newInstance();
		obj.execute(null);
	}
	
	
	public void disableAgent(long id) throws SchedulerException{
		removeAgent(id);
	}
	
	
	public Boolean removeAgent(long id) throws SchedulerException{
		return factory.getScheduler().deleteJob(new JobKey(getClassName(id + "AGENT")));
	}
	
	
}
