package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.util.List;

public class User {
	private String name;
	private String email;
	private String birthDate;
	private float weight;
	private int height;
	private int maxHeartRate;
	private int restHeartRate;
	private List<Session> sessionList;
	private List<Challenge> challengeList;
		
	
	// Constructor, getters and setters
	
	public User(String name, String email, String birthDate, float weight, int height, int maxHeartRate,
			int restHeartRate, List<Session> sessionList, List<Challenge> challengeList) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.maxHeartRate = maxHeartRate;
		this.restHeartRate = restHeartRate;
		this.sessionList = sessionList;
		this.challengeList = challengeList;
	}
	
	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaxHeartRate() {
		return maxHeartRate;
	}

	public void setMaxHeartRate(int maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}

	public int getRestHeartRate() {
		return restHeartRate;
	}

	public void setRestHeartRate(int restHeartRate) {
		this.restHeartRate = restHeartRate;
	}

	public List<Session> getSessionList() {
		return sessionList;
	}

	public void setSessionList(List<Session> sessionList) {
		this.sessionList = sessionList;
	}

	public List<Challenge> getChallengeList() {
		return challengeList;
	}

	public void setChallengeList(List<Challenge> challengeList) {
		this.challengeList = challengeList;
	}
	
	
	// Methods: (toString)
	
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", birthDate=" + birthDate + ", weight=" + weight
				+ ", height=" + height + ", maxHeartRate=" + maxHeartRate + ", restHeartRate=" + restHeartRate
				+ ", sessionList=" + sessionList + ", challengeList=" + challengeList + "]";
	}
	
	
	// Additional methods: 
	
	public void addSession(Session session) {
		
	}

	public void addChallenge(Challenge challenge) {
		
	}
	
	public boolean checkPassword(String password) {
		return true;
	}
}