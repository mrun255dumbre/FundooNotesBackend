package com.bridgelabz.fundoonotes.services;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.model.Label;

public interface ILabelService {

	ResponseDTO createLabel(LabelDTO labelDTO, String token);

	ResponseDTO updateLabel(LabelDTO labelDTO, String token, int id);

	ResponseDTO deleteLabel(String token, int id);

	List<Label> getLabels(String token);

	ResponseDTO addLabelNote(String token, int labelId, int noteId);

	ResponseDTO removeLabelNote(String token, int labelId, int noteId);

}
