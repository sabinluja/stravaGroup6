package es.deusto.ingenieria.sd.strava.server.gateways;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.*;


public class GoogleGateway2 implements IProviderGateway{

	private static String BASE_URL;
	private static Gson gson = new Gson();
	
	public GoogleGateway2 (String connectionURL) {
		BASE_URL=connectionURL;
	}
	
	public boolean register(String email, String password) {
		System.out.format("Registerin user: %suser/register/%s/%s ...", BASE_URL, email, password);
		HttpClient client = HttpClient.newHttpClient();
		
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(BASE_URL + "user/register/" + email + "/" + password))
	            .build();
	    System.out.println("\n" + request.uri());
	    try {
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        System.out.println("Response: " + response.statusCode());
	        if (response.statusCode() == 200) {                     
	            return Boolean.parseBoolean(response.body());
	        } else {
	        	System.out.println("ERROR");
	            System.out.format("- Error: %d", response.statusCode());
	        }

	    } catch (Exception e) {
	        System.out.format("- ERROR Validating user credentials: %svalidate/password/%s/%s [%s]", BASE_URL, email, password, e.getMessage());
	    }

	    // En caso de error o excepción, asumimos que las credenciales no son válidas.
	    return false;
	}
	
	
	public boolean validatePassword(String email, String password) {		
		
		System.out.format("Validating user credentials: %svalidate/password/%s/%s ...", BASE_URL, email, password);
	    HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(BASE_URL + "validate/password/" + email + "/" + password))
	            .build();

	    try {
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	        if (response.statusCode() == 200) {                     
	            return Boolean.parseBoolean(response.body());
	        } else {
	            System.out.format("- Error: %d", response.statusCode());
	        }

	    } catch (Exception e) {
	        System.out.format("- ERROR Validating user credentials: %svalidate/password/%s/%s [%s]", BASE_URL, email, password, e.getMessage());
	    }

	    // En caso de error o excepción, asumimos que las credenciales no son válidas.
	    return false;
	}
	
	public boolean validateEmail(String email) {		
		
		System.out.format("Validating user email: %svalidate/email/%s ...", BASE_URL, email);
	    HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(BASE_URL + "validate/email/" + email))
	            .build();

	    try {
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	        if (response.statusCode() == 200) {                     
	            return Boolean.parseBoolean(response.body());
	        } else {
	            System.out.format("- Error: %d", response.statusCode());
	        }

	    } catch (Exception e) {
	        System.out.format("- ERROR Validating user email: %svalidate/password/%s [%s]", BASE_URL, email, e.getMessage());
	    }

	    // En caso de error o excepción, asumimos que las credenciales no son válidas.
	    return false;
	}
	
}
