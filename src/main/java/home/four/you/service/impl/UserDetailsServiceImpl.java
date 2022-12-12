package home.four.you.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import home.four.you.model.entity.User;
import home.four.you.repository.UserRepository;
import home.four.you.security.Home4YouUserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		   User user = userRepository.findByEmail(username);
	        if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new Home4YouUserPrincipal(user);
	}

}
