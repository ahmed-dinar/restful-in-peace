package rokomari.java.recruit.restfulinpeace.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import rokomari.java.recruit.restfulinpeace.lib.Helper;
import rokomari.java.recruit.restfulinpeace.lib.Messages;
import rokomari.java.recruit.restfulinpeace.model.Doctor;
import rokomari.java.recruit.restfulinpeace.model.Role;
import rokomari.java.recruit.restfulinpeace.model.User;
import rokomari.java.recruit.restfulinpeace.service.RoleService;
import rokomari.java.recruit.restfulinpeace.service.UserService;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
    Messages messages;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> getRoles(@RequestHeader HttpHeaders headers) {
		
		Long userId = Helper.hasValidId(headers.get("user_id"));
		
		//no user_id provided, so just return all role list
		if(userId == -1) {
			List<Role> roles = roleService.getAll();
			return new ResponseEntity<Object>(roles, HttpStatus.OK);
		}

		Optional<User> userObj = userService.findOne(userId);
		
		if(userObj == null || !userObj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		User user = userObj.get();

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode roleNode = mapper.createObjectNode();
		roleNode.put("first_name", user.getFirst_name());
		roleNode.put("email", user.getEmail());
		roleNode.put("mobile", user.getMobile());
		roleNode.putArray("roles").addAll((ArrayNode)mapper.valueToTree(user.getRoles()));

		return ResponseEntity.ok().body(roleNode);
	}
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/insert/role/new", method = RequestMethod.POST)
	public ResponseEntity<Object> addRole(@Valid @RequestBody Role role, @RequestHeader HttpHeaders headers) {
		roleService.save(role);
		return ResponseEntity.ok().body("{ \"status\": \"success\" }");
	}
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/delete/roles", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteRole(@RequestHeader HttpHeaders headers) {
		
		Long roleId = Helper.hasValidId(headers.get("role_id"));
		
		if(roleId == -1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		roleService.delete(roleId);
		return ResponseEntity.ok().body("{ \"status\": \"deleted\" }");
	}
	
	
	
	
	
	
	
	
	
}
