package rokomari.java.recruit.restfulinpeace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.model.Appointment;
import rokomari.java.recruit.restfulinpeace.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public List<Appointment> getAll(){
		return (List<Appointment>) appointmentRepository.findAll();
	}
	
	
	public Optional<Appointment> findOne(Long appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}
	
	public List<Appointment> getDoctorAppointments(Long doctorId) {
		return appointmentRepository.findByDoctor_id(doctorId);
	}
	
	public List<Appointment> getPatientAppointments(Long patientId) {
		return appointmentRepository.findByPatient_id(patientId);
	}
	
	public List<Appointment> getDoctorPatientAppointments(Long doctorId, Long patientId) {
		return appointmentRepository.findByDoctor_idAndPatient_id(doctorId, patientId);
	}
	
	public Appointment save(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	public void delete(Long appointmentId) {
		appointmentRepository.deleteById(appointmentId);
	}
	
	public boolean exists(Long appointmentId) {
		return appointmentRepository.existsById(appointmentId);
	}
}
