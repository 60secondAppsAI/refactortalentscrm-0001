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

import com.refactortalentscrm.domain.Interview;
import com.refactortalentscrm.dto.InterviewDTO;
import com.refactortalentscrm.dto.InterviewSearchDTO;
import com.refactortalentscrm.dto.InterviewPageDTO;
import com.refactortalentscrm.service.InterviewService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/interview")
@RestController
public class InterviewController {

	private final static Logger logger = LoggerFactory.getLogger(InterviewController.class);

	@Autowired
	InterviewService interviewService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Interview> getAll() {

		List<Interview> interviews = interviewService.findAll();
		
		return interviews;	
	}

	//@ReadAccess
	@GetMapping(value = "/{interviewId}")
	@ResponseBody
	public InterviewDTO getInterview(@PathVariable Integer interviewId) {
		
		return (interviewService.getInterviewDTOById(interviewId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addInterview", method = RequestMethod.POST)
	public ResponseEntity<?> addInterview(@RequestBody InterviewDTO interviewDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = interviewService.addInterview(interviewDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/interviews")
	public ResponseEntity<InterviewPageDTO> getInterviews(InterviewSearchDTO interviewSearchDTO) {
 
		return interviewService.getInterviews(interviewSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateInterview", method = RequestMethod.POST)
	public ResponseEntity<?> updateInterview(@RequestBody InterviewDTO interviewDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = interviewService.updateInterview(interviewDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
