package rokomari.java.recruit.restfulinpeace.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rokomari.java.recruit.restfulinpeace.model.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	List<Appointment> findByDoctor_id(Long doctorId);
	
	List<Appointment> findByPatient_id(Long patientId);
	
	List<Appointment> findByDoctor_idAndPatient_id(Long doctorId, Long patientId);
	

}
