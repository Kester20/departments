import axios from "axios";

export const getDepartmentsSuccess = (departments, pageNumber, itemsPerPage) => {
    return {
        type: 'GET_DEPARTMENTS_SUCCESS',
        departments,
        pageNumber,
        itemsPerPage
    }
};

export const getCountOfDepartmentsSuccess = (countOfDepartments) => {
    return {
        type: 'GET_COUNT_OF_DEPARTMENTS_SUCCESS',
        countOfDepartments
    }
};

export const deleteDepartmentSuccess = () => {
    return {
        type: 'DELETE_DEPARTMENT_SUCCESS',
    }
};

export const getDepartments = (pageNumber = 1, itemsPerPage = 5) => {
    return (dispatch) => {
        return axios.get('/department/getAll/' + pageNumber + '/' + itemsPerPage)
            .then(response => {
                dispatch(getDepartmentsSuccess(response.data, pageNumber, itemsPerPage))
            })
            .catch(error => {
                console.log(error);
            });
    };
};

export const getCountOfDepartments = () => {
    return (dispatch) => {
        return axios.get('/department/getTotal/')
            .then(response => {
                dispatch(getCountOfDepartmentsSuccess(response.data))
            })
            .catch(error => {
                console.log(error);
            });
    };
};

export const deleteDepartment = (id) => {
    return (dispatch) => {
        return axios.post('/department/delete?departmentId=' + id)
            .then( () => {
                dispatch(getDepartments());
                dispatch(getCountOfDepartments());
            })
            .catch(error => {
                console.log(error);
            });
    };
};