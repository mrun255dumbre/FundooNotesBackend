package com.bridgelabz.fundoonotes.model;

import java.util.List;

import javax.persistence.*;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "email") 
		})
public @Data class UserData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private String phoneNumber;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	private List<Label> labels;
	
	public UserData() { }
	
	public UserData(UserDTO userDTO) {
		this.addUserData(userDTO);
	}
	
	public void addUserData(UserDTO userDTO) {
		this.username = userDTO.username;
		this.password = userDTO.password;
		this.email = userDTO.email;
		this.phoneNumber = userDTO.phoneNumber;
	}
	
	public UserData(LoginDTO loginDTO) {
		this.email = loginDTO.email;
		this.password = loginDTO.password;
	}
	
	@Override
    public String toString() {
        return "User{" +
        		"id='" + id + '\'' +
        		"userName='" + username + '\'' +
                "email='" + email + '\'' +
                '}';
    }
}
