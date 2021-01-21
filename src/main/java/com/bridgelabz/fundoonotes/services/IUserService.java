package com.bridgelabz.fundoonotes.services;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;

public interface IUserService {

	ResponseDTO signupUserData(UserDTO userDTO);

	ResponseDTO signinUserData(LoginDTO loginDTO);

	ResponseDTO forgotPassword(String email);

	ResponseDTO resetPassword(String token, LoginDTO loginDTO);

	ResponseDTO getUser(String token);
}
