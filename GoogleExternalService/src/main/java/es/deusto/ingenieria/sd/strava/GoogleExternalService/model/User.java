package es.deusto.ingenieria.sd.strava.GoogleExternalService.model;

import jakarta.persistence.*;

@Table(name="userTable")
@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String password;
    
    @Column(unique = true)
    private String email;
      
    public User () {}
    
    public User (String email, String password) {
    	this.email = email;
    	this.password = password;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
