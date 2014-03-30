package br.com.cardif.sms.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.quartz.Job;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import br.com.cardif.sms.agents.AgentDataMock;
import br.com.cardif.sms.agents.AgentSchedulerUtil;
import br.com.cardif.sms.model.Agent;


@ManagedBean(name="agent")
@SessionScoped
@Component
public class AgentBean {

	private String name = "br.com.cardif.sms.agents.SmsLoadFileAgent" ;//= "br.com.cardif.sms.SmsLoadFileAgent";
	private Long agentId = 2359L;
	private String className = "SmsLoadFileAgent";
	
	public String getName() {
		return name ;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public void execute(ActionEvent evt){
		AgentSchedulerUtil util = new AgentSchedulerUtil(null);
		try {
			System.out.println("Executado agent: " + name);
			util.execute(name);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void disableAgent(ActionEvent evt){
		
		System.out.println("Desativado agent: " + className);
		
		StdSchedulerFactory stdSchedulerFactory = (StdSchedulerFactory) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
		
		AgentSchedulerUtil util = new AgentSchedulerUtil(stdSchedulerFactory);
		try {
			util.disableAgent(className);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeAgent(ActionEvent evt){
		
		StdSchedulerFactory stdSchedulerFactory = (StdSchedulerFactory) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
		
		AgentSchedulerUtil util = new AgentSchedulerUtil(stdSchedulerFactory);
		try {
			if(util.removeAgent(className)){
				System.out.println("removido com sucesso agent: " + className);
			}else{
				
				System.out.println("não foi possivel remover  agent: " + className);
			}
				
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void exchedule(ActionEvent evt) throws ClassNotFoundException, SchedulerException{
		
		StdSchedulerFactory stdSchedulerFactory = (StdSchedulerFactory) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
		
		AgentSchedulerUtil util = new AgentSchedulerUtil(stdSchedulerFactory);
		
		AgentDataMock agent = new AgentDataMock(false);
		
		util.addAgent((Agent)agent.agentsFake().toArray()[0]);
		
	}
	
	
}
