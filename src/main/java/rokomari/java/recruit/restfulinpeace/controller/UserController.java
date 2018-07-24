package rokomari.java.recruit.restfulinpeace.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import rokomari.java.recruit.restfulinpeace.lib.Messages;
import rokomari.java.recruit.restfulinpeace.model.User;
import rokomari.java.recruit.restfulinpeace.service.UserService;


@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
    Messages messages;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
		
		//should replace with custom validatsor annotation
		User usrExists = userService.findbyEmail(user.getEmail());
		
		if( usrExists != null && usrExists.getId() != null ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages.json("400","Email already exixts"));
		}
		
		userService.save(user);
		return ResponseEntity.ok().body("{ \"status\": \"success\" }");
	}
	
	@RequestMapping(value="/user/all", method = RequestMethod.GET)
	public List<User> findAll() {
		return (List<User>) userService.getAll();
	}
	
	
/*	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<User>> getUserById(@PathVariable(value="id") Long id) {
		Optional<User> user = userService.findOne(id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(user);
	}
	*/
}
