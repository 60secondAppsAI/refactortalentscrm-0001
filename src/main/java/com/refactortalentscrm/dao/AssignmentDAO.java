package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Assignment;





public interface AssignmentDAO extends GenericDAO<Assignment, Integer> {
  
	List<Assignment> findAll();
	






}


