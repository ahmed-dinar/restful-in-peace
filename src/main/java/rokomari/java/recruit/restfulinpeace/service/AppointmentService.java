package rokomari.java.recruit.restfulinpeace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.model.Appointment;
import rokomari.java.recruit.restfulinpeace.model.Role;
import rokomari.java.recruit.restfulinpeace.repository.AppointmentRepository;
import rokomari.java.recruit.restfulinpeace.repository.RoleRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public List<Appointment> getAll(){
		return (List<Appointment>) appointmentRepository.findAll();
	}
	
}
