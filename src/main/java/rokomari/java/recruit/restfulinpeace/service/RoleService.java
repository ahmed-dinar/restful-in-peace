package rokomari.java.recruit.restfulinpeace.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.model.Role;
import rokomari.java.recruit.restfulinpeace.model.User;
import rokomari.java.recruit.restfulinpeace.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserService userService;
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
	
	public Set<Role> getUserRoles(Long id){
		Optional<User> user = userService.findOne(id);
		
		if(user == null || !user.isPresent()) {
			return null;
		}

		return user.get().getRoles();
	}
	
	
	public List<Role> getAll(){
		return (List<Role>) roleRepository.findAll();
	}
	
	public Role createIfNotExists(String role) {
		
		Role r = roleRepository.findByName(role);

		if( r != null  ) {
			return r;
		}
		r = new Role();
		r.setName(role);
		r.setStatus("ACTIVE");
		
		return roleRepository.save(r);
	}
	
}
