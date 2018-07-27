package rokomari.java.recruit.restfulinpeace.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import rokomari.java.recruit.restfulinpeace.lib.Messages;
import rokomari.java.recruit.restfulinpeace.model.Role;
import rokomari.java.recruit.restfulinpeace.model.User;
import rokomari.java.recruit.restfulinpeace.service.RoleService;
import rokomari.java.recruit.restfulinpeace.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	Messages messages;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {

		// should replace with custom annotation?
		User usrExists = userService.findbyEmail(user.getEmail());
		if (usrExists != null && usrExists.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages.json("400", "Email already exixts"));
		}

		// is this bad? find every time on every registration? hmmm?
		Role role = roleService.createIfNotExists("USER");
		Set<Role> roles = new HashSet<>(Arrays.asList(role));

		user.setRoles(roles);

		userService.save(user);

		JsonNode results = new ObjectMapper().createObjectNode();
		((ObjectNode) results).put("first_name", user.getFirst_name());
		((ObjectNode) results).put("last_name", user.getLast_name());
		((ObjectNode) results).put("email", user.getEmail());
		((ObjectNode) results).put("mobile", user.getMobile());
		((ObjectNode) results).put("status", "success");

		return new ResponseEntity<Object>(results, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public List<User> findAll() {
		return (List<User>) userService.getAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "id") Long id) {
		Optional<User> user = userService.findOne(id);

		if (user == null || !user.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(user);
	}
}
