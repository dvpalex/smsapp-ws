package br.com.cardif.sms.agents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;

import static org.quartz.CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.reflections.Reflections;

import br.com.cardif.sms.model.Agent;
import br.com.cardif.sms.util.DateUtils;


public class ScheduleServletListener implements ServletContextListener  {
	
	
	public static Scheduler scheduler;
	
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
	
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
			try {
				exchedule();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	protected void exchedule() throws SchedulerException{
	
			JobDetail job;
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			
			for(Agent obj : loadAgentFake()){
				
				try{
				
					@SuppressWarnings("unchecked")
					Class<? extends Job> c =  (Class<? extends Job>) Class.forName(getClassName(obj.getName()));
					
					job = JobBuilder.newJob(c)
						.usingJobData("ids", obj.getAgentId().toString())	
						.withIdentity(obj.getName() + obj.hashCode() , "agents").build();
						
					
					List<Integer> lstDays = getDaysOfWeeks(obj);
					Integer[] days = new Integer[lstDays.size()];
					lstDays.toArray(days);
				
					
					if(obj.isFlagFrequencyOnce() && !obj.isFlagFrequencyEach()){
				
						Calendar cal = Calendar.getInstance();
						cal.setTime(obj.getFlagFrequencyOnceValue());
						
						System.out.println("Agent : " + obj.getName() +  " - programado para executar todos os dias as: " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + " Days of Week :  " + days.length);
						
						Trigger trigger = TriggerBuilder
								  .newTrigger()
								  .withIdentity(obj.getName() + obj.hashCode() +"-Trigger", "agents")
								  .withSchedule(atHourAndMinuteOnGivenDaysOfWeek(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),days))	
								  .build();
						
						scheduler.scheduleJob(job, trigger);
						
					}else if(!obj.isFlagFrequencyOnce() && obj.isFlagFrequencyEach()){
						
						job.getJobDataMap().put("id" ,obj.getAgentId());
						
						Date begin = DateUtils.getDateWithTimeOnly(9, 0, 0);
						Date end = DateUtils.getDateWithTimeOnly(18, 0, 0);
						
						int count = 0;
						
						for(Date dt : cronExpression(begin, end, obj.getFlagFrequencyEachValue())){
							
							count++;
							
							job = JobBuilder.newJob(c)
									.withIdentity(obj.getName() + obj.hashCode()   + "-" + count++, "agents").build();
							
							String cronExp = getCronExpression(dt,getDaysOfWeeksExp(obj), obj.getFlagFrequencyEachValue() < 60 ? true:false);
							Trigger trigger = TriggerBuilder
									  .newTrigger()
									  .withIdentity(obj.getName() + obj.hashCode() +"Trigger-" + count , "agents")
									  .withSchedule(
									  CronScheduleBuilder.cronSchedule(cronExp)).build();
						
							scheduler.scheduleJob(job, trigger);
							System.out.println("Agent : " + obj.getName() +  " - Cron Expression: "  + cronExp);
						}

						
						
						
					
					}
				}catch(ClassNotFoundException ex1 ){
					System.out.println("Classe Agent : " + obj.getName() + " Não localizada");	
				}catch(SchedulerException ex2){
					System.out.println("Classe Agent : " + obj.getName() + " Schedule Invalid");
				}
			}
		
	}
	
	
	public Set<Agent> loadAgentFake(){
		
		Set<Agent> agents = new HashSet<Agent>();
		
		//Frequencia recorrente
		Agent smsLoadAgent = new Agent();
		smsLoadAgent.setName("SmsLoadFileAgent");
		smsLoadAgent.setAgentId(2359L);
		smsLoadAgent.setFlagFrequencyEach(true);
		smsLoadAgent.setFlagFrequencyEachBegin(9);
		smsLoadAgent.setFlagFrequencyEachEnd(23);
		smsLoadAgent.setFlagFrequencyEachValue(60); //60
		smsLoadAgent.setFlagWeekDayFriday(true);
		smsLoadAgent.setFlagWeekDaySaturday(true);
		smsLoadAgent.setFlagWeekDaySunday(true);
		smsLoadAgent.setFlagWeekDayMonday(true);
		smsLoadAgent.setFlagWeekDayTuesday(true);
		smsLoadAgent.setFlagWeekDayWednesday(true);
		
		Agent smsLoadAgent1 = new Agent();
		smsLoadAgent1.setName("SmsLoadFileAgent");
		smsLoadAgent1.setAgentId(2358L);
		smsLoadAgent1.setFlagFrequencyEach(true);
		smsLoadAgent1.setFlagFrequencyEachBegin(9);
		smsLoadAgent1.setFlagFrequencyEachEnd(23);
		smsLoadAgent1.setFlagFrequencyEachValue(70); //60
		smsLoadAgent1.setFlagWeekDayFriday(true);
		smsLoadAgent1.setFlagWeekDaySaturday(true);
		smsLoadAgent1.setFlagWeekDaySunday(true);
		smsLoadAgent1.setFlagWeekDayMonday(true);
		smsLoadAgent1.setFlagWeekDayTuesday(true);
		smsLoadAgent1.setFlagWeekDayWednesday(true);
	
		//Frequencia recorrente
		Agent smsCheckStatusAgent = new Agent();
		smsCheckStatusAgent.setName("SmsCheckStatusAgent");
		smsCheckStatusAgent.setAgentId(2400L);
		smsCheckStatusAgent.setFlagFrequencyEach(true);
		smsCheckStatusAgent.setFlagFrequencyEachBegin(9);
		smsCheckStatusAgent.setFlagFrequencyEachEnd(23);
		smsCheckStatusAgent.setFlagFrequencyEachValue(1);
		smsCheckStatusAgent.setFlagWeekDayFriday(true);
		smsCheckStatusAgent.setFlagWeekDaySaturday(true);
		smsCheckStatusAgent.setFlagWeekDaySunday(true);
		smsCheckStatusAgent.setFlagWeekDayMonday(true);
		smsCheckStatusAgent.setFlagWeekDayTuesday(true);
		smsCheckStatusAgent.setFlagWeekDayWednesday(true);
		
		//Frequencia recorrente
		Agent smsSendAgent = new Agent();
		smsSendAgent.setName("SmsCheckStatusAgent");
		smsSendAgent.setAgentId(2401L);
		smsSendAgent.setFlagFrequencyEach(true);
		smsSendAgent.setFlagFrequencyEachBegin(9);
		smsSendAgent.setFlagFrequencyEachEnd(23);
		smsSendAgent.setFlagFrequencyEachValue(120);
		smsSendAgent.setFlagWeekDayFriday(true);
		smsSendAgent.setFlagWeekDaySaturday(true);
		smsSendAgent.setFlagWeekDaySunday(true);
		smsSendAgent.setFlagWeekDayMonday(true);
		smsSendAgent.setFlagWeekDayTuesday(true);
		smsSendAgent.setFlagWeekDayWednesday(true);
		
		
		
		
		Date dt = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 4);
		
