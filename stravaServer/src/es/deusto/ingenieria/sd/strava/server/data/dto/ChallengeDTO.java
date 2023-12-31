package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//This class implements DTO pattern
public class ChallengeDTO implements Serializable {
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
	
	private String name;
    private String startDate;
    private String endDate;
    private long targetTime;
    private float targetDistance;
    private String sport;
    
    
    // Getters and setters
	
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
	
	public String getSport() {
		return sport;
	}
	
	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	// Methods: (toString)
	
	public String toString() {
		return "Challenge [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", targetTime="
				+ targetTime + ", targetDistance=" + targetDistance + ", sport=" + sport + "]";
	}
}