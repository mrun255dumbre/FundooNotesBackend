package com.bridgelabz.fundoonotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.model.UserData;
import com.bridgelabz.fundoonotes.repository.IUserRepository;
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
	
	@Override
	public UserData signupUserData(UserDTO userDTO) {
		UserData user = new UserData(userDTO);
		log.debug("User Data: "+user.toString());
		return userRepository.save(user);
	}

	@Override
	public ResponseDTO signinUserData(LoginDTO loginDTO) throws IllegalArgumentException, UnsupportedEncodingException {
		ResponseDTO response = null;
		UserData user = new UserData(loginDTO);
		log.debug("User Data: "+user.toString());
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
}
