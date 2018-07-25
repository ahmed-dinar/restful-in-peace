package rokomari.java.recruit.restfulinpeace.repository;

import org.springframework.data.repository.CrudRepository;

import rokomari.java.recruit.restfulinpeace.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	boolean existsByEmail(String email);

	User findByEmail(String email);

}