package rokomari.java.recruit.restfulinpeace.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rokomari.java.recruit.restfulinpeace.repository.UserRepository;
import rokomari.java.recruit.restfulinpeace.model.User;



@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	/**
	 * better way to auto encode using security? need to find out from doc!
	 * @param user
	 * @return
	 */
	public User save(User user) {
		final String encodedPassoword = passwordEncoder().encode(user.getPassword());
		user.setPassword(encodedPassoword);
		return this.userRepository.save(user);
	}
	
	/**
	 * all registered user list
	 * @return
	 */
	public List<User> getAll(){
		return (List<User>) this.userRepository.findAll();
	}
	
	
	public Optional<User> findOne(Long id) {
		return this.userRepository.findById(id);
	}
	
	
	public User findbyEmail(String email) {
		return userRepository.findByEmail(email);
	}

	

	/**
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByEmail(email);
		
		if(user == null){
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}
	
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
}
