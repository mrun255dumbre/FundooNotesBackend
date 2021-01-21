package com.bridgelabz.fundoonotes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Component
@Entity
@Table( name= "labels")
public @Data class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int labelId;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userId;
	
	private String labelName;
	
	@ManyToMany(mappedBy="noteLabels", cascade=CascadeType.ALL)
	private List<Note> listNotes;
	
	public Label() { }
	
	public Label(LabelDTO labelDTO) {
		this.addLabelData(labelDTO);
	}
	
	public void addLabelData(LabelDTO labelDTO) {
		this.labelName = labelDTO.labelName;
	}

	public UserData getUser() {
        return userId;
    }

    public void setUser(UserData user) {
        this.userId = user;
    }
	
	@JsonIgnore
	public List<Note> getLNotes() {
		return listNotes;
	}

	@JsonIgnore
	public void setLNotes(List<Note> listNotes) {
		this.listNotes = listNotes;
	}

	@Override
	public String toString() {
		return "Labels [labelId=" + labelId + ", userId=" + userId + ", labelName=" + labelName + "]";
	}

}
