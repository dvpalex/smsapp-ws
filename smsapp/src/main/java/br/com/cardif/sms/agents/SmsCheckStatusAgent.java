package br.com.cardif.sms.agents;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SmsCheckStatusAgent implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//-" + context.getJobDetail().getJobDataMap().getString("ids") + "
		System.out.println("SmsCheckStatus executed at: " + Calendar.getInstance().getTime() );
	
		
	}

}
