package home.four.you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import home.four.you.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);
	
}
