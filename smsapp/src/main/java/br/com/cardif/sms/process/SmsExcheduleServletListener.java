package br.com.cardif.sms.process;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SmsExcheduleServletListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		
				
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
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

}
