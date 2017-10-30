import {fromJS} from 'immutable';
import * as constants from '../util/constants';

const initialState = fromJS({
    list: [],
    pageNumber: 1,
    itemsPerPage: 5,
    countOfEmployees: 0,
    employee: {
        departmentId: null,
        name: null
    },
    errorMessage: null
});

export default (state = initialState, action) => {
    switch (action.type) {
        case constants.GET_EMPLOYEES_SUCCESS:
            return state
                .set('list', fromJS(action.employees))
                .set('pageNumber', action.pageNumber)
                .set('itemsPerPage', action.itemsPerPage);

        case constants.GET_COUNT_OF_EMPLOYEES_SUCCESS:
            return state.set('countOfEmployees', action.countOfEmployees);

        case constants.GET_EMPLOYEE_SUCCESS:
            return state.set('department', action.department);

        default:
            return state
    }
}