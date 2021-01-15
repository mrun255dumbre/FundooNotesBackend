package com.bridgelabz.fundoonotes.services;

import java.io.UnsupportedEncodingException;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.model.UserData;

public interface IUserService {

	ResponseDTO signupUserData(UserDTO userDTO);

	ResponseDTO signinUserData(LoginDTO loginDTO);

	ResponseDTO forgotPassword(String email);

	ResponseDTO resetPassword(String token, LoginDTO loginDTO);
}
