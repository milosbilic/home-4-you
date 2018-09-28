package advertising.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import advertising.dto.UserDto;
import advertising.exception.EmailExistsException;
import advertising.helper.converter.ConvertToUserDto;
import advertising.helper.converter.ConvertToUserEntity;
import advertising.model.Role;
import advertising.model.User;
import advertising.repository.RoleRepository;
import advertising.repository.UserRepository;
import advertising.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConvertToUserEntity toEntity;
	
	@Autowired
	private ConvertToUserDto toDto;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User save(UserDto user) throws EmailExistsException{
		if (emailExists(user.getEmail())) {
			throw new EmailExistsException("There is an account with this email: "
					+ user.getEmail());
		}
		User registered = toEntity.convertMinimum(user);
		registered.setPassword(passwordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findOne(2L));
		registered.setRoles(roles);
		return userRepository.save(registered);
	}

	private boolean emailExists(String email) {
	     User user = userRepository.findByEmail(email);
	        if (user != null) {
	            return true;
	        }
	        return false;
	}
	
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User createUserAccount(UserDto userDto, BindingResult result) {
		User registered = null;	
		try {
			registered = save(userDto);
		} catch (EmailExistsException e) {
			return null;
		}
		return registered;
	}

}
