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
import com.refactortalentscrm.dao.InterviewDAO;
import com.refactortalentscrm.domain.Interview;
import com.refactortalentscrm.dto.InterviewDTO;
import com.refactortalentscrm.dto.InterviewSearchDTO;
import com.refactortalentscrm.dto.InterviewPageDTO;
import com.refactortalentscrm.dto.InterviewConvertCriteriaDTO;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import com.refactortalentscrm.service.InterviewService;
import com.refactortalentscrm.util.ControllerUtils;





@Service
public class InterviewServiceImpl extends GenericServiceImpl<Interview, Integer> implements InterviewService {

    private final static Logger logger = LoggerFactory.getLogger(InterviewServiceImpl.class);

	@Autowired
	InterviewDAO interviewDao;

	


	@Override
	public GenericDAO<Interview, Integer> getDAO() {
		return (GenericDAO<Interview, Integer>) interviewDao;
	}
	
	public List<Interview> findAll () {
		List<Interview> interviews = interviewDao.findAll();
		
		return interviews;	
		
	}

	public ResultDTO addInterview(InterviewDTO interviewDTO, RequestDTO requestDTO) {

		Interview interview = new Interview();

		interview.setInterviewId(interviewDTO.getInterviewId());


		interview.setInterviewDate(interviewDTO.getInterviewDate());


		interview.setInterviewType(interviewDTO.getInterviewType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		interview = interviewDao.save(interview);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Interview> getAllInterviews(Pageable pageable) {
		return interviewDao.findAll(pageable);
	}

	public Page<Interview> getAllInterviews(Specification<Interview> spec, Pageable pageable) {
		return interviewDao.findAll(spec, pageable);
	}

	public ResponseEntity<InterviewPageDTO> getInterviews(InterviewSearchDTO interviewSearchDTO) {
	
			Integer interviewId = interviewSearchDTO.getInterviewId(); 
   			String interviewType = interviewSearchDTO.getInterviewType(); 
 			String sortBy = interviewSearchDTO.getSortBy();
			String sortOrder = interviewSearchDTO.getSortOrder();
			String searchQuery = interviewSearchDTO.getSearchQuery();
			Integer page = interviewSearchDTO.getPage();
			Integer size = interviewSearchDTO.getSize();

	        Specification<Interview> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, interviewId, "interviewId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, interviewType, "interviewType"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("interviewType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Interview> interviews = this.getAllInterviews(spec, pageable);
		
		//System.out.println(String.valueOf(interviews.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(interviews.getTotalPages()));
		
		List<Interview> interviewsList = interviews.getContent();
		
		InterviewConvertCriteriaDTO convertCriteria = new InterviewConvertCriteriaDTO();
		List<InterviewDTO> interviewDTOs = this.convertInterviewsToInterviewDTOs(interviewsList,convertCriteria);
		
		InterviewPageDTO interviewPageDTO = new InterviewPageDTO();
		interviewPageDTO.setInterviews(interviewDTOs);
		interviewPageDTO.setTotalElements(interviews.getTotalElements());
		return ResponseEntity.ok(interviewPageDTO);
	}

	public List<InterviewDTO> convertInterviewsToInterviewDTOs(List<Interview> interviews, InterviewConvertCriteriaDTO convertCriteria) {
		
		List<InterviewDTO> interviewDTOs = new ArrayList<InterviewDTO>();
		
		for (Interview interview : interviews) {
			interviewDTOs.add(convertInterviewToInterviewDTO(interview,convertCriteria));
		}
		
		return interviewDTOs;

	}
	
	public InterviewDTO convertInterviewToInterviewDTO(Interview interview, InterviewConvertCriteriaDTO convertCriteria) {
		
		InterviewDTO interviewDTO = new InterviewDTO();
		
		interviewDTO.setInterviewId(interview.getInterviewId());

	
		interviewDTO.setInterviewDate(interview.getInterviewDate());

	
		interviewDTO.setInterviewType(interview.getInterviewType());

	

		
		return interviewDTO;
	}

	public ResultDTO updateInterview(InterviewDTO interviewDTO, RequestDTO requestDTO) {
		
		Interview interview = interviewDao.getById(interviewDTO.getInterviewId());

		interview.setInterviewId(ControllerUtils.setValue(interview.getInterviewId(), interviewDTO.getInterviewId()));

		interview.setInterviewDate(ControllerUtils.setValue(interview.getInterviewDate(), interviewDTO.getInterviewDate()));

		interview.setInterviewType(ControllerUtils.setValue(interview.getInterviewType(), interviewDTO.getInterviewType()));



        interview = interviewDao.save(interview);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public InterviewDTO getInterviewDTOById(Integer interviewId) {
	
		Interview interview = interviewDao.getById(interviewId);
			
		
		InterviewConvertCriteriaDTO convertCriteria = new InterviewConvertCriteriaDTO();
		return(this.convertInterviewToInterviewDTO(interview,convertCriteria));
	}







}
