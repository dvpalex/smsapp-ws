package br.com.cardif.sms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.print.attribute.standard.SheetCollate;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import br.com.cardif.sms.agents.AgentSchedulerUtil;
import br.com.cardif.sms.model.Agent;
import br.com.cardif.sms.model.QuartzJob;

@ManagedBean(name="scheduler")
@SessionScoped
@Component
public class SchedulerBean implements Serializable {
	
	private static final long serialVersionUID = -2989665184264836888L;
	private Scheduler scheduler;
	private List<QuartzJob> list; 
	
	public SchedulerBean() {
		
	}
	public List<QuartzJob> getList(){	

		list = new ArrayList<QuartzJob>() ;
			
		StdSchedulerFactory stdSchedulerFactory = (StdSchedulerFactory) FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap().get(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
			
			
		AgentSchedulerUtil util = new AgentSchedulerUtil(stdSchedulerFactory);
			
		return util.getList("SmsLoadFileAgent");
	
	}
		
	
}
