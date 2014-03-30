package br.com.cardif.sms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="AGENT")
public class Agent implements Serializable {

	private static final long serialVersionUID = -1389434314993717947L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AGENT_ID")
	private Long agentId;

	@Column(name = "ENABLED")
	private Boolean enabled;
	
	@Column(name = "NAME", nullable = false, length = 255 )
	private String name;
	
	@Column(name = "OBJECTIVE", nullable = false , length = 255)
	private String objective;
	
	@Column(name = "FLAG_FREQUENCY_ONE", nullable = true)
	private boolean flagFrequencyOnce;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FLAG_FREQUENCY_ONE_VALUE", nullable = true)
	private Date flagFrequencyOnceValue;
	
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_EXECUTION", nullable = true)
	private Date lastExecution;
	
	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public Date getFlagFrequencyOnceValue() {
		return flagFrequencyOnceValue;
	}

	public void setFlagFrequencyOnceValue(Date flagFrequencyOnceValue) {
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
		if(flagFrequencyEachBegin > 23 && flagFrequencyEachBegin < 0){
			throw new NumberFormatException("Integer out of range[0..23]");
		}else{	
			this.flagFrequencyEachBegin = flagFrequencyEachBegin;
		}
	}

	public Integer getFlagFrequencyEachEnd() {
		return flagFrequencyEachEnd;
	}

	public void setFlagFrequencyEachEnd(Integer flagFrequencyEachEnd) {
		if(flagFrequencyEachBegin > 23 && flagFrequencyEachBegin < 0){
			throw new NumberFormatException("Integer out of range[0..23]");	
		}else{
			this.flagFrequencyEachEnd = flagFrequencyEachEnd;
		}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
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
		Agent other = (Agent) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		return true;
	}

	public String getClassName(){
		String[] ret = name.split("\\."); 
		if(ret.length == 0){
			return name;
		}else{
			return ret[ret.length -1];
		}
	}
	
	
	
}
