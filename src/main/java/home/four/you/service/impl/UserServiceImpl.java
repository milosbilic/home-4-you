package home.four.you.service.impl;

import home.four.you.model.dto.UserDto;
import home.four.you.exception.EmailExistsException;
import home.four.you.model.entity.Role;
import home.four.you.model.entity.User;
import home.four.you.repository.RoleRepository;
import home.four.you.repository.UserRepository;
import home.four.you.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private ConvertToUserEntity toEntity;

//    @Autowired
//    private ConvertToUserDto toDto;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User save(UserDto user) throws EmailExistsException {
        if (emailExists(user.getEmail())) {
            throw new EmailExistsException("There is an account with this email: "
                    + user.getEmail());
        }
        var registered = new User();
//        User registered = toEntity.convertMinimum(user);
//        registered.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(2L).orElseThrow());
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
    public User createUserAccount(UserDto userDto) {
        User registered = null;
        try {
            registered = save(userDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
