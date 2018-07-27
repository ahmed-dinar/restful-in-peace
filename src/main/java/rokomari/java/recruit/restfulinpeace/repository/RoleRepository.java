package rokomari.java.recruit.restfulinpeace.repository;

import org.springframework.data.repository.CrudRepository;

import rokomari.java.recruit.restfulinpeace.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

}