package br.com.cardif.sms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EVENTARGUMENT")
public class EventArgument implements Serializable{

	private static final long serialVersionUID = -1249002732995958802L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EVENTARGUMENT_ID")
	private Long eventArgumentId;
	
	@ManyToOne
	@JoinColumn(name = "EVENT_ID", nullable = true)
	private Event event;
	
	@Column(name = "ARGUMENT_KEY", length = 25, nullable = false)
	private String key;
	
	@Column(name = "VALUE", length = 50, nullable = false)
	private String value;
		
	public Long getEventArgumentId() {
		return eventArgumentId;
	}
	public void setEventArgumentId(Long eventArgumentId) {
		this.eventArgumentId = eventArgumentId;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventArgumentId == null) ? 0 : eventArgumentId.hashCode());
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
		EventArgument other = (EventArgument) obj;
		if (eventArgumentId == null) {
			if (other.eventArgumentId != null)
				return false;
		} else if (!eventArgumentId.equals(other.eventArgumentId))
			return false;
		return true;
	}
	
	
	
}
