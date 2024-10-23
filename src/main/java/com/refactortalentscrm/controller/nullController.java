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

import com.refactortalentscrm.domain.${className};
import com.refactortalentscrm.dto.${className}DTO;
import com.refactortalentscrm.dto.${className}SearchDTO;
import com.refactortalentscrm.dto.${className}PageDTO;
import com.refactortalentscrm.service.${className}Service;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/${classNameCamel}")
@RestController
public class ${className}Controller {

	private final static Logger logger = LoggerFactory.getLogger(${className}Controller.class);

	@Autowired
	${className}Service ${classNameCamel}Service;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<${className}> getAll() {

		List<${className}> ${classNameCamelPlural} = ${classNameCamel}Service.findAll();
		
		return ${classNameCamelPlural};	
	}

	//@ReadAccess
	@GetMapping(value = "/{${classNameCamel}Id}")
	@ResponseBody
	public ${className}DTO get${className}(@PathVariable Integer ${classNameCamel}Id) {
		
		return (${classNameCamel}Service.get${className}DTOById(${classNameCamel}Id));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/add${className}", method = RequestMethod.POST)
	public ResponseEntity<?> add${className}(@RequestBody ${className}DTO ${classNameCamel}DTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ${classNameCamel}Service.add${className}(${classNameCamel}DTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/${classNameCamelPlural}")
	public ResponseEntity<${className}PageDTO> get${classNamePlural}(${className}SearchDTO ${classNameCamel}SearchDTO) {
 
		return ${classNameCamel}Service.get${classNamePlural}(${classNameCamel}SearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/update${className}", method = RequestMethod.POST)
	public ResponseEntity<?> update${className}(@RequestBody ${className}DTO ${classNameCamel}DTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ${classNameCamel}Service.update${className}(${classNameCamel}DTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
