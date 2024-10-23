package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Candidate;





public interface CandidateDAO extends GenericDAO<Candidate, Integer> {
  
	List<Candidate> findAll();
	






}


