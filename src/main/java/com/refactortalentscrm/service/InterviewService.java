package com.refactortalentscrm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.refactortalentscrm.domain.Interview;
import com.refactortalentscrm.dto.InterviewDTO;
import com.refactortalentscrm.dto.InterviewSearchDTO;
import com.refactortalentscrm.dto.InterviewPageDTO;
import com.refactortalentscrm.dto.InterviewConvertCriteriaDTO;
import com.refactortalentscrm.service.GenericService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InterviewService extends GenericService<Interview, Integer> {

	List<Interview> findAll();

	ResultDTO addInterview(InterviewDTO interviewDTO, RequestDTO requestDTO);

	ResultDTO updateInterview(InterviewDTO interviewDTO, RequestDTO requestDTO);

    Page<Interview> getAllInterviews(Pageable pageable);

    Page<Interview> getAllInterviews(Specification<Interview> spec, Pageable pageable);

	ResponseEntity<InterviewPageDTO> getInterviews(InterviewSearchDTO interviewSearchDTO);
	
	List<InterviewDTO> convertInterviewsToInterviewDTOs(List<Interview> interviews, InterviewConvertCriteriaDTO convertCriteria);

	InterviewDTO getInterviewDTOById(Integer interviewId);







}





