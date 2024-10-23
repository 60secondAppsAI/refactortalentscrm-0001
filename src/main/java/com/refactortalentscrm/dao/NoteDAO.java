package com.refactortalentscrm.dao;

import java.util.List;

import com.refactortalentscrm.dao.GenericDAO;
import com.refactortalentscrm.domain.Note;





public interface NoteDAO extends GenericDAO<Note, Integer> {
  
	List<Note> findAll();
	






}


