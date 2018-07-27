package rokomari.java.recruit.restfulinpeace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.model.UserRole;
import rokomari.java.recruit.restfulinpeace.repository.UserRoleRepository;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public List<UserRole> userRoles(Long userId){
		return userRoleRepository.findByUserId(userId);
	}
	
	
	public UserRole save(UserRole userRole){
		return userRoleRepository.save(userRole);
	}
	
	public void delete(Long roleId){
		userRoleRepository.deleteById(roleId);
	}
	
	
	public Optional<UserRole> findOne(Long roleId) {
		return userRoleRepository.findById(roleId);
	}
}
