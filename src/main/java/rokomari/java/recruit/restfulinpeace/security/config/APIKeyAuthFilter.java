package rokomari.java.recruit.restfulinpeace.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.MalformedJwtException;

import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


import static rokomari.java.recruit.restfulinpeace.model.Constants.API_KEY_HEADER;


public class APIKeyAuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String apiKey = request.getHeader(API_KEY_HEADER);
		boolean validApi = false;
		
		System.out.println("api key--");
		System.out.println(apiKey);
		
		if( apiKey != null ) {
			try {
				validApi = new ApiKeyProvider().validateKey(apiKey);
			}
			catch (ClaimJwtException e) {
				logger.error("an error occured ClaimJwtException api key", e);
			} 
			catch (MalformedJwtException e) {
				logger.error("an error occured MalformedJwtException api key", e);
			} 
			catch (SignatureException e) {
				logger.error("an error occured SignatureException api key", e);
			} 
			catch (UnsupportedJwtException e) {
				logger.error("an error occured UnsupportedJwtException api key", e);
			} 
			catch (Exception e) {
				logger.error("an error EXCEPTION", e);
			}
		}
		
		if( !validApi ) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{ \"status\":\"401\", \"error\": \"Unauthorized\", \"message\": \"Valid API Key required\" }");
			return;
		}
		
		filterChain.doFilter(request, response);
	}

}
