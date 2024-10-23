package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Job;





public interface JobDAO extends GenericDAO<Job, Integer> {
  
	List<Job> findAll();
	






}


