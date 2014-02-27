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
@Table(name="CHANNEL")
public class Channel implements Serializable {

	private static final long serialVersionUID = -3107650115598563320L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CANNEL_ID")
	private Long channelId;
	
	@Column(name = "NAME", length = 25, nullable = false)
	private String name;
	
	@Column(name = "MSNSIZELIMIT",  nullable = false)
	private Integer msnSizeLimit;
	
	@OneToMany(mappedBy = "channel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Template> templates;
	
	//@ManyToMany(mappedBy="channels")
	//private List<Template> templates;
	
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMsnSizeLimit() {
		return msnSizeLimit;
	}
	public void setMsnSizeLimit(Integer msnSizeLimit) {
		this.msnSizeLimit = msnSizeLimit;
	}
	public List<Template> getTemplates() {
		return templates;
	}
	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((channelId == null) ? 0 : channelId.hashCode());
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
		Channel other = (Channel) obj;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		return true;
	}
	
	
	
	
}
