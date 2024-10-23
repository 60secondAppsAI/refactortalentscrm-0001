package com.refactortalentscrm.service;

import com.refactortalentscrm.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}