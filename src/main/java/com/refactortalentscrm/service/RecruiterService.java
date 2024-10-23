package com.refactortalentscrm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.refactortalentscrm.domain.Recruiter;
import com.refactortalentscrm.dto.RecruiterDTO;
import com.refactortalentscrm.dto.RecruiterSearchDTO;
import com.refactortalentscrm.dto.RecruiterPageDTO;
import com.refactortalentscrm.dto.RecruiterConvertCriteriaDTO;
import com.refactortalentscrm.service.GenericService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RecruiterService extends GenericService<Recruiter, Integer> {

	List<Recruiter> findAll();

	ResultDTO addRecruiter(RecruiterDTO recruiterDTO, RequestDTO requestDTO);

	ResultDTO updateRecruiter(RecruiterDTO recruiterDTO, RequestDTO requestDTO);

    Page<Recruiter> getAllRecruiters(Pageable pageable);

    Page<Recruiter> getAllRecruiters(Specification<Recruiter> spec, Pageable pageable);

	ResponseEntity<RecruiterPageDTO> getRecruiters(RecruiterSearchDTO recruiterSearchDTO);
	
	List<RecruiterDTO> convertRecruitersToRecruiterDTOs(List<Recruiter> recruiters, RecruiterConvertCriteriaDTO convertCriteria);

	RecruiterDTO getRecruiterDTOById(Integer recruiterId);







}





