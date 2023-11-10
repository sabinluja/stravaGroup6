package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.io.Serializable;

public class SessionDTO implements Serializable {
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
		
    private String title;
    private String sport;
    private float distance;
    private String startDate;
    private long startTime;
    private int duration;
    
    
    // Getters and setters
    
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSport() {
		return sport;
	}
	
	public void setSport(String sport) {
		this.sport = sport;
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

	
	// Methods: (toString)
	
	public String toString() {
		return "SessionDTO [title=" + title + ", sport=" + sport + ", distance=" + distance + ", startDate=" + startDate
				+ ", startTime=" + startTime + ", duration=" + duration + "]";
	}
}
