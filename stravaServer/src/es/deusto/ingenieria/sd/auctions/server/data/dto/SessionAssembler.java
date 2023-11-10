package es.deusto.ingenieria.sd.auctions.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;

public class SessionAssembler {
    private static SessionAssembler instance;

    private SessionAssembler() {}

    public static SessionAssembler getInstance() {
        if (instance == null) {
            instance = new SessionAssembler();
        }
        return instance;
    }

    public SessionDTO sessionToDTO(Session session) {
    	SessionDTO dto = new SessionDTO();
		
		dto.setTitle(session.getTitle());
		dto.setSport(session.getSports());
		dto.setDistance(session.getDistance());
		dto.setStartDate(session.getStartDate());
		dto.setStartTime(session.getStartTime());
		dto.setDuration(session.getDuration());
				
		return dto;
    }

    public List<SessionDTO> sessionToDTO(List<Session> sessions) {
    	List<SessionDTO> dtos = new ArrayList<>();
		
		for (Session session : sessions) {
			dtos.add(this.sessionToDTO(session));
		}
		
		return dtos;
    }
}
