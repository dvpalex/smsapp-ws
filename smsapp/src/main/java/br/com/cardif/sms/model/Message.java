package br.com.cardif.sms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGE")
public class Message implements Serializable
{	
	private static final long serialVersionUID = 5071543880000697022L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_ID")
	private Long messageId;
	
	@Column(name = "DESTINATION", length = 250, nullable = false)
	private String destination;
	
	@Column(name = "TEXT", length = 2000, nullable = false)
	private String text;
	
	@Column(name = "SCHEDULED",  nullable = false)
	private Date scheduled;
	
	@ManyToOne
	@JoinColumn(name = "EVENT_ID", nullable = false)
	private Event event;
	
	@OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MessageStatus> messageStatus;
	
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getDestination() {
		return destination;
	}
	public void setTo(String destination) {
		this.destination = destination;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getScheduled() {
		return scheduled;
	}
	public void setScheduled(Date scheduled) {
		this.scheduled = scheduled;
	}
	public List<MessageStatus> getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(List<MessageStatus> messageStatus) {
		this.messageStatus = messageStatus;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messageId == null) ? 0 : messageId.hashCode());
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
		Message other = (Message) obj;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		return true;
	}
}