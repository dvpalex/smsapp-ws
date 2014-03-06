package br.com.cardif.sms.process;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SmsLoadFileJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int i = 0;
		System.out.println("Rodada: " + i++);
	}

	
}
