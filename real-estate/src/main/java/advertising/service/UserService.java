package advertising.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import advertising.dto.UserDto;
import advertising.exception.EmailExistsException;
import advertising.model.User;

public interface UserService {

	List<User> findAll();
	
	User save(User user);
	
	User save(UserDto user) throws EmailExistsException;
	
	void delete(User user);

	User findOne(Long id);
	
	User createUserAccount(UserDto userDto);

	User findByUsername(String username);
	
		
}
