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
import com.refactortalentscrm.dao.ScheduleDAO;
import com.refactortalentscrm.domain.Schedule;
import com.refactortalentscrm.dto.ScheduleDTO;
import com.refactortalentscrm.dto.ScheduleSearchDTO;
import com.refactortalentscrm.dto.SchedulePageDTO;
import com.refactortalentscrm.dto.ScheduleConvertCriteriaDTO;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import com.refactortalentscrm.service.ScheduleService;
import com.refactortalentscrm.util.ControllerUtils;





@Service
public class ScheduleServiceImpl extends GenericServiceImpl<Schedule, Integer> implements ScheduleService {

    private final static Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

	@Autowired
	ScheduleDAO scheduleDao;

	


	@Override
	public GenericDAO<Schedule, Integer> getDAO() {
		return (GenericDAO<Schedule, Integer>) scheduleDao;
	}
	
	public List<Schedule> findAll () {
		List<Schedule> schedules = scheduleDao.findAll();
		
		return schedules;	
		
	}

	public ResultDTO addSchedule(ScheduleDTO scheduleDTO, RequestDTO requestDTO) {

		Schedule schedule = new Schedule();

		schedule.setScheduleId(scheduleDTO.getScheduleId());


		schedule.setScheduledDate(scheduleDTO.getScheduledDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		schedule = scheduleDao.save(schedule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Schedule> getAllSchedules(Pageable pageable) {
		return scheduleDao.findAll(pageable);
	}

	public Page<Schedule> getAllSchedules(Specification<Schedule> spec, Pageable pageable) {
		return scheduleDao.findAll(spec, pageable);
	}

	public ResponseEntity<SchedulePageDTO> getSchedules(ScheduleSearchDTO scheduleSearchDTO) {
	
			Integer scheduleId = scheduleSearchDTO.getScheduleId(); 
   			String sortBy = scheduleSearchDTO.getSortBy();
			String sortOrder = scheduleSearchDTO.getSortOrder();
			String searchQuery = scheduleSearchDTO.getSearchQuery();
			Integer page = scheduleSearchDTO.getPage();
			Integer size = scheduleSearchDTO.getSize();

	        Specification<Schedule> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, scheduleId, "scheduleId"); 
			
 			

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

		Page<Schedule> schedules = this.getAllSchedules(spec, pageable);
		
		//System.out.println(String.valueOf(schedules.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(schedules.getTotalPages()));
		
		List<Schedule> schedulesList = schedules.getContent();
		
		ScheduleConvertCriteriaDTO convertCriteria = new ScheduleConvertCriteriaDTO();
		List<ScheduleDTO> scheduleDTOs = this.convertSchedulesToScheduleDTOs(schedulesList,convertCriteria);
		
		SchedulePageDTO schedulePageDTO = new SchedulePageDTO();
		schedulePageDTO.setSchedules(scheduleDTOs);
		schedulePageDTO.setTotalElements(schedules.getTotalElements());
		return ResponseEntity.ok(schedulePageDTO);
	}

	public List<ScheduleDTO> convertSchedulesToScheduleDTOs(List<Schedule> schedules, ScheduleConvertCriteriaDTO convertCriteria) {
		
		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
		
		for (Schedule schedule : schedules) {
			scheduleDTOs.add(convertScheduleToScheduleDTO(schedule,convertCriteria));
		}
		
		return scheduleDTOs;

	}
	
	public ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule, ScheduleConvertCriteriaDTO convertCriteria) {
		
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		
		scheduleDTO.setScheduleId(schedule.getScheduleId());

	
		scheduleDTO.setScheduledDate(schedule.getScheduledDate());

	

		
		return scheduleDTO;
	}

	public ResultDTO updateSchedule(ScheduleDTO scheduleDTO, RequestDTO requestDTO) {
		
		Schedule schedule = scheduleDao.getById(scheduleDTO.getScheduleId());

		schedule.setScheduleId(ControllerUtils.setValue(schedule.getScheduleId(), scheduleDTO.getScheduleId()));

		schedule.setScheduledDate(ControllerUtils.setValue(schedule.getScheduledDate(), scheduleDTO.getScheduledDate()));



        schedule = scheduleDao.save(schedule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ScheduleDTO getScheduleDTOById(Integer scheduleId) {
	
		Schedule schedule = scheduleDao.getById(scheduleId);
			
		
		ScheduleConvertCriteriaDTO convertCriteria = new ScheduleConvertCriteriaDTO();
		return(this.convertScheduleToScheduleDTO(schedule,convertCriteria));
	}







}