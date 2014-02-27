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
import javax.persistence.Transient;

@Entity
@Table(name="STATUS")
public class Status implements Serializable {

	private static final long serialVersionUID = -6816504688608045433L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATUS_ID")
	private Long statusId;
	
	@ManyToOne
	@JoinColumn(name = "CARDIFSTATUS_ID", nullable = true)
	private CardifStatus cardifStatus = new CardifStatus();
	
	@ManyToOne
	@JoinColumn(name = "BROKERSTATUS_ID", nullable = true)
	private BrokerStatus brokerStatus = new BrokerStatus();

	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MessageStatus> messageStatus;
	
	@Transient
	private String message;
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public CardifStatus getCardifStatus() {
		return cardifStatus;
	}
	public void setCardifStatus(CardifStatus cardifStatus) {
		this.cardifStatus = cardifStatus;
	}
	public BrokerStatus getBrokerStatus() {
		return brokerStatus;
	}
	public void setBrokerStatus(BrokerStatus brokerStatus) {
		this.brokerStatus = brokerStatus;
	}
	
	
	public List<MessageStatus> getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(List<MessageStatus> messageStatus) {
		this.messageStatus = messageStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((statusId == null) ? 0 : statusId.hashCode());
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
		Status other = (Status) obj;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		return true;
	}	
}