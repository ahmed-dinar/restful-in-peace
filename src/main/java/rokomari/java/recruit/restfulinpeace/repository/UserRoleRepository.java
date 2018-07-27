package rokomari.java.recruit.restfulinpeace.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rokomari.java.recruit.restfulinpeace.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	List<UserRole> findByUserId(Long userId);

}
