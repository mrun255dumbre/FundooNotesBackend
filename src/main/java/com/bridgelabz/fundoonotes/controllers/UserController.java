package com.bridgelabz.fundoonotes.controllers;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.model.UserData;
import com.bridgelabz.fundoonotes.services.IUserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	@Autowired
	private IUserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO){
		UserData user = userService.signupUserData(userDTO);
		ResponseDTO respDTO = new ResponseDTO("Success", user);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<ResponseDTO> signinUser(@RequestBody LoginDTO loginDTO) throws IllegalArgumentException, UnsupportedEncodingException{
		ResponseDTO responseDTO = userService.signinUserData(loginDTO);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
}
