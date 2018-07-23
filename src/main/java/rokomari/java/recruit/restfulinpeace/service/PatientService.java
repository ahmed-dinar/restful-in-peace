package rokomari.java.recruit.restfulinpeace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.model.Patient;
import rokomari.java.recruit.restfulinpeace.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;
	
	/**
	 * @param doctor
	 * @return
	 */
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}
	
	
	/**
	 * ok, looks like same as save, need to find out more!
	 * @param doctor
	 * @return
	 */
	public Patient update(Patient patient) {
		return patientRepository.save(patient);
	}
	
	
	/**
	 * @return
	 */
	public void delete(Long id) {
		patientRepository.deleteById(id);
	}
	
	
	/**
	 * @return
	 */
	public List<Patient> getAll(){
		return (List<Patient>) patientRepository.findAll();
	}
	
	
	/**
	 * @param id
	 * @return
	 */
	public Optional<Patient> findOne(Long id) {
		return patientRepository.findById(id);
	}
	
}
