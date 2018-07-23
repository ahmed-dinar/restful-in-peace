package rokomari.java.recruit.restfulinpeace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.model.Doctor;
import rokomari.java.recruit.restfulinpeace.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	/**
	 * Create a new doctor
	 * @param doctor
	 * @return
	 */
	public Doctor save(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	
	/**
	 * @param doctor
	 * @return
	 */
	public Doctor update(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	
	/**
	 * @return
	 */
	public void delete(Long id) {
		doctorRepository.deleteById(id);
	}
	
	
	/**
	 * @return
	 */
	public List<Doctor> getAll(){
		return (List<Doctor>) doctorRepository.findAll();
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Doctor> findOne(Long id) {
		return doctorRepository.findById(id);
	}
	
	
}
