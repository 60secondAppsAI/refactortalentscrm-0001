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

import com.refactortalentscrm.domain.Recruiter;
import com.refactortalentscrm.dto.RecruiterDTO;
import com.refactortalentscrm.dto.RecruiterSearchDTO;
import com.refactortalentscrm.dto.RecruiterPageDTO;
import com.refactortalentscrm.service.RecruiterService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/recruiter")
@RestController
public class RecruiterController {

	private final static Logger logger = LoggerFactory.getLogger(RecruiterController.class);

	@Autowired
	RecruiterService recruiterService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Recruiter> getAll() {

		List<Recruiter> recruiters = recruiterService.findAll();
		
		return recruiters;	
	}

	//@ReadAccess
	@GetMapping(value = "/{recruiterId}")
	@ResponseBody
	public RecruiterDTO getRecruiter(@PathVariable Integer recruiterId) {
		
		return (recruiterService.getRecruiterDTOById(recruiterId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addRecruiter", method = RequestMethod.POST)
	public ResponseEntity<?> addRecruiter(@RequestBody RecruiterDTO recruiterDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = recruiterService.addRecruiter(recruiterDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/recruiters")
	public ResponseEntity<RecruiterPageDTO> getRecruiters(RecruiterSearchDTO recruiterSearchDTO) {
 
		return recruiterService.getRecruiters(recruiterSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateRecruiter", method = RequestMethod.POST)
	public ResponseEntity<?> updateRecruiter(@RequestBody RecruiterDTO recruiterDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = recruiterService.updateRecruiter(recruiterDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
