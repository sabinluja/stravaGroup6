package es.deusto.ingenieria.sd.strava.FacebookExternalService;

import java.util.HashMap;
import java.util.StringTokenizer;

public class FacebookService {

    private static final String DELIMITER = "#";
    private static HashMap<String, String> userCredentials = new HashMap<>();

    public boolean processRequest(String request) {
        StringTokenizer tokenizer = new StringTokenizer(request, DELIMITER);
        String action = tokenizer.nextToken();

        switch (action) {
            case "register_mandatory":
                return registerUser(tokenizer.nextToken(), tokenizer.nextToken());
            case "validate_password":
                return validatePassword(tokenizer.nextToken(), tokenizer.nextToken());
            case "validate_email":
                return validateEmail(tokenizer.nextToken());
            default:
                return false;
        }
    }

    private boolean registerUser(String email, String password) {
        if (!userCredentials.containsKey(email)) {
            userCredentials.put(email, password);
            return true;
        }
        return false;
    }

    private boolean validatePassword(String email, String password) {
        if (userCredentials.containsKey(email)) {
            String storedPassword = userCredentials.get(email);
            if (password.equals(storedPassword)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateEmail(String email) {
        if (userCredentials.containsKey(email)) {
            return false;
        }
        return true;
    }
}
