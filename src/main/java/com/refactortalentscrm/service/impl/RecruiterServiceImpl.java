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
import com.refactortalentscrm.dao.RecruiterDAO;
import com.refactortalentscrm.domain.Recruiter;
import com.refactortalentscrm.dto.RecruiterDTO;
import com.refactortalentscrm.dto.RecruiterSearchDTO;
import com.refactortalentscrm.dto.RecruiterPageDTO;
import com.refactortalentscrm.dto.RecruiterConvertCriteriaDTO;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import com.refactortalentscrm.service.RecruiterService;
import com.refactortalentscrm.util.ControllerUtils;





@Service
public class RecruiterServiceImpl extends GenericServiceImpl<Recruiter, Integer> implements RecruiterService {

    private final static Logger logger = LoggerFactory.getLogger(RecruiterServiceImpl.class);

	@Autowired
	RecruiterDAO recruiterDao;

	


	@Override
	public GenericDAO<Recruiter, Integer> getDAO() {
		return (GenericDAO<Recruiter, Integer>) recruiterDao;
	}
	
	public List<Recruiter> findAll () {
		List<Recruiter> recruiters = recruiterDao.findAll();
		
		return recruiters;	
		
	}

	public ResultDTO addRecruiter(RecruiterDTO recruiterDTO, RequestDTO requestDTO) {

		Recruiter recruiter = new Recruiter();

		recruiter.setRecruiterId(recruiterDTO.getRecruiterId());


		recruiter.setName(recruiterDTO.getName());


		recruiter.setEmail(recruiterDTO.getEmail());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		recruiter = recruiterDao.save(recruiter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Recruiter> getAllRecruiters(Pageable pageable) {
		return recruiterDao.findAll(pageable);
	}

	public Page<Recruiter> getAllRecruiters(Specification<Recruiter> spec, Pageable pageable) {
		return recruiterDao.findAll(spec, pageable);
	}

	public ResponseEntity<RecruiterPageDTO> getRecruiters(RecruiterSearchDTO recruiterSearchDTO) {
	
			Integer recruiterId = recruiterSearchDTO.getRecruiterId(); 
 			String name = recruiterSearchDTO.getName(); 
 			String email = recruiterSearchDTO.getEmail(); 
 			String sortBy = recruiterSearchDTO.getSortBy();
			String sortOrder = recruiterSearchDTO.getSortOrder();
			String searchQuery = recruiterSearchDTO.getSearchQuery();
			Integer page = recruiterSearchDTO.getPage();
			Integer size = recruiterSearchDTO.getSize();

	        Specification<Recruiter> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, recruiterId, "recruiterId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Recruiter> recruiters = this.getAllRecruiters(spec, pageable);
		
		//System.out.println(String.valueOf(recruiters.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(recruiters.getTotalPages()));
		
		List<Recruiter> recruitersList = recruiters.getContent();
		
		RecruiterConvertCriteriaDTO convertCriteria = new RecruiterConvertCriteriaDTO();
		List<RecruiterDTO> recruiterDTOs = this.convertRecruitersToRecruiterDTOs(recruitersList,convertCriteria);
		
		RecruiterPageDTO recruiterPageDTO = new RecruiterPageDTO();
		recruiterPageDTO.setRecruiters(recruiterDTOs);
		recruiterPageDTO.setTotalElements(recruiters.getTotalElements());
		return ResponseEntity.ok(recruiterPageDTO);
	}

	public List<RecruiterDTO> convertRecruitersToRecruiterDTOs(List<Recruiter> recruiters, RecruiterConvertCriteriaDTO convertCriteria) {
		
		List<RecruiterDTO> recruiterDTOs = new ArrayList<RecruiterDTO>();
		
		for (Recruiter recruiter : recruiters) {
			recruiterDTOs.add(convertRecruiterToRecruiterDTO(recruiter,convertCriteria));
		}
		
		return recruiterDTOs;

	}
	
	public RecruiterDTO convertRecruiterToRecruiterDTO(Recruiter recruiter, RecruiterConvertCriteriaDTO convertCriteria) {
		
		RecruiterDTO recruiterDTO = new RecruiterDTO();
		
		recruiterDTO.setRecruiterId(recruiter.getRecruiterId());

	
		recruiterDTO.setName(recruiter.getName());

	
		recruiterDTO.setEmail(recruiter.getEmail());

	

		
		return recruiterDTO;
	}

	public ResultDTO updateRecruiter(RecruiterDTO recruiterDTO, RequestDTO requestDTO) {
		
		Recruiter recruiter = recruiterDao.getById(recruiterDTO.getRecruiterId());

		recruiter.setRecruiterId(ControllerUtils.setValue(recruiter.getRecruiterId(), recruiterDTO.getRecruiterId()));

		recruiter.setName(ControllerUtils.setValue(recruiter.getName(), recruiterDTO.getName()));

		recruiter.setEmail(ControllerUtils.setValue(recruiter.getEmail(), recruiterDTO.getEmail()));



        recruiter = recruiterDao.save(recruiter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RecruiterDTO getRecruiterDTOById(Integer recruiterId) {
	
		Recruiter recruiter = recruiterDao.getById(recruiterId);
			
		
		RecruiterConvertCriteriaDTO convertCriteria = new RecruiterConvertCriteriaDTO();
		return(this.convertRecruiterToRecruiterDTO(recruiter,convertCriteria));
	}







}
