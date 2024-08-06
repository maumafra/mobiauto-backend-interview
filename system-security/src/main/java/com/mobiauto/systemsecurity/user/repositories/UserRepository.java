package com.mobiauto.systemsecurity.user.repositories;

import com.mobiauto.systemsecurity.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(final String username);
}
