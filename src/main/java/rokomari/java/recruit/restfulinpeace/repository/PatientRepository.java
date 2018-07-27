package rokomari.java.recruit.restfulinpeace.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import rokomari.java.recruit.restfulinpeace.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

	@Transactional
	void deleteById(Long id);

}
