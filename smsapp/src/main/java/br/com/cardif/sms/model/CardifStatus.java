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
@Table(name="CARDIFSTATUS")
public class CardifStatus implements Serializable {
	
	private static final long serialVersionUID = -4220871947292538688L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CARDIFSTATUS_ID")
	private Long cardifStatusId;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", nullable = true)
	private Country country = new Country();
	
	@Column(name = "NAME", length = 25, nullable = false)	
	private String name;
	
	@OneToMany(mappedBy = "cardifStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Status> status;
		
	public Long getCardifStatusId() {
		return cardifStatusId;
	}
	public void setCardifStatusId(Long cardifStatusId) {
		this.cardifStatusId = cardifStatusId;
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
	public List<Status> getStatues() {
		return status;
	}
	public void setStatues(List<Status> statues) {
		this.status = statues;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardifStatusId == null) ? 0 : cardifStatusId.hashCode());
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
		CardifStatus other = (CardifStatus) obj;
		if (cardifStatusId == null) {
			if (other.cardifStatusId != null)
				return false;
		} else if (!cardifStatusId.equals(other.cardifStatusId))
			return false;
		return true;
	}
	
	
	
}
