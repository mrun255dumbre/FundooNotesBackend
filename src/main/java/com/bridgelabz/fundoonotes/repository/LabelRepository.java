package com.bridgelabz.fundoonotes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundoonotes.model.Label;


public interface LabelRepository extends JpaRepository<Label, Integer> {

	Optional<Label> findByLabelName(String labelName);
	
	@Query(value = "SELECT * FROM labels where label_id = :id and user_id =:userid", nativeQuery=true)
	Label findByLabelIdAndUserId(int id, int userid);

	@Query(value = "SELECT * FROM labels where user_id =:user_id", nativeQuery=true)
	List<Label> findByUserId(int user_id);

}
