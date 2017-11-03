import * as constants from '../util/constants';

export const getDepartments = (pageNumber = 1, itemsPerPage = 5) => {
    return {
        type: constants.GET_DEPARTMENTS,
        pageNumber,
        itemsPerPage
    }
};

export const getCountOfDepartments = () => {
    return {
        type: constants.GET_COUNT_OF_DEPARTMENTS
    }
};

export const deleteDepartment = (id) => {
    return {
        type: constants.DELETE_DEPARTMENT,
        id
    }
};

export const getDepartment = (id) => {
    return {
        type: constants.GET_DEPARTMENT,
        id
    }
};

export const saveDepartment = (params) => {
    return {
        type: constants.SAVE_DEPARTMENT,
        params
    }
};

export const getDepartmentsSuccess = (departments, pageNumber, itemsPerPage) => {
    return {
        type: constants.GET_DEPARTMENTS_SUCCESS,
        departments,
        pageNumber,
        itemsPerPage
    }
};

export const getCountOfDepartmentsSuccess = (countOfDepartments) => {
    return {
        type: constants.GET_COUNT_OF_DEPARTMENTS_SUCCESS,
        countOfDepartments
    }
};

export const getDepartmentSuccess = (department) => {
    return {
        type: constants.GET_DEPARTMENT_SUCCESS,
        department
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