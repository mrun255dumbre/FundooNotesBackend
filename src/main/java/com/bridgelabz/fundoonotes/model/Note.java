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
    @JoinColumn(name = "user")
    private UserData user;
    //private int userId;
	
	private String title;
	
	private String description;
	
	public Note() { }
	
	public Note(NoteDTO noteDTO) {
		this.addNoteData(noteDTO);
	}
	
	public void addNoteData(NoteDTO noteDTO) {
		this.user = noteDTO.userId;
		this.title = noteDTO.title;
		this.description = noteDTO.description;
	}
	
	public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
}
