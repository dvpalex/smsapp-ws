package br.com.cardif.sms.agents;

import static org.quartz.CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek;
import static org.quartz.DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.Scheduler;
import org.reflections.Reflections;

import br.com.cardif.sms.model.Agent;
import br.com.cardif.sms.model.QuartzJob;

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
					.withIdentity(obj.getClassName().toUpperCase() , "agentsj").build();
			
			Set<Integer> lstDays = daysOfWeeks(obj);
			Integer[] daysOfWeek = new Integer[lstDays.size()];
			lstDays.toArray(daysOfWeek);
			
			if(obj.isFlagFrequencyOnce() && !obj.isFlagFrequencyEach()){
				
				trigger = excheduleFraquencyOnce(obj, daysOfWeek);
				
			}else if(!obj.isFlagFrequencyOnce() && obj.isFlagFrequencyEach()){
				
				trigger = excheduleFrequencyInterval(obj, lstDays);	
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

	/**
	 * @param obj
	 * @param lstDays
	 * @return
	 */
	private Trigger excheduleFrequencyInterval(Agent obj, Set<Integer> lstDays) {
		Trigger trigger;
		trigger = TriggerBuilder.newTrigger()
				.withIdentity(obj.getAgentId() + "T", "agentst")
				.withSchedule(
				dailyTimeIntervalSchedule()
				.startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(obj.getFlagFrequencyEachBegin(), 0, 0))
				.endingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(obj.getFlagFrequencyEachEnd(), 0, 0))
				.onDaysOfTheWeek(lstDays)
				.withInterval(obj.getFlagFrequencyEachValue(), IntervalUnit.MINUTE))
				.startNow()
				.build();
		return trigger;
	}

	/**
	 * @param obj
	 * @param daysOfWeek
	 * @return
	 */
	private Trigger excheduleFraquencyOnce(Agent obj, Integer[] daysOfWeek) {
		Trigger trigger;
		Calendar cal = Calendar.getInstance();
		cal.setTime(obj.getFlagFrequencyOnceValue());
		
		trigger = TriggerBuilder
				  .newTrigger()
				  .withIdentity(obj.getAgentId() + "T", "agentst")
				  .withSchedule(atHourAndMinuteOnGivenDaysOfWeek(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),daysOfWeek))	
				  .build();
		return trigger;
	}

	
	public List<QuartzJob> getList(String agentName){	
		
		List<QuartzJob>  list = new ArrayList<QuartzJob>() ;
		
		try {
			
			if(factory != null){
			
				Scheduler scheduler = factory.getScheduler();
			
			
				 for (String groupName : scheduler.getJobGroupNames()) {
					 
						for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher
							.jobGroupEquals(groupName))) {
				 
							if(jobKey.getName().equalsIgnoreCase(agentName)){
							
								String jobName = jobKey.getName();
								String jobGroup = jobKey.getGroup();
					 
								@SuppressWarnings("unchecked")
								List<Trigger> triggers = (List<Trigger>) scheduler
									.getTriggersOfJob(jobKey);
								Date nextFireTime = triggers.get(0).getNextFireTime();
								list.add(new QuartzJob(jobName, jobGroup, nextFireTime));
							}
						}
				 
					  }
			}
			 
		} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return list;
		
	}
	
	
	@SuppressWarnings("unused")
	private String getClassName(String name){
		
		Reflections reflections = new Reflections("br.com.cardif.sms.agents");

		 Set<Class<? extends Job>> allClasses = 
		     reflections.getSubTypesOf(Job.class);
		
		 for(Class<? extends Job> cls : allClasses){
			 if(cls.getName().equalsIgnoreCase(name)){
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
	
	
	public void disableAgent(String classname) throws SchedulerException{
		removeAgent(classname.toUpperCase());
	}
	
	
	public Boolean removeAgent(String classname) throws SchedulerException{
		JobKey jobKey = new JobKey(classname.toUpperCase(), "agentsj");
		Boolean ret = factory.getScheduler().deleteJob(jobKey); 
		return ret;
	}
	
	
	public void addAgent(Agent agent) throws ClassNotFoundException, SchedulerException{
		Trigger trigger = null;
		
		Class<? extends Job> c;
		c = getClassAgent(agent.getName());
		
		JobDetail job = JobBuilder.newJob(c)
				.withIdentity(agent.getClassName().toUpperCase() , "agentsj").build();
		
		Set<Integer> lstDays = daysOfWeeks(agent);
		Integer[] daysOfWeek = new Integer[lstDays.size()];
		lstDays.toArray(daysOfWeek);
		
		if(agent.isFlagFrequencyOnce() && !agent.isFlagFrequencyEach()){
			
			trigger = excheduleFraquencyOnce(agent, daysOfWeek);
			
		}else if(!agent.isFlagFrequencyOnce() && agent.isFlagFrequencyEach()){
			
			trigger = excheduleFrequencyInterval(agent, lstDays);	
		}
		
		Scheduler scheduler = factory.getScheduler();
		scheduler.scheduleJob(job, trigger);
		
	}
	
}
