package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;

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
    	ChallengeDTO dto = new ChallengeDTO();
		
		dto.setName(challenge.getName());
		dto.setStartDate(challenge.getStartDate());
		dto.setEndDate(challenge.getEndDate());
		dto.setTargetTime(challenge.getTargetTime());
		dto.setTargetDistance(challenge.getTargetDistance());
		dto.setSport(challenge.getSports());
				
		return dto;
    }

    public List<ChallengeDTO> challengeToDTO(List<Challenge> challenges) {
    	List<ChallengeDTO> dtos = new ArrayList<>();
		
		for (Challenge challenge : challenges) {
			dtos.add(this.challengeToDTO(challenge));
		}
		
		return dtos;
    }
}
