package com.bridgelabz.fundoonotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.model.UserData;
import com.bridgelabz.fundoonotes.repository.IUserRepository;
import com.bridgelabz.fundoonotes.utility.EmailService;
import com.bridgelabz.fundoonotes.utility.JwtToken;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@Slf4j
public class UserServices implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public ResponseDTO signupUserData(UserDTO userDTO) {
		ResponseDTO response = null;
		UserData user = new UserData(userDTO);
		Optional<UserData> availability = userRepository.findByEmail(user.getEmail());
		if(availability.isPresent()) {
			response = new ResponseDTO("Email Already Present", null);
			return response;
		} else {
			log.debug("User Data: " + user.toString());
			userRepository.save(user);
			response = new ResponseDTO("Success", user);
			return response;
		}
	}

	@Override
	public ResponseDTO signinUserData(LoginDTO loginDTO) {
		ResponseDTO response = null;
		UserData user = new UserData(loginDTO);
		log.debug("User Data: " + user.toString());
		Optional<UserData> availability = userRepository.findByEmail(user.getEmail());
		if(availability.isPresent()) {
			boolean checkUser = userRepository.existsByPassword(user.getPassword());
			if(checkUser) {
				String tokengenerate = jwtToken.generateToken(availability.get().getId());
				response = new ResponseDTO("Token", tokengenerate);
				return response;
			}
		}
		response = new ResponseDTO("User not Present", null);
		return response;
	}

	@Override
	public ResponseDTO forgotPassword(String email) {
		ResponseDTO response = null;
		Optional<UserData> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			String subject = "Reset Password";
			String tokengenerate = jwtToken.generateToken(user.get().getId());
			String text = "To reset your Password, please click here :"+"http://localhost:3000/reset-password/" + tokengenerate;
			emailService.sendEmail(user.get().getEmail(), subject, text);
			response = new ResponseDTO("Token", tokengenerate);
		}
		return response;
	}

	@Override
	public ResponseDTO resetPassword(String token, LoginDTO loginDTO) {
		ResponseDTO response = null;
		long id = jwtToken.decodeToken(token);
		Optional<UserData> user = userRepository.findById((int) id);
		if (user.isPresent()) {
			user.get().setPassword(loginDTO.getPassword());
			userRepository.save(user.get());
			response = new ResponseDTO("Success", user);
		}
		return response;
	}
}
