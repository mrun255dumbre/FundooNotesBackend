package com.bridgelabz.fundoonotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<ResponseDTO> createNote(@RequestBody NoteDTO noteDTO, @RequestHeader String token) {
		ResponseDTO response = noteService.createNote(noteDTO, token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/note")
	public List<Note> getNote(@RequestHeader String token) {
		List<Note> notesList = noteService.getNote(token);
		return notesList;
	}
	
	@PutMapping("/note")
	public ResponseEntity<ResponseDTO> updateNote(@RequestBody NoteDTO noteDTO, @RequestHeader String token, @RequestParam int id) {
		ResponseDTO response = noteService.updateNote(noteDTO, token, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/note")
	public ResponseEntity<ResponseDTO> deleteNote(@RequestHeader String token, @RequestParam int id) {
		ResponseDTO response = noteService.deleteNote(token, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("note/pin")
	public ResponseEntity<ResponseDTO> pinNote(@RequestHeader String token, @RequestParam int id) {
		ResponseDTO response = noteService.pinNote(token, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("note/archive")
	public ResponseEntity<ResponseDTO> archiveNote(@RequestHeader String token, @RequestParam int id) {
		ResponseDTO response = noteService.archiveNote(token, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("note/trash")
	public ResponseEntity<ResponseDTO> trashNote(@RequestHeader String token, @RequestParam int id) {
		ResponseDTO response = noteService.trashNote(token, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
