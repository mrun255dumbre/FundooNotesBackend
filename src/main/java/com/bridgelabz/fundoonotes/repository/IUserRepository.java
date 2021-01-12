package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.bridgelabz.fundoonotes.model.UserData;

public interface IUserRepository extends JpaRepository<UserData, Integer> {
	
	Optional<UserData> findByEmail(String email);
	
	boolean existsByPassword(String password);
	
}
