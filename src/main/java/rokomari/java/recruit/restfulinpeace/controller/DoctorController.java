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

import rokomari.java.recruit.restfulinpeace.lib.Helper;
import rokomari.java.recruit.restfulinpeace.lib.Messages;
import rokomari.java.recruit.restfulinpeace.model.Doctor;
import rokomari.java.recruit.restfulinpeace.service.DoctorService;

@RestController
@RequestMapping("/api")
public class DoctorController {
	
	@Autowired
    Messages messages;
	
	@Autowired
	private DoctorService doctorService;

	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/insert/doctor/new", method = RequestMethod.POST)
	public ResponseEntity<Object> createDoctor(@Valid @RequestBody Doctor doctor) {
		doctorService.save(doctor);
		return ResponseEntity.ok().body("{ \"status\": \"success\" }");
	}
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/delete/doctors", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteDoctor(@RequestHeader HttpHeaders headers) {
		
		Long doctorId = Helper.hasValidId(headers.get("doctor_id"));
		
		if(doctorId == -1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		doctorService.delete(doctorId);
		return ResponseEntity.ok().body("{ \"status\": \"deleted\" }");
	}
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/update/doctors", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@Valid @RequestBody Doctor doctor, @RequestHeader HttpHeaders headers) {
		
		Long doctorId = Helper.hasValidId(headers.get("doctor_id"));
		
		if(doctorId == -1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.json("404", "doctor id required"));
		}
		
		// there must be a better way!!
		Optional<Doctor> docexists = doctorService.findOne(doctorId);
		
	
		if( docexists == null || !docexists.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.json("404", "no data found to update"));
		}
		
		doctor.setId(doctorId);
		doctorService.save(doctor);
		return ResponseEntity.ok().body("{ \"status\": \"updated\" }");
	}
	
	@PreAuthorize ("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/doctors", method = RequestMethod.GET)
	public ResponseEntity<Object> findOne(@RequestHeader HttpHeaders headers) {

		
		List<String> id = headers.get("doctor_id");
		Long doctor_id = null;
		
		//no doctor_id provided, so just return all doctor list
		if(id == null || id.size() == 0) {
			List<Doctor> doctors = doctorService.getAll();
			return new ResponseEntity<Object>(doctors, HttpStatus.OK);
		}
		
		
		try {
			doctor_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}

		Optional<Doctor> doctor = doctorService.findOne(doctor_id);
		
		if(doctor == null || !doctor.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.json("404", "no data found"));
		}
		
		return ResponseEntity.ok().body(doctor);
	}


	
}
