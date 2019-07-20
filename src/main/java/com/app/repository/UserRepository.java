package com.app.repository;

import com.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
