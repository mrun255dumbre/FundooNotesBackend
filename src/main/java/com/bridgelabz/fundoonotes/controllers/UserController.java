package com.bridgelabz.fundoonotes.controllers;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.services.IUserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	@Autowired
	private IUserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO){
		ResponseDTO user = userService.signupUserData(userDTO);
		return new ResponseEntity<ResponseDTO>(user, HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<ResponseDTO> signinUser(@Valid @RequestBody LoginDTO loginDTO) {
		ResponseDTO responseDTO = userService.signinUserData(loginDTO);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@PostMapping("/forgotpassword")
    public ResponseEntity<ResponseDTO> forgotPasword(@RequestBody LoginDTO loginDTO){
		ResponseDTO respDTO = userService.forgotPassword(loginDTO.getEmail());
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
	
	@PutMapping("/resetpassword/{token}")
    public ResponseEntity<ResponseDTO> resetpassword(@PathVariable("token") String token, @RequestBody LoginDTO loginDTO) {
		ResponseDTO respDTO = userService.resetPassword(token, loginDTO);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}
