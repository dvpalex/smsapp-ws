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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TEMPLATE")
public class Template implements Serializable {

	private static final long serialVersionUID = 3042892017019228712L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEMPLATE_ID")
	private Long templateId;
	
	@ManyToOne
	@JoinColumn(name = "EVENTCATEGORY_ID", nullable = false)
	private EventCategory eventCategory;
	
	@ManyToOne
	@JoinColumn(name = "PARTNER_ID", nullable = false)
	private Partner partner;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "CHANNEL_ID", nullable = false)
	private Channel channel;
	
	@Column(name = "TEMPLATE", length = 1000, nullable = false)
	private String template;
	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinTable(name = "TEMPLATECHANNEL",  joinColumns = { 
	//		@JoinColumn(name = "TEMPLATE_ID", nullable = false, updatable = false) }, 
	//		inverseJoinColumns = { @JoinColumn(name = "CHANNEL_ID", 
	//				nullable = false, updatable = false) })
	//private List<Channel> channels;
	
	@OneToMany(mappedBy = "template", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Event> events;
	
	
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public EventCategory getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	//public List<Channel> getChannels() {
	//	return channels;
	//}
	//public void setChannels(List<Channel> channels) {
	//	this.channels = channels;
	//}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((template == null) ? 0 : template.hashCode());
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
		Template other = (Template) obj;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		return true;
	}
}