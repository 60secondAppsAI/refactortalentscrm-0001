package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Application;





public interface ApplicationDAO extends GenericDAO<Application, Integer> {
  
	List<Application> findAll();
	






}


