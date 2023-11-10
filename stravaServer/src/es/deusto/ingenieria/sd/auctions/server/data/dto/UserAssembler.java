package es.deusto.ingenieria.sd.auctions.server.data.dto;

import es.deusto.ingenieria.sd.auctions.server.data.domain.User;

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
        // Conversion logic from User to UserDTO
        // Implement conversion here
    	
    	return null;
    }
}
