package com.aimprosoft.noormal.dao;

import com.aimprosoft.noormal.exception.DaoException;

import java.util.List;

/**
 * @author Arsalan
 */
public interface Dao<T> {

    void saveEntity(T entity) throws DaoException;

    void deleteEntity(T entity) throws DaoException;

    List<T> findAllEntities(Integer page, Integer itemsPerPage) throws DaoException;

    T findOne(Long id) throws DaoException;

    T findOneByCriteria(String criteriaName, String criteriaValue) throws DaoException;

    Integer getTotalItems() throws DaoException;
}
