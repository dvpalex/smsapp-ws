package br.com.cardif.sms.model;

import java.io.Serializable;
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
@Table(name="EVENTCATEGORY")
public class EventCategory implements Serializable{
	
	
	private static final long serialVersionUID = 7745681455468330066L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EVENTCATEGORY_ID")
	private Long eventCategoryId;
	
	@Column(name = "NAME", length = 100, nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "eventCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Template> templastes;

	public Long getEventCategoryId() {
		return eventCategoryId;
	}
	public void setEventCategoryId(Long eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Template> getTemplastes() {
		return templastes;
	}
	public void setTemplastes(List<Template> templastes) {
		this.templastes = templastes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventCategoryId == null) ? 0 : eventCategoryId.hashCode());
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
		EventCategory other = (EventCategory) obj;
		if (eventCategoryId == null) {
			if (other.eventCategoryId != null)
				return false;
		} else if (!eventCategoryId.equals(other.eventCategoryId))
			return false;
		return true;
	}	
}