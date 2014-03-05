package br.com.cardif.sms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EXECUTION_LOG")
public class ExecutionLog implements Serializable
{
	private static final long serialVersionUID = -1535554793481563812L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOG_ID")
	private Long id;
	
	@Column(name = "LEVEL", length=20, nullable = false)
	private String level;
	
	@Column(name = "LOCATION", length=255, nullable = false)
	private String location;
	
	@Column(name = "THROWABLE", length=1000, nullable = true)
	private String throwable;
	
	@Column(name = "STACKTRACE", length=1000, nullable = true)
	private String stacktrace;
	
	@Column(name = "MESSAGE", length=255, nullable = false)
	private String message;
	
	@Column(name = "USER_LOGIN", length=255, nullable = true)
	private String userLogin;
	
	@ManyToOne
	@JoinColumn(name = "MODULE_ID", nullable = false)
	private Module module = new Module();
	
	@ManyToOne
	@JoinColumn(name = "OPERATION_ID")
	private Operation operation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE", nullable = false)
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getThrowable() {
		return throwable;
	}

	public void setThrowable(String throwable) {
		this.throwable = throwable;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}


	
	
}