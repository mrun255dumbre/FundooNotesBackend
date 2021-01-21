package com.bridgelabz.fundoonotes.services;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.model.Note;

public interface INoteService {

	ResponseDTO createNote(NoteDTO noteDTO, String token);

	List<Note> getNote(String token);

	ResponseDTO updateNote(NoteDTO noteDTO, String token, int id);

	ResponseDTO deleteNote(String token, int id);

	ResponseDTO pinNote(String token, int id);

	ResponseDTO trashNote(String token, int id);

	ResponseDTO archiveNote(String token, int id);

}
