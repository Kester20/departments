import axios from "axios";
import history from '../util/history';
import {change} from 'redux-form';
import * as constants from '../util/constants';

export const getDepartments = (pageNumber = 1, itemsPerPage = 5) => {
    return (dispatch) => {
        return axios.get('/department/getAll/' + pageNumber + '/' + itemsPerPage)
            .then(response => {dispatch(getDepartmentsSuccess(response.data, pageNumber, itemsPerPage))})
            .catch(error => {console.log(error);});
    };
};

export const getCountOfDepartments = () => {
    return (dispatch) => {
        return axios.get('/department/getTotal/')
            .then(response => {dispatch(getCountOfDepartmentsSuccess(response.data))})
            .catch(error => {console.log(error);});
    };
};

export const deleteDepartment = (id) => {
    return (dispatch, getState) => {
        const state = getState();
        return axios.post('/department/delete?departmentId=' + id)
            .then(() => {
                dispatch(getDepartments(state.departments.get('pageNumber'), state.departments.get('itemsPerPage')));
                dispatch(getCountOfDepartments());
            })
            .catch(error => {console.log(error);});
    };
};

export const getDepartment = (id) => {
    return (dispatch) => {
        axios
            .get('/department/save?departmentId=' + id)
            .then(response => {
                dispatch(getDepartmentSuccess(response.data));
                dispatch(change(constants.DEPARTMENT_SAVE_FORM, 'name', response.data.name));
            })
            .catch(err => console.log(err));
    }
};

export const saveDepartment = (params) => {
    return (dispatch) => {
        axios
            .post('/department/save?' + params)
            .then( () => {
                history.push('/');
            })
            .catch(err => dispatch(handleError(err.response.data.name)));
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