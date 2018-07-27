package rokomari.java.recruit.restfulinpeace.security.config;

import static rokomari.java.recruit.restfulinpeace.model.Constants.API_SIGNING_KEY;
import static rokomari.java.recruit.restfulinpeace.model.Constants.API_SUBJECT;
import static rokomari.java.recruit.restfulinpeace.model.Constants.API_ISSUER;

import java.io.Serializable;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ApiKeyProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiKeyProvider() {
	}

	public String generateKey() {

		return Jwts.builder().setSubject(API_SUBJECT).setIssuer(API_ISSUER)
				.signWith(SignatureAlgorithm.HS256, API_SIGNING_KEY).setIssuedAt(new Date(System.currentTimeMillis()))
				.compact();
	}

	public boolean validateKey(String key) {

		Claims claims = Jwts.parser().setSigningKey(API_SIGNING_KEY).parseClaimsJws(key).getBody();

		System.out.println("CLAIMS--");
		System.out.println(claims);

		return claims.getSubject().equals(API_SUBJECT) && claims.getIssuer().equals(API_ISSUER);
	}

}
