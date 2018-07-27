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
import rokomari.java.recruit.restfulinpeace.model.Patient;
import rokomari.java.recruit.restfulinpeace.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientController {
	
	
	@Autowired
    Messages messages;
	
	@Autowired
	private PatientService patientService;

	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/insert/patient/new", method = RequestMethod.POST)
	public ResponseEntity<Object> createPatient(@Valid @RequestBody Patient patient) {
		patientService.save(patient);
		return ResponseEntity.ok().body("{ \"status\": \"success\" }");
	}
	

	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/delete/patients", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePatient(@RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("patient_id");
		Long patient_id = null;
		
		if(id == null || id.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		try {
			patient_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		patientService.delete(patient_id);
		return ResponseEntity.ok().body("{ \"status\": \"deleted\" }");
	}
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/update/patients", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@Valid @RequestBody Patient patient, @RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("patient_id");
		Long patient_id = null;
		
		if(id == null || id.size() == 0 || patient == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.json("404", "patient id required"));
		}
		
		try {
			patient_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}
		
		// there must be a better way!!
		Optional<Patient> docexists = patientService.findOne(patient_id);
		
	
		if( docexists == null || !docexists.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.json("404", "no data found to update"));
		}
		
		patient.setId(patient_id);
		patientService.save(patient);
		return ResponseEntity.ok().body("{ \"status\": \"updated\" }");
	}
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/patients", method = RequestMethod.GET)
	public ResponseEntity<Object> findOne(@RequestHeader HttpHeaders headers) {
		
		Long patientId = Helper.hasValidId(headers.get("patient_id"));

		//no patient_id provided, so just return all patien list
		if(patientId == -1) {
			List<Patient> patients = patientService.getAll();
			return new ResponseEntity<Object>(patients, HttpStatus.OK);
		}
		
		Optional<Patient> patient = patientService.findOne(patientId);
		
		if( patient == null || !patient.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.json("404", "no data found"));
		}
		
		return ResponseEntity.ok().body(patient);
	}
	
}
