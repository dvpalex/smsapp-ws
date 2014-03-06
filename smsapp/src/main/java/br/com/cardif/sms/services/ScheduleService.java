package br.com.cardif.sms.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.cardif.sms.model.Schedule;

@Configurable
@Service
public class ScheduleService {

	@PersistenceContext
	protected  EntityManager em;
	
	
	public List<Schedule> listEnabled()
	{
		  TypedQuery<Schedule> query = em.createQuery("from Schedule r", Schedule.class);
		  return query.getResultList();
	}
	
}
