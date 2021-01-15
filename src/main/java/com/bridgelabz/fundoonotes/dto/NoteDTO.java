package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bridgelabz.fundoonotes.model.UserData;

import lombok.ToString;

public @ToString class NoteDTO {
	private int noteId;
	
	public UserData userId;
	
	@NotBlank
	@Size(max = 50)
	public String title;

	@NotBlank
	public String description;
}
