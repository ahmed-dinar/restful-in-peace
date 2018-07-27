package rokomari.java.recruit.restfulinpeace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.model.Role;
import rokomari.java.recruit.restfulinpeace.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	public Role save(Role role) {
		return roleRepository.save(role);
	}

	public List<Role> getAll() {
		return (List<Role>) roleRepository.findAll();
	}

	public void delete(Long roleId) {
		roleRepository.deleteById(roleId);
	}

	public Role createIfNotExists(String role) {

		Role r = roleRepository.findByName(role);

		if (r != null) {
			return r;
		}
		r = new Role();
		r.setName(role);
		r.setStatus("ACTIVE");

		return roleRepository.save(r);
	}

}
