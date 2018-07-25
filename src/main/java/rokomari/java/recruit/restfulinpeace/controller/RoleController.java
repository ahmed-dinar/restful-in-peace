package rokomari.java.recruit.restfulinpeace.controller;

import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rokomari.java.recruit.restfulinpeace.model.Role;
import rokomari.java.recruit.restfulinpeace.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> getRoles(@RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("user_id");
		Long user_id = null;

		//no user_id provided, so just return all role list
		if(id == null || id.size() == 0) {
			List<Role> roles = roleService.getAll();
			return new ResponseEntity<Object>(roles, HttpStatus.OK);
		}
		
		try {
			user_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			return ResponseEntity.notFound().build();
		}
		
		Set<Role> roles = roleService.getUserRoles(user_id);
		return ResponseEntity.ok().body(roles);
	}
	
	
	
	
}
