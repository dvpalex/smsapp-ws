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
import javax.persistence.Transient;

@Entity
@Table(name="CONNECTOR")
public class Connector implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONNECTOR_ID")
	private Long connectorId;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "OBJECTIVE", nullable = false)
	private String objective;
	
	@Column(name = "FLAG_FREQUENCY_ONE", nullable = true)
	private boolean flagFrequencyOnce;
	
	@Column(name = "FLAG_FREQUENCY_ONE_VALUE", nullable = true)
	private Integer flagFrequencyOnceValue;
	
	@Column(name = "FLAG_FREQUENCY_EACH", nullable = true)
	private boolean flagFrequencyEach;
	
	@Column(name = "FLAG_FREQUENCY_EACH_VALUE", nullable = true)
	private Integer flagFrequencyEachValue;
	
	@Column(name = "FLAG_FREQUENCY_EACH_VALUE_BEGIN", nullable = true)
	private Integer flagFrequencyEachBegin;
	
	@Column(name = "FLAG_FREQUENCY_EACH_VALUE_END", nullable = true)
	private Integer flagFrequencyEachEnd;
	
	@Column(name = "FLAG_WEEK_DAY_SUNDAY", nullable = true)
	private boolean flagWeekDaySunday;
	
	@Column(name = "FLAG_WEEK_DAY_MONDAY", nullable = true)
	private boolean flagWeekDayMonday;
	
	@Column(name = "FLAG_WEEK_DAY_TUESDAY", nullable = true)
	private boolean flagWeekDayTuesday;
	
	@Column(name = "FLAG_WEEK_DAY_WEDNESDAY", nullable = true)
	private boolean flagWeekDayWednesday;
	
	@Column(name = "FLAG_WEEK_DAY_THURSDAY", nullable = true)
	private boolean flagWeekDayThursday;
	
	@Column(name = "FLAG_WEEK_DAY_FRIDAY", nullable = true)
	private boolean flagWeekDayFriday;
	
	@Column(name = "FLAG_WEEK_DAY_SATURDAY", nullable = true)
	private boolean flagWeekDaySaturday;
	
	@ManyToOne
	@JoinColumn(name = "DISPATCHER_ID", nullable = false)
	private Dispatcher dispatcher;

	@Transient
	private String frequency;
	
	public Long getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(Long connectorId) {
		this.connectorId = connectorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public boolean isFlagFrequencyOnce() {
		return flagFrequencyOnce;
	}

	public void setFlagFrequencyOnce(boolean flagFrequencyOnce) {
		this.flagFrequencyOnce = flagFrequencyOnce;
	}

	public Integer getFlagFrequencyOnceValue() {
		return flagFrequencyOnceValue;
	}

	public void setFlagFrequencyOnceValue(Integer flagFrequencyOnceValue) {
		this.flagFrequencyOnceValue = flagFrequencyOnceValue;
	}

	public boolean isFlagFrequencyEach() {
		return flagFrequencyEach;
	}

	public void setFlagFrequencyEach(boolean flagFrequencyEach) {
		this.flagFrequencyEach = flagFrequencyEach;
	}

	public Integer getFlagFrequencyEachValue() {
		return flagFrequencyEachValue;
	}

	public void setFlagFrequencyEachValue(Integer flagFrequencyEachValue) {
		this.flagFrequencyEachValue = flagFrequencyEachValue;
	}

	public Integer getFlagFrequencyEachBegin() {
		return flagFrequencyEachBegin;
	}

	public void setFlagFrequencyEachBegin(Integer flagFrequencyEachBegin) {
		this.flagFrequencyEachBegin = flagFrequencyEachBegin;
	}

	public Integer getFlagFrequencyEachEnd() {
		return flagFrequencyEachEnd;
	}

	public void setFlagFrequencyEachEnd(Integer flagFrequencyEachEnd) {
		this.flagFrequencyEachEnd = flagFrequencyEachEnd;
	}

	public boolean isFlagWeekDaySunday() {
		return flagWeekDaySunday;
	}

	public void setFlagWeekDaySunday(boolean flagWeekDaySunday) {
		this.flagWeekDaySunday = flagWeekDaySunday;
	}

	public boolean isFlagWeekDayMonday() {
		return flagWeekDayMonday;
	}

	public void setFlagWeekDayMonday(boolean flagWeekDayMonday) {
		this.flagWeekDayMonday = flagWeekDayMonday;
	}

	public boolean isFlagWeekDayTuesday() {
		return flagWeekDayTuesday;
	}

	public void setFlagWeekDayTuesday(boolean flagWeekDayTuesday) {
		this.flagWeekDayTuesday = flagWeekDayTuesday;
	}

	public boolean isFlagWeekDayWednesday() {
		return flagWeekDayWednesday;
	}

	public void setFlagWeekDayWednesday(boolean flagWeekDayWednesday) {
		this.flagWeekDayWednesday = flagWeekDayWednesday;
	}

	public boolean isFlagWeekDayThursday() {
		return flagWeekDayThursday;
	}

	public void setFlagWeekDayThursday(boolean flagWeekDayThursday) {
		this.flagWeekDayThursday = flagWeekDayThursday;
	}

	public boolean isFlagWeekDayFriday() {
		return flagWeekDayFriday;
	}

	public void setFlagWeekDayFriday(boolean flagWeekDayFriday) {
		this.flagWeekDayFriday = flagWeekDayFriday;
	}

	public boolean isFlagWeekDaySaturday() {
		return flagWeekDaySaturday;
	}

	public void setFlagWeekDaySaturday(boolean flagWeekDaySaturday) {
		this.flagWeekDaySaturday = flagWeekDaySaturday;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public String getFrequency() {
		
		if(flagFrequencyOnce){
			frequency = "U";
		}else{
				frequency = "V";
		}
		return frequency;
	}

	public void setFrequency(String frequency) 
	{
		this.frequency = frequency;
	}
}