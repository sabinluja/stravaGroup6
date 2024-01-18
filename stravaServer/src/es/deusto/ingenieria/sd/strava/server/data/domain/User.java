package es.deusto.ingenieria.sd.strava.server.data.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {
	private String name;
	@Id
	private String email;
	private String password;
	private String birthDate;
	private float weight;
	private int height;
	private int maxHeartRate;
	private int restHeartRate;
	private String provider;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Session> sessionList = new HashSet<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Challenge> challengeList = new HashSet<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Challenge> acceptedChallengeList = new HashSet<>();
	
	// Constructor, getters and setters
	
	public User(String name, String email, String birthDate, float weight, int height,
			int maxHeartRate, int restHeartRate, String provider) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.weight = weight;
		this.height = height;
		this.maxHeartRate = maxHeartRate;
		this.restHeartRate = restHeartRate;
		this.provider = provider;
	}
	
	public User(String name, String email, String birthDate, String provider) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.provider = provider;
	}

	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Set<Session> getSessionList() {
		return sessionList;
	}

	public void setSessionList(Set<Session> sessionList) {
		this.sessionList = sessionList;
	}

	public Set<Challenge> getChallengeList() {
		return challengeList;
	}

	public void setChallengeList(Set<Challenge> challengeList) {
		this.challengeList = challengeList;
	}
	
	public Set<Challenge> getAcceptedChallengeList() {
		return acceptedChallengeList;
	}

	public void setAcceptedChallengeList(Set<Challenge> acceptedChallengeList) {
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
			session.setUser(this);
			this.sessionList.add(session);
		}
	}

	public void addChallenge(Challenge challenge) {
		if (challenge != null && !this.challengeList.contains(challenge)) {
			challenge.addUser(this);
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