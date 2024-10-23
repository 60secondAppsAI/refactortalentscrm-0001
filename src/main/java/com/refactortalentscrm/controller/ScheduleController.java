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

import com.refactortalentscrm.domain.Schedule;
import com.refactortalentscrm.dto.ScheduleDTO;
import com.refactortalentscrm.dto.ScheduleSearchDTO;
import com.refactortalentscrm.dto.SchedulePageDTO;
import com.refactortalentscrm.service.ScheduleService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/schedule")
@RestController
public class ScheduleController {

	private final static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	ScheduleService scheduleService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Schedule> getAll() {

		List<Schedule> schedules = scheduleService.findAll();
		
		return schedules;	
	}

	//@ReadAccess
	@GetMapping(value = "/{scheduleId}")
	@ResponseBody
	public ScheduleDTO getSchedule(@PathVariable Integer scheduleId) {
		
		return (scheduleService.getScheduleDTOById(scheduleId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
	public ResponseEntity<?> addSchedule(@RequestBody ScheduleDTO scheduleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = scheduleService.addSchedule(scheduleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/schedules")
	public ResponseEntity<SchedulePageDTO> getSchedules(ScheduleSearchDTO scheduleSearchDTO) {
 
		return scheduleService.getSchedules(scheduleSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
	public ResponseEntity<?> updateSchedule(@RequestBody ScheduleDTO scheduleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = scheduleService.updateSchedule(scheduleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
