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

import com.refactortalentscrm.domain.Assignment;
import com.refactortalentscrm.dto.AssignmentDTO;
import com.refactortalentscrm.dto.AssignmentSearchDTO;
import com.refactortalentscrm.dto.AssignmentPageDTO;
import com.refactortalentscrm.service.AssignmentService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/assignment")
@RestController
public class AssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(AssignmentController.class);

	@Autowired
	AssignmentService assignmentService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Assignment> getAll() {

		List<Assignment> assignments = assignmentService.findAll();
		
		return assignments;	
	}

	//@ReadAccess
	@GetMapping(value = "/{assignmentId}")
	@ResponseBody
	public AssignmentDTO getAssignment(@PathVariable Integer assignmentId) {
		
		return (assignmentService.getAssignmentDTOById(assignmentId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addAssignment(@RequestBody AssignmentDTO assignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assignmentService.addAssignment(assignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/assignments")
	public ResponseEntity<AssignmentPageDTO> getAssignments(AssignmentSearchDTO assignmentSearchDTO) {
 
		return assignmentService.getAssignments(assignmentSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updateAssignment(@RequestBody AssignmentDTO assignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assignmentService.updateAssignment(assignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
