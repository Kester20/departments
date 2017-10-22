import axios from "axios";

export const getDepartmentsSuccess = (departments, pageNumber, itemsPerPage) => {
    return {
        type: 'GET_DEPARTMENTS_SUCCESS',
        departments,
        pageNumber,
        itemsPerPage
    }
};

export const getDepartments = (pageNumber = 1, itemsPerPage = 5) => {
    return (dispatch) => {
        return axios.get('/department/getAll/' + pageNumber + '/' + itemsPerPage)
            .then(response => {
                dispatch(getDepartmentsSuccess(response.data, pageNumber, itemsPerPage))
            })
            .catch(error => {
                throw(error);
            });
    };
};