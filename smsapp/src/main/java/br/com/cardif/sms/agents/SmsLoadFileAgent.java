package br.com.cardif.sms.agents;

import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import br.com.cardif.sms.model.Schedule;
import br.com.cardif.sms.services.ScheduleService;


@Configurable
@Component
public class SmsLoadFileAgent implements Job {
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int i = 0;
		System.out.println("Rodada: " + Calendar.getInstance().getTime() );
	}

	
}
