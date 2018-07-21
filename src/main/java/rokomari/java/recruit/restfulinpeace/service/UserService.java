package rokomari.java.recruit.restfulinpeace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.repository.UserRepository;
import rokomari.java.recruit.restfulinpeace.model.User;


@Service
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAll(){
		return (List<User>) userRepository.findAll();
	}
	
	
	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}
	
}
