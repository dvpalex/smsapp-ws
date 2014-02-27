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
@Table(name="COUNTRY")
public class Country implements Serializable{

		private static final long serialVersionUID = 3703773775531792742L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "COUNTRY_ID")
		private Long countryId;
		
		@Column(name = "NAME", length = 50, nullable = false)
		private String name;
		
		@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<CardifStatus> cardifStatus;
		
		@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<Template> templates;
		
		@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<BrokerStatus> brokerStatus;
							
		public Long getCountryId() {
			return countryId;
		}
		public void setCountryId(Long countryId) {
			this.countryId = countryId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<CardifStatus> getCardifStatus() {
			return cardifStatus;
		}
		public void setCardifStatus(List<CardifStatus> cardifStatus) {
			this.cardifStatus = cardifStatus;
		}
		public List<Template> getTemplates() {
			return templates;
		}
		public void setTemplates(List<Template> templates) {
			this.templates = templates;
		}
		public List<BrokerStatus> getBrokerStatus() {
			return brokerStatus;
		}
		public void setBrokerStatus(List<BrokerStatus> brokerStatus) {
			this.brokerStatus = brokerStatus;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((countryId == null) ? 0 : countryId.hashCode());
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
			Country other = (Country) obj;
			if (countryId == null) {
				if (other.countryId != null)
					return false;
			} else if (!countryId.equals(other.countryId))
				return false;
			return true;
		}
		
		
		
		
}
