package com.meh.stuff.auth.db.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meh.stuff.auth.db.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findUserByUsername(String username);
}
