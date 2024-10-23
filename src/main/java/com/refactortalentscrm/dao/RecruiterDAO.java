package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Recruiter;





public interface RecruiterDAO extends GenericDAO<Recruiter, Integer> {
  
	List<Recruiter> findAll();
	






}


