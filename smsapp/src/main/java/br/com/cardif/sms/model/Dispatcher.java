package br.com.cardif.sms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="DISPATCHER")
public class Dispatcher implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISPATCHER_ID")
	private Long dispatcherId;
	
	@Column(name = "NAME", length = 25, nullable = false)
	private String name;
	
	@Column(name = "OBJECTIVE", length = 100, nullable = false)
	private String objective;
	
	//@OneToMany(mappedBy = "dispatcher", fetch = FetchType.LAZY)
	//@Cascade(value = { org.hibernate.annotations.CascadeType.MERGE} ) 
	//private List<Agent> agents;

	public Long getDispatcherId() {
		return dispatcherId;
	}

	public void setDispatcherId(Long dispatcherId) {
		this.dispatcherId = dispatcherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	/*
	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	*/

}