package ua.aimprosoft.noormal.dao;

import ua.aimprosoft.noormal.exception.DaoException;

import java.util.List;

/**
 * @author Arsalan
 */
public interface Dao<T>  {

    void saveEntity(T entity) throws DaoException;

    void deleteEntity(T entity) throws DaoException;

    List<T> findAllEntities() throws DaoException;

    T findOne(Long id) throws DaoException;

    T findOneByCriteria(String criteriaName, String criteriaValue) throws DaoException;

}
