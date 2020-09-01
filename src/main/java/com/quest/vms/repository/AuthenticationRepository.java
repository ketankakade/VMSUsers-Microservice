package com.quest.vms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.vms.entity.SignIn;

public interface AuthenticationRepository extends JpaRepository<SignIn, Long> {
	Optional<SignIn> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
