package rokomari.java.recruit.restfulinpeace.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import rokomari.java.recruit.restfulinpeace.model.User;
import rokomari.java.recruit.restfulinpeace.service.UserService;


@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public User registerUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<User> findAll() {
		return (List<User>) userService.getAll();
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<User>> getUserById(@PathVariable(value="id") Long id) {
		Optional<User> user = userService.findOne(id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(user);
	}
	
}
