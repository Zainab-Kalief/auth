package com.wuraalese.auth2.repo;

import com.wuraalese.auth2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer>{
    Optional<User> findByName(String name);
}
