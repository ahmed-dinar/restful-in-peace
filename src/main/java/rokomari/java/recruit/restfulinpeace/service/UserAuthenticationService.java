package rokomari.java.recruit.restfulinpeace.service;

import java.util.Optional;

import rokomari.java.recruit.restfulinpeace.model.User;

public interface UserAuthenticationService {

	
	Optional<String> login(String email, String password);
	
	Optional<User> findByToken(String token);

}
