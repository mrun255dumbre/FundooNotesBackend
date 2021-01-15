package com.bridgelabz.fundoonotes.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.services.INoteService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/noteservice")
public class NotesController {
	
	@Autowired
	private INoteService noteService;
	
	@PostMapping("/note")
	public ResponseEntity<ResponseDTO> createNote(@RequestBody NoteDTO noteDTO, @RequestHeader String token) throws IllegalArgumentException, UnsupportedEncodingException {
		ResponseDTO response = noteService.createNote(noteDTO, token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/note")
	public List<Note> getNote(@RequestHeader String token) throws IllegalArgumentException, UnsupportedEncodingException {
		List<Note> notesList = noteService.getNote(token);
		return notesList;
	}
}
