package com.refactortalentscrm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.refactortalentscrm.domain.Application;
import com.refactortalentscrm.dto.ApplicationDTO;
import com.refactortalentscrm.dto.ApplicationSearchDTO;
import com.refactortalentscrm.dto.ApplicationPageDTO;
import com.refactortalentscrm.dto.ApplicationConvertCriteriaDTO;
import com.refactortalentscrm.service.GenericService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ApplicationService extends GenericService<Application, Integer> {

	List<Application> findAll();

	ResultDTO addApplication(ApplicationDTO applicationDTO, RequestDTO requestDTO);

	ResultDTO updateApplication(ApplicationDTO applicationDTO, RequestDTO requestDTO);

    Page<Application> getAllApplications(Pageable pageable);

    Page<Application> getAllApplications(Specification<Application> spec, Pageable pageable);

	ResponseEntity<ApplicationPageDTO> getApplications(ApplicationSearchDTO applicationSearchDTO);
	
	List<ApplicationDTO> convertApplicationsToApplicationDTOs(List<Application> applications, ApplicationConvertCriteriaDTO convertCriteria);

	ApplicationDTO getApplicationDTOById(Integer applicationId);







}





