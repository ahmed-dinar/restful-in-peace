package rokomari.java.recruit.restfulinpeace.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rokomari.java.recruit.restfulinpeace.model.Doctor;
import rokomari.java.recruit.restfulinpeace.service.DoctorService;

@RestController
@RequestMapping("/api")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	
	@RequestMapping(value="/insert/doctor/new", method = RequestMethod.POST)
	public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
		return doctorService.save(doctor);
	}
	
	
	@RequestMapping(value="/delete/doctors", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("doctor_id");
		Long doctor_id = null;
		
		if(id == null || id.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			doctor_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}
		
		doctorService.delete(doctor_id);
		return ResponseEntity.ok().body("{ \"status\": \"deleted\" }");
	}
	
	
	@RequestMapping(value="/update/doctors", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@Valid @RequestBody Doctor doctor, @RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("doctor_id");
		Long doctor_id = null;
		
		if(id == null || id.size() == 0 || doctor == null) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			doctor_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}
		
		// there must be a better way!!
		Optional<Doctor> docexists = doctorService.findOne(doctor_id);
		
		
		System.out.println("docexists");
		System.out.println(docexists);
		
		
		if( docexists == null || !docexists.isPresent() ) {
			return ResponseEntity.notFound().build();
		}

		doctor.setId(doctor_id);
		return ResponseEntity.ok().body(doctorService.save(doctor));
	}
	

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
		
		if(doctor == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(doctor);
	}
	
}
