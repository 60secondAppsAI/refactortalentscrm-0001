package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Interview;





public interface InterviewDAO extends GenericDAO<Interview, Integer> {
  
	List<Interview> findAll();
	






}


