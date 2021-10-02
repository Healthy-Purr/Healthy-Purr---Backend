package com.dawmecnagtrt.healthypurr.repository;

import com.dawmecnagtrt.healthypurr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
    Boolean existsByUsername( String username);
}
