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

import com.refactortalentscrm.domain.Job;
import com.refactortalentscrm.dto.JobDTO;
import com.refactortalentscrm.dto.JobSearchDTO;
import com.refactortalentscrm.dto.JobPageDTO;
import com.refactortalentscrm.service.JobService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/job")
@RestController
public class JobController {

	private final static Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	JobService jobService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Job> getAll() {

		List<Job> jobs = jobService.findAll();
		
		return jobs;	
	}

	//@ReadAccess
	@GetMapping(value = "/{jobId}")
	@ResponseBody
	public JobDTO getJob(@PathVariable Integer jobId) {
		
		return (jobService.getJobDTOById(jobId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addJob", method = RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody JobDTO jobDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = jobService.addJob(jobDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/jobs")
	public ResponseEntity<JobPageDTO> getJobs(JobSearchDTO jobSearchDTO) {
 
		return jobService.getJobs(jobSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateJob", method = RequestMethod.POST)
	public ResponseEntity<?> updateJob(@RequestBody JobDTO jobDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = jobService.updateJob(jobDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
