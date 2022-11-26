package home.four.you.service;

import java.util.List;

import home.four.you.dto.UserDto;
import home.four.you.exception.EmailExistsException;
import home.four.you.model.entity.User;

public interface UserService {

	List<User> findAll();
	
	User save(User user);
	
	User save(UserDto user) throws EmailExistsException;
	
	void delete(User user);

	User findOne(Long id);
	
	User createUserAccount(UserDto userDto);

	User findByUsername(String username);
	
		
}
