package es.deusto.ingenieria.sd.auctions.server.data.domain;

public class Challenge {	
	private String name;
	private String startDate;
	private String endDate;
	private long targetTime;
	private float targetDistance;
	private String sports;
	
	
	// Constructor, getters and setters
	
	public Challenge(String name, String startDate, String endDate, long targetTime, float targetDistance,
			String sports) {
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

	
	// Methods: (toString)
	
	public String toString() {
		return "Challenge [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", targetTime="
				+ targetTime + ", targetDistance=" + targetDistance + ", sports=" + sports + "]";
	}
	
	
	// Additional methods: 
}