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

import com.refactortalentscrm.domain.Application;
import com.refactortalentscrm.dto.ApplicationDTO;
import com.refactortalentscrm.dto.ApplicationSearchDTO;
import com.refactortalentscrm.dto.ApplicationPageDTO;
import com.refactortalentscrm.service.ApplicationService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/application")
@RestController
public class ApplicationController {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	ApplicationService applicationService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Application> getAll() {

		List<Application> applications = applicationService.findAll();
		
		return applications;	
	}

	//@ReadAccess
	@GetMapping(value = "/{applicationId}")
	@ResponseBody
	public ApplicationDTO getApplication(@PathVariable Integer applicationId) {
		
		return (applicationService.getApplicationDTOById(applicationId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addApplication", method = RequestMethod.POST)
	public ResponseEntity<?> addApplication(@RequestBody ApplicationDTO applicationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = applicationService.addApplication(applicationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/applications")
	public ResponseEntity<ApplicationPageDTO> getApplications(ApplicationSearchDTO applicationSearchDTO) {
 
		return applicationService.getApplications(applicationSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateApplication", method = RequestMethod.POST)
	public ResponseEntity<?> updateApplication(@RequestBody ApplicationDTO applicationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = applicationService.updateApplication(applicationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
