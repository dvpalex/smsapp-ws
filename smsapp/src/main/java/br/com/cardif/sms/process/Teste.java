package br.com.cardif.sms.process;

//import static org.quartz.JobBuilder.*;
//import static org.quartz.TriggerBuilder.*;
//import static org.quartz.SimpleScheduleBuilder.*;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class Teste {

	
	public Teste(){
		
		try{
		
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			
			System.out.println("Exchedule executado");
			
			scheduler.shutdown();
			
		}catch (SchedulerException se){
			se.printStackTrace();
		}

	}
	
	
}
