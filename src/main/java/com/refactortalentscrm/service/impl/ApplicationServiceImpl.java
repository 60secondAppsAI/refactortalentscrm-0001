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
import com.refactortalentscrm.dao.ApplicationDAO;
import com.refactortalentscrm.domain.Application;
import com.refactortalentscrm.dto.ApplicationDTO;
import com.refactortalentscrm.dto.ApplicationSearchDTO;
import com.refactortalentscrm.dto.ApplicationPageDTO;
import com.refactortalentscrm.dto.ApplicationConvertCriteriaDTO;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import com.refactortalentscrm.service.ApplicationService;
import com.refactortalentscrm.util.ControllerUtils;





@Service
public class ApplicationServiceImpl extends GenericServiceImpl<Application, Integer> implements ApplicationService {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	@Autowired
	ApplicationDAO applicationDao;

	


	@Override
	public GenericDAO<Application, Integer> getDAO() {
		return (GenericDAO<Application, Integer>) applicationDao;
	}
	
	public List<Application> findAll () {
		List<Application> applications = applicationDao.findAll();
		
		return applications;	
		
	}

	public ResultDTO addApplication(ApplicationDTO applicationDTO, RequestDTO requestDTO) {

		Application application = new Application();

		application.setApplicationId(applicationDTO.getApplicationId());


		application.setApplicationDate(applicationDTO.getApplicationDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		application = applicationDao.save(application);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Application> getAllApplications(Pageable pageable) {
		return applicationDao.findAll(pageable);
	}

	public Page<Application> getAllApplications(Specification<Application> spec, Pageable pageable) {
		return applicationDao.findAll(spec, pageable);
	}

	public ResponseEntity<ApplicationPageDTO> getApplications(ApplicationSearchDTO applicationSearchDTO) {
	
			Integer applicationId = applicationSearchDTO.getApplicationId(); 
   			String sortBy = applicationSearchDTO.getSortBy();
			String sortOrder = applicationSearchDTO.getSortOrder();
			String searchQuery = applicationSearchDTO.getSearchQuery();
			Integer page = applicationSearchDTO.getPage();
			Integer size = applicationSearchDTO.getSize();

	        Specification<Application> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, applicationId, "applicationId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<Application> applications = this.getAllApplications(spec, pageable);
		
		//System.out.println(String.valueOf(applications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(applications.getTotalPages()));
		
		List<Application> applicationsList = applications.getContent();
		
		ApplicationConvertCriteriaDTO convertCriteria = new ApplicationConvertCriteriaDTO();
		List<ApplicationDTO> applicationDTOs = this.convertApplicationsToApplicationDTOs(applicationsList,convertCriteria);
		
		ApplicationPageDTO applicationPageDTO = new ApplicationPageDTO();
		applicationPageDTO.setApplications(applicationDTOs);
		applicationPageDTO.setTotalElements(applications.getTotalElements());
		return ResponseEntity.ok(applicationPageDTO);
	}

	public List<ApplicationDTO> convertApplicationsToApplicationDTOs(List<Application> applications, ApplicationConvertCriteriaDTO convertCriteria) {
		
		List<ApplicationDTO> applicationDTOs = new ArrayList<ApplicationDTO>();
		
		for (Application application : applications) {
			applicationDTOs.add(convertApplicationToApplicationDTO(application,convertCriteria));
		}
		
		return applicationDTOs;

	}
	
	public ApplicationDTO convertApplicationToApplicationDTO(Application application, ApplicationConvertCriteriaDTO convertCriteria) {
		
		ApplicationDTO applicationDTO = new ApplicationDTO();
		
		applicationDTO.setApplicationId(application.getApplicationId());

	
		applicationDTO.setApplicationDate(application.getApplicationDate());

	

		
		return applicationDTO;
	}

	public ResultDTO updateApplication(ApplicationDTO applicationDTO, RequestDTO requestDTO) {
		
		Application application = applicationDao.getById(applicationDTO.getApplicationId());

		application.setApplicationId(ControllerUtils.setValue(application.getApplicationId(), applicationDTO.getApplicationId()));

		application.setApplicationDate(ControllerUtils.setValue(application.getApplicationDate(), applicationDTO.getApplicationDate()));



        application = applicationDao.save(application);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ApplicationDTO getApplicationDTOById(Integer applicationId) {
	
		Application application = applicationDao.getById(applicationId);
			
		
		ApplicationConvertCriteriaDTO convertCriteria = new ApplicationConvertCriteriaDTO();
		return(this.convertApplicationToApplicationDTO(application,convertCriteria));
	}







}
