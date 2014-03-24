package br.com.cardif.sms.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.cardif.sms.model.QuartzJob;

@ManagedBean(name="schedulerBean")
@SessionScoped
@Component
public class SchedulerBean implements Serializable {
	
	private static final long serialVersionUID = -2989665184264836888L;
	private Scheduler scheduler;
	private List<QuartzJob> quartzJobList = new ArrayList<QuartzJob>();
	
	public SchedulerBean() throws SchedulerException{
		
		
		
		//FacesContext facesContext = FacesContext.getCurrentInstance();
			
		//System.out.println("Bean iniciado - Scheduler : " + facesContext.getExternalContext());
		
		/*
		while(servletContext.getAttributeNames().hasMoreElements()){
		
			System.out.println(servletContext.getAttributeNames().nextElement());
		}
				
		
		ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
/
		 StdSchedulerFactory stdSchedulerFactory = (StdSchedulerFactory) servletContext
					.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
		 
			scheduler = stdSchedulerFactory.getScheduler();
			
			
			System.out.println("Grupos: " + scheduler.getJobGroupNames().size());
			
			 for (String groupName : scheduler.getJobGroupNames()) {
				 
					// get jobkey
					for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher
						.jobGroupEquals(groupName))) {
			 
						String jobName = jobKey.getName();
						String jobGroup = jobKey.getGroup();
			 
						// get job's trigger
						@SuppressWarnings("unchecked")
						List<Trigger> triggers = (List<Trigger>) scheduler
							.getTriggersOfJob(jobKey);
						Date nextFireTime = triggers.get(0).getNextFireTime();
			 
						quartzJobList.add(new QuartzJob(jobName, jobGroup, nextFireTime));
			 
					}
			 
				  }*/
		 
	}
	
	
	
		public void fireNow(String jobName, String jobGroup)
			throws SchedulerException {
	 
			JobKey jobKey = new JobKey(jobName, jobGroup);
			scheduler.triggerJob(jobKey);
	 
		}
	 
		public List<QuartzJob> getQuartzJobList() {
	 
			return quartzJobList;
		}
	
}
