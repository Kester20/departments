package com.aimprosoft.noormal.dao.impl;

import com.aimprosoft.noormal.dao.Dao;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.util.Constants;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Arsalan. Created on 04.06.2017.
 */
@Repository
public abstract class CrudDao<T> implements Dao<T> {

    @Autowired
    protected SessionFactory sessionFactory;
    protected final Class<T> type = initType();

    private Class<T> initType() {
        Class clazz = getClass();
        ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        return (Class<T>) actualTypeArgument;
    }

    @Override
    public void saveEntity(T entity) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(entity);
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_SAVE_ENTITY);
        }
    }

    @Override
    public void deleteEntity(T entity) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(entity);
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_DELETE_ENTITY);
        }
    }

    @Override
    public List<T> findAllEntities(Integer page) throws DaoException {
        String hql = "FROM " + type.getSimpleName();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql).setFirstResult((page - 1) * 5).setMaxResults(5);
            return query.list();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_FIND_ENTITIES);
        }
    }

    @Override
    public T findOne(Long id) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(type, id);
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_FIND_ENTITY);
        }
    }

    @Override
    public T findOneByCriteria(String criteriaName, String criteriaValue) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(type);
            return (T) criteria.add(Restrictions.eq(criteriaName, criteriaValue)).uniqueResult();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_FIND_ENTITY);
        }
    }

    @Override
    public Integer getTotalItems() throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return ((Long) session.createQuery("select count(*) from " + type.getSimpleName()).uniqueResult()).intValue();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_COUNT_ENTITIES);
        }
    }
}
