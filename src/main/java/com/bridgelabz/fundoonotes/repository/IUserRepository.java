package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import com.bridgelabz.fundoonotes.model.UserData;

public interface IUserRepository extends JpaRepository<UserData, Integer> {
	
	Optional<UserData> findByEmail(String email);
	
	boolean existsByPassword(String password);

	@Query(value = "SELECT * FROM users where id =:userId", nativeQuery=true)
	UserData findByUserId(int userId);
	
}
