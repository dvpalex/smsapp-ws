package br.com.cardif.sms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import br.com.cardif.sms.model.QuartzJob;

@ManagedBean(name="scheduler")
@SessionScoped
@Component
public class SchedulerBean implements Serializable {
	
	private static final long serialVersionUID = -2989665184264836888L;
	private Scheduler scheduler;
	private List<QuartzJob> list = new ArrayList<QuartzJob>() ;
	
	public SchedulerBean() {
		
	}
	public List<QuartzJob> getList(){	
		
		try {
			
		
			System.out.println(FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap().size());
			
			
			StdSchedulerFactory stdSchedulerFactory = (StdSchedulerFactory) FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap().get(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
			
			for(String vlr : FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().keySet()){
				
				System.out.println(vlr);
			}
			
			if(stdSchedulerFactory != null){
			
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
				 
							list.add(new QuartzJob(jobName, jobGroup, nextFireTime));
						}
				 
					  }
			}
			 
		} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return list;
		
	}

		
	
}
