package com.bridgelabz.fundoonotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.UserData;
import com.bridgelabz.fundoonotes.repository.IUserRepository;
import com.bridgelabz.fundoonotes.repository.LabelRepository;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.utility.JwtToken;

@Service
public class LabelService implements ILabelService {

	@Autowired
	private JwtToken jwtToken;
	
	@Autowired 
	private LabelRepository labelRepository;
	
	@Autowired 
	private IUserRepository userRepository;
	
	@Autowired 
	private NoteRepository noteRepository;
	
	@Override
	public ResponseDTO createLabel(LabelDTO labelDTO, String token) {
		ResponseDTO response = null;
		Label label = new Label(labelDTO);
		int userId = (int) jwtToken.decodeToken(token);
		Optional<Label> availability = labelRepository.findByLabelName(label.getLabelName());
		if (availability.isPresent()) {
			throw new UserException("Label already present");
		} else {
			Optional<UserData> user = userRepository.findById(userId);
			label.setUser(user.get());
			user.get().getLabels().add(label);
			labelRepository.save(label);
			userRepository.save(user.get());
			response = new ResponseDTO("Label Created", label.toString());
		}
		return response;
	}

	@Override
	public ResponseDTO updateLabel(LabelDTO labelDTO, String token, int id) {
		ResponseDTO response = null;
		Label label = new Label(labelDTO);
		int userId = (int) jwtToken.decodeToken(token);
		Label labels = labelRepository.findByLabelIdAndUserId(id, userId);
		labels.setLabelName(label.getLabelName());
		labelRepository.save(labels);
		response = new ResponseDTO("Label Updated", labels.toString());
		return response;
	}

	@Override
	public ResponseDTO deleteLabel(String token, int labelId) {
		ResponseDTO response = null;
		int userId = (int) jwtToken.decodeToken(token);
		Label labels = labelRepository.findByLabelIdAndUserId(labelId, userId);
		UserData user = userRepository.findByUserId(userId);
		user.getLabels().remove(labels);
		userRepository.save(user);
		labelRepository.delete(labels);
		response = new ResponseDTO("Label Deleted", labels.toString());
		return response;
	}

	@Override
	public List<Label> getLabels(String token) {
		int userId = (int) jwtToken.decodeToken(token);
		List<Label> labelList = (List<Label>) labelRepository.findByUserId(userId);
		return labelList;
	}

	@Override
	public ResponseDTO addLabelNote(String token, int labelId, int noteId) {
		ResponseDTO response=null;
		int	id = (int) jwtToken.decodeToken(token);
		Optional<UserData> user = userRepository.findById(id);
		Label labels = labelRepository.findByLabelIdAndUserId(labelId, id);
		Note notes = noteRepository.findByNoteIdAndUserId(noteId, id);
		if (notes.getNoteLabels().contains(labels)==false && user.isPresent()) {
			labels.getListNotes().add(notes);
			notes.getNoteLabels().add(labels);
			labels.setLabelId(labelId);
			labelRepository.save(labels);
			noteRepository.save(notes);
			response = new ResponseDTO("Label added to note", notes.toString());
			return response;
		} else {
			throw new UserException("Label already added to note");
		}
	}

	@Override
	public ResponseDTO removeLabelNote(String token, int labelId, int noteId) {
		ResponseDTO response=null;
		int id = (int) jwtToken.decodeToken(token);
		Optional<UserData> user = userRepository.findById(id);
		Label labels = labelRepository.findByLabelIdAndUserId(labelId, id);
		Note notes = noteRepository.findByNoteIdAndUserId(noteId, id);
		if (user.isPresent()) {
			labels.getLNotes().remove(notes);
			notes.getNoteLabels().remove(labels);
			labelRepository.save(labels);
			noteRepository.save(notes);
			response = new ResponseDTO("Label remove from note", notes.toString());
			return response;
		}
		throw new UserException("Label not present");
	}
}