		Agent smsSendAgent1 = new Agent();
		smsSendAgent1.setName("SmsSendAgent");
		smsSendAgent1.setAgentId(2407L);
		smsSendAgent1.setFlagFrequencyOnce(true);
		smsSendAgent1.setFlagFrequencyOnceValue(cal.getTime());
		smsSendAgent1.setFlagWeekDaySunday(true);
		smsSendAgent1.setFlagWeekDayMonday(true);
		smsSendAgent1.setFlagWeekDayTuesday(true);
		smsSendAgent1.setFlagWeekDayWednesday(true);
		
		
		/*
		Agent smsClassNotFoundExceptionAgent = new Agent();
		smsClassNotFoundExceptionAgent.setName("SmsClassNotFoundExceptionAgent");
		smsClassNotFoundExceptionAgent.setAgentId(4L);
		
		*/
		
		
		agents.add(smsLoadAgent);
		agents.add(smsSendAgent);
		agents.add(smsCheckStatusAgent);
		agents.add(smsLoadAgent1);
		agents.add(smsSendAgent1);
		return agents;
		
	}
	
	
	public List<Integer> getDaysOfWeeks(Agent agent){
		
		List<Integer>  days = new ArrayList<Integer>();
		
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

public String getDaysOfWeeksExp(Agent agent){
		
		List<Integer>  days = new ArrayList<Integer>();
		
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
		
		return days.toString().replace("[", "").replace("]", "")
        .replace(", ", ",");
		
	}

	
	
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
	
 
	private String getCronExpression(Date dt, String daysOfWeek, Boolean min){
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dt);
			String ret = "";
			
			if(min){
				ret = String.format("0 0/%s 0 * %s ?", calendar.get(Calendar.MINUTE), daysOfWeek);
			}else{
				ret = String.format("0 %s %s * %s ?", calendar.get(Calendar.MINUTE), calendar.get(Calendar.HOUR_OF_DAY), daysOfWeek);
			}
			
			return ret;
	}
	
	public List<Date> cronExpression( Date begin , Date end,int minutes){
		
		long mBegin = DateUtils.intervalMilliseconds(begin);
		long mEnd = DateUtils.intervalMilliseconds(end);
		long mMinutes = minutes * 60 * 1000;
		
		long qt = (mEnd-mBegin)/mMinutes;
		long pos = mBegin;
		List<Date> lst = new ArrayList<Date>();
		
		
		if(minutes < 60){
			lst.add(DateUtils.getDateWithTimeOnly(0, minutes, 0));
		}else{
		
			for(int i = 0; i < qt; i++){
				
				if((pos + mMinutes) < mEnd){
					pos += mMinutes;
					lst.add(DateUtils.intervalDate(pos));
				}
			}
		
		}
		System.out.println(lst);
		
		return lst;
			
		}
	
	
}
