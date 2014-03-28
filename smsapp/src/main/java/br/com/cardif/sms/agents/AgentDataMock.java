package br.com.cardif.sms.agents;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.cardif.sms.model.Agent;




public class AgentDataMock {

	private Set<Agent> agents = new HashSet<Agent>();
	
	public AgentDataMock(){
	
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
			smsLoadAgent.setFlagWeekDayThursday(true);	
			
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
			smsLoadAgent1.setFlagWeekDayThursday(true);	
			
	
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
			smsCheckStatusAgent.setFlagWeekDayThursday(true);	
			
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
			smsSendAgent.setFlagWeekDayThursday(true);
			
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
			smsSendAgent1.setFlagWeekDayThursday(true);
			
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

	}

	
	public Set<Agent> agentsFake(){
		return agents;
	}
}
