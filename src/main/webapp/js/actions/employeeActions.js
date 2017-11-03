import * as constants from '../util/constants';

export const getEmployees = (departmentId, pageNumber = 1, itemsPerPage = 5) => {
    return {
        type: constants.GET_EMPLOYEES,
        departmentId,
        pageNumber,
        itemsPerPage
    }
};

export const getCountOfEmployees = (id) => {
    return {
        type: constants.GET_COUNT_OF_EMPLOYEES,
        id
    }
};

export const getEmployee = (id) => {
    return {
        type: constants.GET_EMPLOYEE,
        id
    }
};

export const saveEmployee = (params, departmentId) => {
    return {
        type: constants.SAVE_EMPLOYEE,
        params,
        departmentId
    }
};

export const deleteEmployee = (id) => {
    return {
        type: constants.DELETE_EMPLOYEE,
        id
    }
};

export const getEmployeesSuccess = (employees, pageNumber, itemsPerPage) => {
    return {
        type: constants.GET_EMPLOYEES_SUCCESS,
        employees,
        pageNumber,
        itemsPerPage
    }
};

export const getCountOfEmployeesSuccess = (countOfEmployees) => {
    return {
        type: constants.GET_COUNT_OF_EMPLOYEES_SUCCESS,
        countOfEmployees
    }
};

export const getEmployeeSuccess = (employee) => {
    return {
        type: constants.GET_EMPLOYEE_SUCCESS,
        employee
    }
};

export const handleError = (errorMessage) => {
    return {
        type: constants.GET_VALIDATION_ERROR,
        errorMessage
    }
};

export const cleanState = () => {
    return {
        type: constants.CLEAN_STATE
    }
};