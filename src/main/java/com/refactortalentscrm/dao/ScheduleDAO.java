package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Schedule;





public interface ScheduleDAO extends GenericDAO<Schedule, Integer> {
  
	List<Schedule> findAll();
	






}


