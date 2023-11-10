package es.deusto.ingenieria.sd.auctions.server.data.dto;

import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;

public class ChallengeAssembler {
    private static ChallengeAssembler instance;

    private ChallengeAssembler() {}

    public static ChallengeAssembler getInstance() {
        if (instance == null) {
            instance = new ChallengeAssembler();
        }
        return instance;
    }

    public ChallengeDTO challengeToDTO(Challenge challenge) {
        // Conversion logic from Challenge to ChallengeDTO
        // Implement conversion here
    	
    	return null;
    }

    public List<ChallengeDTO> challengeToDTO(List<Challenge> challenges) {
        // Conversion logic from List<Challenge> to List<ChallengeDTO>
        // Implement conversion here
    	
    	return null;
    }
}
