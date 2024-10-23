package com.refactortalentscrm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.refactortalentscrm.domain.${className};
import com.refactortalentscrm.dto.${className}DTO;
import com.refactortalentscrm.dto.${className}SearchDTO;
import com.refactortalentscrm.dto.${className}PageDTO;
import com.refactortalentscrm.dto.${className}ConvertCriteriaDTO;
import com.refactortalentscrm.service.GenericService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ${className}Service extends GenericService<${className}, Integer> {

	List<${className}> findAll();

	ResultDTO add${className}(${className}DTO ${classNameCamel}DTO, RequestDTO requestDTO);

	ResultDTO update${className}(${className}DTO ${classNameCamel}DTO, RequestDTO requestDTO);

    Page<${className}> getAll${classNamePlural}(Pageable pageable);

    Page<${className}> getAll${classNamePlural}(Specification<${className}> spec, Pageable pageable);

	ResponseEntity<${className}PageDTO> get${classNamePlural}(${className}SearchDTO ${classNameCamel}SearchDTO);
	
	List<${className}DTO> convert${classNamePlural}To${className}DTOs(List<${className}> ${classNameCamelPlural}, ${className}ConvertCriteriaDTO convertCriteria);

	${className}DTO get${className}DTOById(Integer ${classNameCamel}Id);







}





