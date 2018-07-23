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

import rokomari.java.recruit.restfulinpeace.model.Patient;
import rokomari.java.recruit.restfulinpeace.service.PatientService;


@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@RequestMapping(value="/insert/patient/new", method = RequestMethod.POST)
	public Patient createPatient(@Valid @RequestBody Patient patient) {
		return patientService.save(patient);
	}
	
	
	@RequestMapping(value="/delete/patients", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("patient_id");
		Long patient_id = null;
		
		if(id == null || id.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			patient_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}
		
		patientService.delete(patient_id);
		return ResponseEntity.ok().body("{ \"status\": \"deleted\" }");
	}
	
	
	@RequestMapping(value="/update/patients", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@Valid @RequestBody Patient patient, @RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("patient_id");
		Long patient_id = null;
		
		if(id == null || id.size() == 0 || patient == null) {
			return ResponseEntity.notFound().build();
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
		
		
		System.out.println("docexists");
		System.out.println(docexists);
		
		
		if( docexists == null || !docexists.isPresent() ) {
			return ResponseEntity.notFound().build();
		}

		patient.setId(patient_id);
		return ResponseEntity.ok().body(patientService.save(patient));
	}
	

	@RequestMapping(value="/patients", method = RequestMethod.GET)
	public ResponseEntity<Object> findOne(@RequestHeader HttpHeaders headers) {
		
		List<String> id = headers.get("patient_id");
		Long patient_id = null;
		
		//no doctor_id provided, so just return all doctor list
		if(id == null || id.size() == 0) {
			List<Patient> doctors = patientService.getAll();
			return new ResponseEntity<Object>(doctors, HttpStatus.OK);
		}
		
		
		try {
			patient_id = Long.parseLong(id.get(0));
		}
		catch (NumberFormatException e) {
			System.out.println(e);
			return ResponseEntity.notFound().build();
		}

		Optional<Patient> patient = patientService.findOne(patient_id);
		
		if(patient == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(patient);
	}
	
}
