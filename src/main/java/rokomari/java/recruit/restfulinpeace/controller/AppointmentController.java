package rokomari.java.recruit.restfulinpeace.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import rokomari.java.recruit.restfulinpeace.lib.Helper;
import rokomari.java.recruit.restfulinpeace.lib.Messages;
import rokomari.java.recruit.restfulinpeace.model.Appointment;
import rokomari.java.recruit.restfulinpeace.service.AppointmentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
    Messages messages;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
    private Validator validator;
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/appointments", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestHeader HttpHeaders headers) throws AuthenticationException {
		
		Long appointmentId = Helper.hasValidId(headers.get("appointment_id"));
		
		if( appointmentId != -1 ) {
			Optional<Appointment> appointment = appointmentService.findOne(appointmentId);
			return (appointment == null || !appointment.isPresent()) 
					? ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404")) 
					:  ResponseEntity.ok().body(appointment);
		}
		
		Long doctorId = Helper.hasValidId(headers.get("doctor_id"));
		Long patientId = Helper.hasValidId(headers.get("patient_id"));
		
		/**
		 * no id provided, return all roles
		 */
		if( doctorId == -1 && patientId == -1 ) {
			return ResponseEntity.ok().body(appointmentService.getAll());
		}
		
		/**
		 * specific doctor appointments
		 */
		if( doctorId != -1 && patientId == -1 ) {
			return ResponseEntity.ok().body(appointmentService.getDoctorAppointments(doctorId));
		}
		
		/**
		 * specific patient appointments
		 */
		if( doctorId == -1 && patientId != -1 ) {
			return ResponseEntity.ok().body(appointmentService.getPatientAppointments(patientId));
		}
		
		/**
		 * return all appointments of a doctor and patient
		 */
		return ResponseEntity.ok().body(appointmentService.getDoctorPatientAppointments(doctorId, patientId));
	}
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/insert/appointment/new", method = RequestMethod.POST)
	public ResponseEntity<?> insertAppointment(@Valid @RequestBody Appointment appointment) throws AuthenticationException {
		
		Appointment saved = appointmentService.save(appointment);
		
		System.out.println("saved Appointment");
		System.out.println(saved);
		
		return ResponseEntity.ok().body("{ \"Status\": \"Success\" }");
	}
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/delete/appointments", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAppointment(@RequestHeader HttpHeaders headers) throws AuthenticationException {
		
		Long appointmentId = Helper.hasValidId(headers.get("appointment_id"));
		
		if(appointmentId == -1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		appointmentService.delete(appointmentId);
		return ResponseEntity.ok().body("{ \"status\": \"deleted\" }");
	}
	
	
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/update/appointments", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment, @RequestHeader HttpHeaders headers) throws AuthenticationException {
		
		Long appointmentId = Helper.hasValidId(headers.get("appointment_id"));
		
		if(appointmentId == -1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.get("http.response.404"));
		}
		
		Optional<Appointment> appointmentExists = appointmentService.findOne(appointmentId);
		
		if( appointmentExists == null || !appointmentExists.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages.json("404", "no data found to update"));
		}

		Appointment temp = appointmentExists.get();
		appointment.setId(temp.getId());
		appointment.setDoctor_id(temp.getDoctor_id());
		appointment.setPatient_id(temp.getPatient_id());
		
		Set<ConstraintViolation<Appointment>> invalid = validator.validate(appointment);
		
		/**
		 * model validation failed
		 */
		if( invalid.size() > 0 ) {
			
			List<String> errors = new ArrayList<String>();
			
			for(ConstraintViolation<Appointment> ops : invalid) {
				errors.add(ops.getPropertyPath() + " " +  ops.getMessage());
			}
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode errorNode = mapper.createObjectNode();
			errorNode.put("status","failed");
			errorNode.putArray("errors").addAll((ArrayNode)mapper.valueToTree(errors));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorNode);
		}
		
		appointmentService.save(appointment);
		return ResponseEntity.ok().body("{ \"status\": \"updated\" }");
	}
	
}
