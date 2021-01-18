package com.bridgelabz.fundoonotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.exception.UserException;
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
	public ResponseDTO createNote(NoteDTO noteDTO, String token) {
		int id = (int) jwtToken.decodeToken(token);
		Note note = new Note(noteDTO);
		ResponseDTO response = null;
		if (note.getTitle().isEmpty() && note.getDescription().isEmpty()) {
			response = new ResponseDTO("Fail", null);
			return response;
		} else {
			note.setUser(userRepository.findById(id).get());
			noteRepository.save(note);
			response = new ResponseDTO("Note created", note);
			return response;
		}	
	}

	@Override
	public List<Note> getNote(String token) {
		int userid = (int) jwtToken.decodeToken(token);
		List<Note> notesList = (List<Note>) noteRepository.findByUserId(userid);
		return notesList;
	}

	@Override
	public ResponseDTO updateNote(NoteDTO noteDTO, String token, int id) {
		Note note = new Note(noteDTO);
		if (note.getTitle().isEmpty() && note.getDescription().isEmpty()) {
			throw new UserException("Notes are empty");
		}
		int userId = (int) jwtToken.decodeToken(token);
		Note notes = noteRepository.findByNoteIdAndUserId(id, userId);
		notes.setTitle(note.getTitle());
		notes.setDescription(note.getDescription());
		note = noteRepository.save(notes);
		ResponseDTO response = new ResponseDTO("Note Updated", note);
		return response;
	}

	@Override
	public ResponseDTO deleteNote(String token, int id) {
		ResponseDTO response = null;
		int userid = (int) jwtToken.decodeToken(token);
		Note note = noteRepository.findByNoteIdAndUserId(id, userid);
		if (note.isTrash() == true) {
			noteRepository.delete(note);
			response = new ResponseDTO("Note Deleted", note);
		} else {
			throw new UserException("Deletion Failed");
		}
		return response;
	}

	@Override
	public ResponseDTO pinNote(String token, int id) {
		ResponseDTO response = null;
		int userid = (int) jwtToken.decodeToken(token);
		Note note = noteRepository.findByNoteIdAndUserId(id, userid);
		if (note.isPin() == false) {
			note.setPin(true);
			noteRepository.save(note);
			response = new ResponseDTO("Note Pinned", note);
		} else if (note.isPin() == true) {
			note.setPin(false);
			noteRepository.save(note);
			response = new ResponseDTO("Unpinned", note);
		} else {
			throw new UserException("Pinned Operation Failed");
		}
		return response;
	}

	@Override
	public ResponseDTO trashNote(String token, int id) {
		ResponseDTO response = null;
		int userid = (int) jwtToken.decodeToken(token);
		Note note = noteRepository.findByNoteIdAndUserId(id, userid);
		if (note.isTrash() == false) {
			note.setTrash(true);
			noteRepository.save(note);
			response = new ResponseDTO("Note added to Trash", note);
		} else if (note.isTrash() == true) {
			note.setTrash(false);
			noteRepository.save(note);
			response = new ResponseDTO("Note Restore", note);
		} else {
			throw new UserException("Trash Operation Failed");
		}
		return response;
	}

	@Override
	public ResponseDTO archiveNote(String token, int id) {
		ResponseDTO response = null;
		int userid = (int) jwtToken.decodeToken(token);
		Note note = noteRepository.findByNoteIdAndUserId(id, userid);
		if (note.isArchive() == false) {
			note.setArchive(true);
			noteRepository.save(note);
			response = new ResponseDTO("Note Archived", note);
		} else if (note.isArchive() == true) {
			note.setArchive(false);
			noteRepository.save(note);
			response = new ResponseDTO("Note Unarchived", note);
		} else {
			throw new UserException("Archive Operation Failed");
		}
		return response;
	}
}
