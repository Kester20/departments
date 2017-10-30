import axios from "axios";
import history from '../util/history';
import {change} from 'redux-form';
import * as constants from '../util/constants';

export const getEmployees = (departmentId, pageNumber = 1, itemsPerPage = 5) => {
    return (dispatch) => {
        return axios.get('/employee/getByDepartment/' + departmentId + '/' + pageNumber + '/' + itemsPerPage)
            .then(response => {dispatch(getEmployeeSuccess(response.data, pageNumber, itemsPerPage))})
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

export const getEmployeeSuccess = (employees, pageNumber, itemsPerPage) => {
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