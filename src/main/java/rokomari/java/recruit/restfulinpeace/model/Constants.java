package rokomari.java.recruit.restfulinpeace.model;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "adad";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "jwt_token";
    public static final String AUTHORITIES_KEY = "scopes";
    
    public static final String API_KEY_HEADER = "token";
    public static final String API_SIGNING_KEY = "adad";
    public static final String API_SUBJECT = "scopes";
    public static final String API_ISSUER = "noone";
    
    
    @SuppressWarnings("unused")
	private static final String SAMPLE_API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzY29wZXMiLCJpc3MiOiJub29uZSIsImlhdCI6MTUzMjM2ODExMX0.F55Q8bV3SFn0fhAczzHesvLFOW2Erpy6D1TzmGGPpwY";
    @SuppressWarnings("unused")
	private static final String SAMPLE_JWT = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZGQuZGluYXJAZ21haWwuY29tIiwic2NvcGVzIjoiUk9MRV9hZG1pbiIsImlhdCI6MTUzMjM2OTA0MCwiZXhwIjoxNTMyMzg3MDQwfQ.6eSLRR4rQJuuESKxYMVsUD1Qr2zO4qSvpPOk8YDE9nQ";
	
}

