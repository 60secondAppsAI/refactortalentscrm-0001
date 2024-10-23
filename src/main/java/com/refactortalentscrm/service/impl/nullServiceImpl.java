package com.refactortalentscrm.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.service.GenericService;
import com.refactortalentscrm.service.impl.GenericServiceImpl;
import com.refactortalentscrm.dao.${className}DAO;
import com.refactortalentscrm.domain.${className};
import com.refactortalentscrm.dto.${className}DTO;
import com.refactortalentscrm.dto.${className}SearchDTO;
import com.refactortalentscrm.dto.${className}PageDTO;
import com.refactortalentscrm.dto.${className}ConvertCriteriaDTO;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import com.refactortalentscrm.service.${className}Service;
import com.refactortalentscrm.util.ControllerUtils;





@Service
public class ${className}ServiceImpl extends GenericServiceImpl<${className}, Integer> implements ${className}Service {

    private final static Logger logger = LoggerFactory.getLogger(${className}ServiceImpl.class);

	@Autowired
	${className}DAO ${classNameCamel}Dao;

	


	@Override
	public GenericDAO<${className}, Integer> getDAO() {
		return (GenericDAO<${className}, Integer>) ${classNameCamel}Dao;
	}
	
	public List<${className}> findAll () {
		List<${className}> ${classNameCamelPlural} = ${classNameCamel}Dao.findAll();
		
		return ${classNameCamelPlural};	
		
	}

	public ResultDTO add${className}(${className}DTO ${classNameCamel}DTO, RequestDTO requestDTO) {

		${className} ${classNameCamel} = new ${className}();

		${classNameCamel}.set${field.name.pascal}(${classNameCamel}DTO.get${field.name.pascal}());


		${classNameCamel}.set${field.name.pascal}(${classNameCamel}DTO.get${field.name.pascal}());


		${classNameCamel}.set${field.name.pascal}(${classNameCamel}DTO.get${field.name.pascal}());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		${classNameCamel} = ${classNameCamel}Dao.save(${classNameCamel});
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<${className}> getAll${classNamePlural}(Pageable pageable) {
		return ${classNameCamel}Dao.findAll(pageable);
	}

	public Page<${className}> getAll${classNamePlural}(Specification<${className}> spec, Pageable pageable) {
		return ${classNameCamel}Dao.findAll(spec, pageable);
	}

	public ResponseEntity<${className}PageDTO> get${classNamePlural}(${className}SearchDTO ${classNameCamel}SearchDTO) {
	
			Integer $field.name.camel = ${classNameCamel}SearchDTO.get${field.name.pascal}(); 
 			String $field.name.camel = ${classNameCamel}SearchDTO.get${field.name.pascal}(); 
   			String sortBy = ${classNameCamel}SearchDTO.getSortBy();
			String sortOrder = ${classNameCamel}SearchDTO.getSortOrder();
			String searchQuery = ${classNameCamel}SearchDTO.getSearchQuery();
			Integer page = ${classNameCamel}SearchDTO.getPage();
			Integer size = ${classNameCamel}SearchDTO.getSize();

	        Specification<${className}> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, $field.name.camel, "$field.name.camel"); 
			
			spec = ControllerUtils.andIfNecessary(spec, $field.name.camel, "$field.name.camel"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("$field.name.camel")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<${className}> ${classNameCamelPlural} = this.getAll${classNamePlural}(spec, pageable);
		
		//System.out.println(String.valueOf(${classNameCamelPlural}.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(${classNameCamelPlural}.getTotalPages()));
		
		List<${className}> ${classNameCamelPlural}List = ${classNameCamelPlural}.getContent();
		
		${className}ConvertCriteriaDTO convertCriteria = new ${className}ConvertCriteriaDTO();
		List<${className}DTO> ${classNameCamel}DTOs = this.convert${classNamePlural}To${className}DTOs(${classNameCamelPlural}List,convertCriteria);
		
		${className}PageDTO ${classNameCamel}PageDTO = new ${className}PageDTO();
		${classNameCamel}PageDTO.set${classNamePlural}(${classNameCamel}DTOs);
		${classNameCamel}PageDTO.setTotalElements(${classNameCamelPlural}.getTotalElements());
		return ResponseEntity.ok(${classNameCamel}PageDTO);
	}

	public List<${className}DTO> convert${classNamePlural}To${className}DTOs(List<${className}> ${classNameCamelPlural}, ${className}ConvertCriteriaDTO convertCriteria) {
		
		List<${className}DTO> ${classNameCamel}DTOs = new ArrayList<${className}DTO>();
		
		for (${className} ${classNameCamel} : ${classNameCamelPlural}) {
			${classNameCamel}DTOs.add(convert${className}To${className}DTO(${classNameCamel},convertCriteria));
		}
		
		return ${classNameCamel}DTOs;

	}
	
	public ${className}DTO convert${className}To${className}DTO(${className} ${classNameCamel}, ${className}ConvertCriteriaDTO convertCriteria) {
		
		${className}DTO ${classNameCamel}DTO = new ${className}DTO();
		
		${classNameCamel}DTO.set${field.name.pascal}(${classNameCamel}.get${field.name.pascal}());

	
		${classNameCamel}DTO.set${field.name.pascal}(${classNameCamel}.get${field.name.pascal}());

	
		${classNameCamel}DTO.set${field.name.pascal}(${classNameCamel}.get${field.name.pascal}());

	

		
		return ${classNameCamel}DTO;
	}

	public ResultDTO update${className}(${className}DTO ${classNameCamel}DTO, RequestDTO requestDTO) {
		
		${className} ${classNameCamel} = ${classNameCamel}Dao.getById(${classNameCamel}DTO.get${className}Id());

		${classNameCamel}.set${field.name.pascal}(ControllerUtils.setValue(${classNameCamel}.get${field.name.pascal}(), ${classNameCamel}DTO.get${field.name.pascal}()));

		${classNameCamel}.set${field.name.pascal}(ControllerUtils.setValue(${classNameCamel}.get${field.name.pascal}(), ${classNameCamel}DTO.get${field.name.pascal}()));

		${classNameCamel}.set${field.name.pascal}(ControllerUtils.setValue(${classNameCamel}.get${field.name.pascal}(), ${classNameCamel}DTO.get${field.name.pascal}()));



        ${classNameCamel} = ${classNameCamel}Dao.save(${classNameCamel});
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ${className}DTO get${className}DTOById(Integer ${classNameCamel}Id) {
	
		${className} ${classNameCamel} = ${classNameCamel}Dao.getById(${classNameCamel}Id);
			
		
		${className}ConvertCriteriaDTO convertCriteria = new ${className}ConvertCriteriaDTO();
		return(this.convert${className}To${className}DTO(${classNameCamel},convertCriteria));
	}







}
