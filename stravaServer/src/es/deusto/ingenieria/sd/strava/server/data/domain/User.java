package es.deusto.ingenieria.sd.strava.server.data.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String email;
	private String password;
	private String birthDate;
	private float weight;
	private int height;
	private int maxHeartRate;
	private int restHeartRate;
	private List<Session> sessionList = new ArrayList<Session>();
	private List<Challenge> challengeList = new ArrayList<Challenge>();
	private List<Challenge> acceptedChallengeList = new ArrayList<Challenge>();
		
	
	// Constructor, getters and setters
	
	public User(String name, String email, String password, String birthDate, float weight, int height,
			int maxHeartRate, int restHeartRate, List<Session> sessionList, List<Challenge> challengeList, List<Challenge> acceptedChallengeList) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.maxHeartRate = maxHeartRate;
		this.restHeartRate = restHeartRate;
		this.sessionList = sessionList;
		this.challengeList = challengeList;
		this.acceptedChallengeList = acceptedChallengeList;
	}
	
	public User(String name, String email, String birthDate, float weight, int height,
			int maxHeartRate, int restHeartRate) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.maxHeartRate = maxHeartRate;
		this.restHeartRate = restHeartRate;
	}
	
	public User(String name, String email, String birthDate) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
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
	
	public List<Challenge> getAcceptedChallengeList() {
		return acceptedChallengeList;
	}

	public void setAcceptedChallengeList(List<Challenge> acceptedChallengeList) {
		this.acceptedChallengeList = acceptedChallengeList;
	}
	
	
	// Methods: (toString -- equals)

	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", birthDate=" + birthDate
				+ ", weight=" + weight + ", height=" + height + ", maxHeartRate=" + maxHeartRate + ", restHeartRate="
				+ restHeartRate + ", sessionList=" + sessionList + ", challengeList=" + challengeList
				+ ", acceptedChallengeList=" + acceptedChallengeList + "]";
	}
	
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((User)obj).email);
		}
		return false;
	}
	
	
	// Additional methods: 

	public void addSession(Session session) {
		if (session != null && !this.sessionList.contains(session)) {
			this.sessionList.add(session);
		}
	}

	public void addChallenge(Challenge challenge) {
		if (challenge != null && !this.challengeList.contains(challenge)) {
			this.challengeList.add(challenge);
		}
	}
	
	public void addAcceptedChallenge(Challenge challenge) {
		if (challenge != null && !this.acceptedChallengeList.contains(challenge)) {
			this.acceptedChallengeList.add(challenge);
		}
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}