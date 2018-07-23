package rokomari.java.recruit.restfulinpeace.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import rokomari.java.recruit.restfulinpeace.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	@Transactional
	void deleteById(Long id);

}
