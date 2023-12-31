package es.deusto.ingenieria.sd.strava.server.data.dto;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class UserAssembler {
    private static UserAssembler instance;

    private UserAssembler() {}

    public static UserAssembler getInstance() {
        if (instance == null) {
            instance = new UserAssembler();
        }
        return instance;
    }

    public UserDTO userToDTO(User user) {
    	UserDTO dto = new UserDTO();
		
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setBirthDate(user.getBirthDate());
		dto.setWeight(user.getWeight());
		dto.setHeight(user.getHeight());
		dto.setMaxHeartRate(user.getMaxHeartRate());
		dto.setRestHeartRate(user.getRestHeartRate());
				
		return dto;
    }
}
