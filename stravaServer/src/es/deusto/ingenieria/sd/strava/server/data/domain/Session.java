package es.deusto.ingenieria.sd.strava.server.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessionTable")
public class Session {
	@Id
	private String title;
	private String sports;	
	private float distance;
	private String startDate;
	private long startTime;
	private int duration;
	@ManyToOne
    @JoinColumn(name = "id")
	private User user;
	
	
	// Constructor, getters and setters
	
	public Session(String title, String sports, float distance, String startDate, long startTime, int duration) {
		super();
		this.title = title;
		this.sports = sports;
		this.distance = distance;
		this.startDate = startDate;
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public Session() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	// Methods: (toString)
	
	public String toString() {
		return "Session [title=" + title + ", sports=" + sports + ", distance=" + distance + ", startDate=" + startDate
				+ ", startTime=" + startTime + ", duration=" + duration + "]";
	}	
	
	
	// Additional methods: 
}