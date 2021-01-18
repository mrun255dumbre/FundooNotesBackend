package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.ToString;

public class UserDTO {
	private int id;

	@NotBlank
	@Size(max = 20)
	public String username;

	@NotBlank
	@Size(max = 50)
	@Email
	public String email;

	@NotBlank
	@Size(max = 20)
	public String password;
	
	@NotBlank
	@Size(max = 13)
	public String phoneNumber;
	
	@Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                '}';
    }
}
