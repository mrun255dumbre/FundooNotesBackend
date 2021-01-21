package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoonotes.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	List<Note> findByUserId(int userid);

	Note findByNoteIdAndUserId(int noteId, int userid);

}
