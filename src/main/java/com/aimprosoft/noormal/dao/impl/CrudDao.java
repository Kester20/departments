package com.aimprosoft.noormal.dao.impl;

import com.aimprosoft.noormal.dao.Dao;
import com.aimprosoft.noormal.dao.HibernateSessionFactory;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.util.Constants;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Arsalan. Created on 04.06.2017.
 */
public abstract class CrudDao<T> implements Dao<T> {

    private final Class<T> type = initType();

    private Class<T> initType() {
        Class clazz = getClass();
        ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        return (Class<T>) actualTypeArgument;
    }

    @Override
    public void saveEntity(T entity) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_SAVE_ENTITY);
        }
    }

    @Override
    public void deleteEntity(T entity) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_DELETE_ENTITY);
        }
    }

    @Override
    public List<T> findAllEntities() throws DaoException {
        String hql = "FROM " + type.getSimpleName();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_FIND_ENTITIES);
        }
    }

    @Override
    public T findOne(Long id) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_FIND_ENTITY);
        }
    }

    @Override
    public T findOneByCriteria(String criteriaName, String criteriaValue) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(type);
            return (T) criteria.add(Restrictions.eq(criteriaName, criteriaValue))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_FIND_ENTITY);
        }
    }
}
