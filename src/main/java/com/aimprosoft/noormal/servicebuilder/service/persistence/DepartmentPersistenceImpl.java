package com.aimprosoft.noormal.servicebuilder.service.persistence;

import com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException;
import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentImpl;
import com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentModelImpl;
import com.aimprosoft.noormal.servicebuilder.service.persistence.DepartmentPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the department service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Arsalan
 * @see DepartmentPersistence
 * @see DepartmentUtil
 * @generated
 */
public class DepartmentPersistenceImpl extends BasePersistenceImpl<Department>
    implements DepartmentPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DepartmentUtil} to access the department persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DepartmentImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentModelImpl.FINDER_CACHE_ENABLED, DepartmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentModelImpl.FINDER_CACHE_ENABLED, DepartmentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentModelImpl.FINDER_CACHE_ENABLED, DepartmentImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByName",
            new String[] { String.class.getName() },
            DepartmentModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_NAME_NAME_1 = "department.name IS NULL";
    private static final String _FINDER_COLUMN_NAME_NAME_2 = "department.name = ?";
    private static final String _FINDER_COLUMN_NAME_NAME_3 = "(department.name IS NULL OR department.name = '')";
    private static final String _SQL_SELECT_DEPARTMENT = "SELECT department FROM Department department";
    private static final String _SQL_SELECT_DEPARTMENT_WHERE = "SELECT department FROM Department department WHERE ";
    private static final String _SQL_COUNT_DEPARTMENT = "SELECT COUNT(department) FROM Department department";
    private static final String _SQL_COUNT_DEPARTMENT_WHERE = "SELECT COUNT(department) FROM Department department WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "department.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Department exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Department exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DepartmentPersistenceImpl.class);
    private static Department _nullDepartment = new DepartmentImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Department> toCacheModel() {
                return _nullDepartmentCacheModel;
            }
        };

    private static CacheModel<Department> _nullDepartmentCacheModel = new CacheModel<Department>() {
            @Override
            public Department toEntityModel() {
                return _nullDepartment;
            }
        };

    public DepartmentPersistenceImpl() {
        setModelClass(Department.class);
    }

    /**
     * Returns the department where name = &#63; or throws a {@link com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException} if it could not be found.
     *
     * @param name the name
     * @return the matching department
     * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a matching department could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department findByName(String name)
        throws NoSuchDepartmentException, SystemException {
        Department department = fetchByName(name);

        if (department == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDepartmentException(msg.toString());
        }

        return department;
    }

    /**
     * Returns the department where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param name the name
     * @return the matching department, or <code>null</code> if a matching department could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department fetchByName(String name) throws SystemException {
        return fetchByName(name, true);
    }

    /**
     * Returns the department where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param name the name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching department, or <code>null</code> if a matching department could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department fetchByName(String name, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs, this);
        }

        if (result instanceof Department) {
            Department department = (Department) result;

            if (!Validator.equals(name, department.getName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_DEPARTMENT_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_NAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_NAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
                }

                List<Department> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "DepartmentPersistenceImpl.fetchByName(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    Department department = list.get(0);

                    result = department;

                    cacheResult(department);

                    if ((department.getName() == null) ||
                            !department.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                            finderArgs, department);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (Department) result;
        }
    }

    /**
     * Removes the department where name = &#63; from the database.
     *
     * @param name the name
     * @return the department that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department removeByName(String name)
        throws NoSuchDepartmentException, SystemException {
        Department department = findByName(name);

        return remove(department);
    }

    /**
     * Returns the number of departments where name = &#63;.
     *
     * @param name the name
     * @return the number of matching departments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByName(String name) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_DEPARTMENT_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_NAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_NAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_NAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the department in the entity cache if it is enabled.
     *
     * @param department the department
     */
    @Override
    public void cacheResult(Department department) {
        EntityCacheUtil.putResult(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentImpl.class, department.getPrimaryKey(), department);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { department.getName() }, department);

        department.resetOriginalValues();
    }

    /**
     * Caches the departments in the entity cache if it is enabled.
     *
     * @param departments the departments
     */
    @Override
    public void cacheResult(List<Department> departments) {
        for (Department department : departments) {
            if (EntityCacheUtil.getResult(
                        DepartmentModelImpl.ENTITY_CACHE_ENABLED,
                        DepartmentImpl.class, department.getPrimaryKey()) == null) {
                cacheResult(department);
            } else {
                department.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all departments.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DepartmentImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DepartmentImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the department.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Department department) {
        EntityCacheUtil.removeResult(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentImpl.class, department.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(department);
    }

    @Override
    public void clearCache(List<Department> departments) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Department department : departments) {
            EntityCacheUtil.removeResult(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
                DepartmentImpl.class, department.getPrimaryKey());

            clearUniqueFindersCache(department);
        }
    }

    protected void cacheUniqueFindersCache(Department department) {
        if (department.isNew()) {
            Object[] args = new Object[] { department.getName() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
                department);
        } else {
            DepartmentModelImpl departmentModelImpl = (DepartmentModelImpl) department;

            if ((departmentModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { department.getName() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
                    Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
                    department);
            }
        }
    }

    protected void clearUniqueFindersCache(Department department) {
        DepartmentModelImpl departmentModelImpl = (DepartmentModelImpl) department;

        Object[] args = new Object[] { department.getName() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

        if ((departmentModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
            args = new Object[] { departmentModelImpl.getOriginalName() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
        }
    }

    /**
     * Creates a new department with the primary key. Does not add the department to the database.
     *
     * @param departmentId the primary key for the new department
     * @return the new department
     */
    @Override
    public Department create(long departmentId) {
        Department department = new DepartmentImpl();

        department.setNew(true);
        department.setPrimaryKey(departmentId);

        return department;
    }

    /**
     * Removes the department with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param departmentId the primary key of the department
     * @return the department that was removed
     * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department remove(long departmentId)
        throws NoSuchDepartmentException, SystemException {
        return remove((Serializable) departmentId);
    }

    /**
     * Removes the department with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the department
     * @return the department that was removed
     * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department remove(Serializable primaryKey)
        throws NoSuchDepartmentException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Department department = (Department) session.get(DepartmentImpl.class,
                    primaryKey);

            if (department == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDepartmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(department);
        } catch (NoSuchDepartmentException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Department removeImpl(Department department)
        throws SystemException {
        department = toUnwrappedModel(department);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(department)) {
                department = (Department) session.get(DepartmentImpl.class,
                        department.getPrimaryKeyObj());
            }

            if (department != null) {
                session.delete(department);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (department != null) {
            clearCache(department);
        }

        return department;
    }

    @Override
    public Department updateImpl(
        com.aimprosoft.noormal.servicebuilder.model.Department department)
        throws SystemException {
        department = toUnwrappedModel(department);

        boolean isNew = department.isNew();

        Session session = null;

        try {
            session = openSession();

            if (department.isNew()) {
                session.save(department);

                department.setNew(false);
            } else {
                session.merge(department);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !DepartmentModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
            DepartmentImpl.class, department.getPrimaryKey(), department);

        clearUniqueFindersCache(department);
        cacheUniqueFindersCache(department);

        return department;
    }

    protected Department toUnwrappedModel(Department department) {
        if (department instanceof DepartmentImpl) {
            return department;
        }

        DepartmentImpl departmentImpl = new DepartmentImpl();

        departmentImpl.setNew(department.isNew());
        departmentImpl.setPrimaryKey(department.getPrimaryKey());

        departmentImpl.setDepartmentId(department.getDepartmentId());
        departmentImpl.setName(department.getName());

        return departmentImpl;
    }

    /**
     * Returns the department with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the department
     * @return the department
     * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDepartmentException, SystemException {
        Department department = fetchByPrimaryKey(primaryKey);

        if (department == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDepartmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return department;
    }

    /**
     * Returns the department with the primary key or throws a {@link com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException} if it could not be found.
     *
     * @param departmentId the primary key of the department
     * @return the department
     * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department findByPrimaryKey(long departmentId)
        throws NoSuchDepartmentException, SystemException {
        return findByPrimaryKey((Serializable) departmentId);
    }

    /**
     * Returns the department with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the department
     * @return the department, or <code>null</code> if a department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Department department = (Department) EntityCacheUtil.getResult(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
                DepartmentImpl.class, primaryKey);

        if (department == _nullDepartment) {
            return null;
        }

        if (department == null) {
            Session session = null;

            try {
                session = openSession();

                department = (Department) session.get(DepartmentImpl.class,
                        primaryKey);

                if (department != null) {
                    cacheResult(department);
                } else {
                    EntityCacheUtil.putResult(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
                        DepartmentImpl.class, primaryKey, _nullDepartment);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DepartmentModelImpl.ENTITY_CACHE_ENABLED,
                    DepartmentImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return department;
    }

    /**
     * Returns the department with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param departmentId the primary key of the department
     * @return the department, or <code>null</code> if a department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Department fetchByPrimaryKey(long departmentId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) departmentId);
    }

    /**
     * Returns all the departments.
     *
     * @return the departments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Department> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the departments.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of departments
     * @param end the upper bound of the range of departments (not inclusive)
     * @return the range of departments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Department> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the departments.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of departments
     * @param end the upper bound of the range of departments (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of departments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Department> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<Department> list = (List<Department>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DEPARTMENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DEPARTMENT;

                if (pagination) {
                    sql = sql.concat(DepartmentModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Department>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Department>(list);
                } else {
                    list = (List<Department>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the departments from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Department department : findAll()) {
            remove(department);
        }
    }

    /**
     * Returns the number of departments.
     *
     * @return the number of departments
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_DEPARTMENT);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the department persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.aimprosoft.noormal.servicebuilder.model.Department")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Department>> listenersList = new ArrayList<ModelListener<Department>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Department>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DepartmentImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
