package com.bridgelabz.fundoonotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.dto.ResponseDTO;
import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.services.ILabelService;

@CrossOrigin(allowedHeaders="*",origins="*")
@RestController
@RequestMapping("/user")
public class LabelController {
	
	@Autowired
	ILabelService labelService;

	@PostMapping("/label")
	public ResponseEntity<ResponseDTO> createlabels(@RequestBody LabelDTO labelDTO, @RequestHeader String token) {
		ResponseDTO response=labelService.createLabel(labelDTO,token);
		return  new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@PutMapping("/label")
	public ResponseEntity<ResponseDTO> updatelabels(@RequestBody LabelDTO labelDTO, @RequestHeader String token, @RequestParam int id) {
		ResponseDTO response = labelService.updateLabel(labelDTO, token, id);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/label")
	public ResponseEntity<ResponseDTO> deletelabels(@RequestHeader String token, @RequestParam int id) {
		ResponseDTO response=labelService.deleteLabel(token, id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/label")	
	public List<Label> getLabels(@RequestHeader String token){
		List<Label> labelList=labelService.getLabels(token);
		return labelList;
	}
	
	@GetMapping("/labels")
	public ResponseEntity<ResponseDTO> addLabeltoNote(@RequestHeader String token, @RequestParam int labelId, @RequestParam int noteId) {
		ResponseDTO response=labelService.addLabelNote(token, labelId, noteId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/label/remove")
	public ResponseEntity<ResponseDTO> removeLabeltoNote(@RequestHeader String token, @RequestParam int labelId, @RequestParam int noteId){
		ResponseDTO response=labelService.removeLabelNote(token, labelId, noteId) ;
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
