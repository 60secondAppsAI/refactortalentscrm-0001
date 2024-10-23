package com.refactortalentscrm.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.refactortalentscrm.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.refactortalentscrm.domain.Note;
import com.refactortalentscrm.dto.NoteDTO;
import com.refactortalentscrm.dto.NoteSearchDTO;
import com.refactortalentscrm.dto.NotePageDTO;
import com.refactortalentscrm.service.NoteService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/note")
@RestController
public class NoteController {

	private final static Logger logger = LoggerFactory.getLogger(NoteController.class);

	@Autowired
	NoteService noteService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Note> getAll() {

		List<Note> notes = noteService.findAll();
		
		return notes;	
	}

	//@ReadAccess
	@GetMapping(value = "/{noteId}")
	@ResponseBody
	public NoteDTO getNote(@PathVariable Integer noteId) {
		
		return (noteService.getNoteDTOById(noteId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<?> addNote(@RequestBody NoteDTO noteDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = noteService.addNote(noteDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/notes")
	public ResponseEntity<NotePageDTO> getNotes(NoteSearchDTO noteSearchDTO) {
 
		return noteService.getNotes(noteSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateNote", method = RequestMethod.POST)
	public ResponseEntity<?> updateNote(@RequestBody NoteDTO noteDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = noteService.updateNote(noteDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
