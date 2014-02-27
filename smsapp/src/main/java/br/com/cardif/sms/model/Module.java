package br.com.cardif.sms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MODULE")
public class Module
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MODULE_ID")
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExecutionLog> logs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExecutionLog> getLogs() {
		return logs;
	}

	public void setLogs(List<ExecutionLog> logs) {
		this.logs = logs;
	}
}