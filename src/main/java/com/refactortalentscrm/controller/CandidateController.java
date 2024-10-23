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

import com.refactortalentscrm.domain.Candidate;
import com.refactortalentscrm.dto.CandidateDTO;
import com.refactortalentscrm.dto.CandidateSearchDTO;
import com.refactortalentscrm.dto.CandidatePageDTO;
import com.refactortalentscrm.service.CandidateService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/candidate")
@RestController
public class CandidateController {

	private final static Logger logger = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	CandidateService candidateService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Candidate> getAll() {

		List<Candidate> candidates = candidateService.findAll();
		
		return candidates;	
	}

	//@ReadAccess
	@GetMapping(value = "/{candidateId}")
	@ResponseBody
	public CandidateDTO getCandidate(@PathVariable Integer candidateId) {
		
		return (candidateService.getCandidateDTOById(candidateId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addCandidate", method = RequestMethod.POST)
	public ResponseEntity<?> addCandidate(@RequestBody CandidateDTO candidateDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = candidateService.addCandidate(candidateDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/candidates")
	public ResponseEntity<CandidatePageDTO> getCandidates(CandidateSearchDTO candidateSearchDTO) {
 
		return candidateService.getCandidates(candidateSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateCandidate", method = RequestMethod.POST)
	public ResponseEntity<?> updateCandidate(@RequestBody CandidateDTO candidateDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = candidateService.updateCandidate(candidateDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
