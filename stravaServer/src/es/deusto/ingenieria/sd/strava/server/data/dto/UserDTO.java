package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.io.Serializable;

//This class implements DTO pattern
public class UserDTO implements Serializable {	
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
	
	private String email;
    private String name;
    private String birthDate;
    private float weight;
    private int height;
    private int maxHeartRate;
    private int restHeartRate;
    
    
    // Getters and setters
    
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	
	// Methods: (toString)

	public String toString() {
		return "User [email=" + email + ", name=" + name + ", birthDate=" + birthDate + ", weight=" + weight
				+ ", height=" + height + ", maxHeartRate=" + maxHeartRate + ", restHeartRate=" + restHeartRate + "]";
	}	
}