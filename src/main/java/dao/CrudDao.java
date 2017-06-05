package dao;

import exception.DaoException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static util.Constants.Messages.CAN_NOT_DELETE_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_FIND_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_GET_DEPARTMENTS;
import static util.Constants.Messages.CAN_NOT_SAVE_DEPARTMENT;

/**
 * @author Arsalan. Created on 04.06.2017.
 */
public abstract class CrudDao<T> {

    private final Class<T> type;

    public CrudDao(Class<T> type) {
        this.type = type;
    }

    public void saveEntity(T entity) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(CAN_NOT_SAVE_DEPARTMENT);
        }
    }

    public void deleteEntity(T entity) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(CAN_NOT_DELETE_DEPARTMENT);
        }
    }

    public List<T> findAllEntities() throws DaoException {
        String hql = "FROM " + type.getSimpleName();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            throw new DaoException(CAN_NOT_GET_DEPARTMENTS);
        }
    }

    public T findOne(Long id) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            throw new DaoException(CAN_NOT_FIND_DEPARTMENT);
        }
    }

    public T findOneByCriteria(String criteriaName, String criteriaValue) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(type);
            return (T) criteria.add(Restrictions.eq(criteriaName, criteriaValue))
                    .uniqueResult();
        }
    }
}
