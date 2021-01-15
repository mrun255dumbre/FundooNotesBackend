package com.bridgelabz.fundoonotes.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.model.Note;

public interface INoteService {

	ResponseDTO createNote(NoteDTO noteDTO, String token) throws IllegalArgumentException, UnsupportedEncodingException;

	List<Note> getNote(String token) throws IllegalArgumentException, UnsupportedEncodingException;

}
