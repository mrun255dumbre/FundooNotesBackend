package com.bridgelabz.fundoonotes.model;


import com.bridgelabz.fundoonotes.dto.NoteDTO;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "notes")
public @Data class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;
	
	private String title;
	
	private String description;
	
	private boolean isPin;
	private boolean isTrash;
	private boolean isArchive;
	
	public Note() { }
	
	public Note(NoteDTO noteDTO) {
		this.addNoteData(noteDTO);
	}
	
	public void addNoteData(NoteDTO noteDTO) {
		this.user = noteDTO.userId;
		this.title = noteDTO.title;
		this.description = noteDTO.description;
		this.isArchive = noteDTO.isArchive;
		this.isPin = noteDTO.isPin;
		this.isTrash = noteDTO.isTrash;
	}
	
	public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
}
