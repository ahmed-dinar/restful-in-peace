package rokomari.java.recruit.restfulinpeace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rokomari.java.recruit.restfulinpeace.lib.Messages;
import rokomari.java.recruit.restfulinpeace.model.Login;
import rokomari.java.recruit.restfulinpeace.service.AppointmentService;
import rokomari.java.recruit.restfulinpeace.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
    Messages messages;

	@Autowired
	private AppointmentService appointmentService;
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/appointments", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestHeader HttpHeaders headers) throws AuthenticationException {
		
		List<String> id = headers.get("doctor_id");
		Long doctor_id = null;
		
		if(id == null || id.size() == 0) {
			return ResponseEntity.ok().body(appointmentService.getAll());
		}
		
		try {
			doctor_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		
		return ResponseEntity.ok().body(appointmentService.getAll());
		
	}
	
}
