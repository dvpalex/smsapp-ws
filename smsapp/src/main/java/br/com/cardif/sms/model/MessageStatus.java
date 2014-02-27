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
@Table(name="MESSAGESTATUS")
public class MessageStatus implements Serializable 
{
	private static final long serialVersionUID = -8089388084378097026L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGESTATUS_ID")
	private Long messageStatusId;
	
	@ManyToOne
	@JoinColumn(name = "MESSAGE_ID", nullable = false)
	private Message message;
	
	@ManyToOne
	@JoinColumn(name = "STATUS_ID", nullable = true)
	private Status status;
	
	//@OneToMany(mappedBy = "messageStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private List<Event> events;
	
	@Temporal(TemporalType.DATE)
	@Column(name="STATUSDATE")
	private Date statusDate;
	
	public Long getMessageStatusId() {
		return messageStatusId;
	}
	public void setMessageStatusId(Long messageStatusId) {
		this.messageStatusId = messageStatusId;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messageStatusId == null) ? 0 : messageStatusId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageStatus other = (MessageStatus) obj;
		if (messageStatusId == null) {
			if (other.messageStatusId != null)
				return false;
		} else if (!messageStatusId.equals(other.messageStatusId))
			return false;
		return true;
	}
}