package com.bridgelabz.fundoonotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.repository.IUserRepository;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.utility.JwtToken;


@Service
public class NoteService implements INoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Override
	public ResponseDTO createNote(NoteDTO noteDTO, String token) throws IllegalArgumentException, UnsupportedEncodingException {
		int id = (int) jwtToken.decodeToken(token);
		Note note = new Note(noteDTO);
		ResponseDTO response = null;
		if (note.getTitle().isEmpty() && note.getDescription().isEmpty()) {
			response = new ResponseDTO("Fail", null);
			return response;
		} else {
			note.setUser(userRepository.findById(id).get());
			noteRepository.save(note);
			response = new ResponseDTO("Success", note);
			return response;
		}	
	}

	@Override
	public List<Note> getNote(String token) throws IllegalArgumentException, UnsupportedEncodingException {
		int userid = (int) jwtToken.decodeToken(token);
		List<Note> notesList = (List<Note>) noteRepository.findByUserId(userid);
		return notesList;
	}
	
}
