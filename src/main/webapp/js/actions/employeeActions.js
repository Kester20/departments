import axios from "axios";
import history from '../util/history';
import {change} from 'redux-form';
import * as constants from '../util/constants';

export const getEmployees = (departmentId, pageNumber = 1, itemsPerPage = 5) => {
    return (dispatch) => {
        return axios.get('/employee/getByDepartment/' + departmentId + '/' + pageNumber + '/' + itemsPerPage)
            .then(response => {dispatch(getEmployeesSuccess(response.data, pageNumber, itemsPerPage))})
            .catch(error => {console.log(error);});
    };
};

export const getCountOfEmployees = (id) => {
    return (dispatch) => {
        return axios.get('/employee/getTotal/' + id)
            .then(response => {dispatch(getCountOfEmployeesSuccess(response.data))})
            .catch(error => {console.log(error);});
    };
};

export const getEmployee = (id) => {
    return (dispatch) => {
        axios
            .get('/employee/save?employeeId=' + id)
            .then(response => {
                dispatch(getEmployeeSuccess(response.data));
                dispatch(change(constants.EMPLOYEE_SAVE_FORM, 'name', response.data.name));
                dispatch(change(constants.EMPLOYEE_SAVE_FORM, 'age', response.data.age));
                dispatch(change(constants.EMPLOYEE_SAVE_FORM, 'dateOfBirth', response.data.dateOfBirth));
                dispatch(change(constants.EMPLOYEE_SAVE_FORM, 'email', response.data.email));
            })
            .catch(err => console.log(err));
    }
};

export const saveEmployee = (params, departmentId) => {
    return (dispatch) => {
        axios
            .post('/employee/save?' + params)
            .then( () => {
                history.push('/employee/getByDepartment/' + departmentId);
            })
            .catch(err => dispatch(handleError(err.response.data.email)));
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