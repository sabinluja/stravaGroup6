package es.deusto.ingenieria.sd.strava.server.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "challengeTable")
public class Challenge {
	@Id
	private String name;
	private String startDate;
	private String endDate;
	private long targetTime;
	private float targetDistance;
	private String sports;
	@ManyToOne
    @JoinColumn(name = "id")
	private User user;
	
	
	// Constructor, getters and setters
	
	public Challenge(String name, String startDate, String endDate, long targetTime, float targetDistance, String sports) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetTime = targetTime;
		this.targetDistance = targetDistance;
		this.sports = sports;
	}
	
	public Challenge() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(long targetTime) {
		this.targetTime = targetTime;
	}

	public float getTargetDistance() {
		return targetDistance;
	}

	public void setTargetDistance(float targetDistance) {
		this.targetDistance = targetDistance;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	// Methods: (toString)
	
	public String toString() {
		return "Challenge [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", targetTime="
				+ targetTime + ", targetDistance=" + targetDistance + ", sports=" + sports + "]";
	}
	
	
	// Additional methods: 
}