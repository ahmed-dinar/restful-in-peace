package rokomari.java.recruit.restfulinpeace.repository;

import org.springframework.data.repository.CrudRepository;

import rokomari.java.recruit.restfulinpeace.model.Appointment;
import rokomari.java.recruit.restfulinpeace.model.Role;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	
	
}
