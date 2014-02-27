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
@Table(name="BROKERSTATUS")
public class BrokerStatus implements Serializable {
	
	private static final long serialVersionUID = -3640638627655044288L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BROKERSTATUS_ID")
	private Long brokerStatusId;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	private Country country = new Country();
	
	@Column(name = "NAME", length = 25, nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "brokerStatus", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Status> status;

	public Long getBrokerStatusId() {
		return brokerStatusId;
	}
	public void setBrokerStatusId(Long brokerStatusId) {
		this.brokerStatusId = brokerStatusId;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Status> getStatus() {
		return status;
	}
	public void setStatues(List<Status> status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((brokerStatusId == null) ? 0 : brokerStatusId.hashCode());
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
		BrokerStatus other = (BrokerStatus) obj;
		if (brokerStatusId == null) {
			if (other.brokerStatusId != null)
				return false;
		} else if (!brokerStatusId.equals(other.brokerStatusId))
			return false;
		return true;
	}
	
	
	
}
