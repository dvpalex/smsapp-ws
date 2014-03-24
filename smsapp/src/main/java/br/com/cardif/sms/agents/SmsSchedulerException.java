package br.com.cardif.sms.agents;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SmsSchedulerException  implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("SmsSchedulerException  executed at: " + Calendar.getInstance().getTime() );
	}
}
