package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.ToString;

public @ToString class LoginDTO {
	private int id;

	@NotBlank
	@Size(max = 50)
	@Email
	public String email;

	@NotBlank
	@Size(max = 20)
	public String password;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
