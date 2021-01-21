package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotBlank;

import lombok.ToString;

public class LabelDTO {
	
	@NotBlank
	public String labelName;

}
