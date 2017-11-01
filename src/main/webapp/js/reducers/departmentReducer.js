import {fromJS} from 'immutable';
import * as constants from '../util/constants';

const initialState = fromJS({
    list: [],
    pageNumber: 1,
    itemsPerPage: 5,
    countOfDepartments: 0,
    department: {
        departmentId: null,
        name: null
    },
    errorMessage: null
});

export default (state = initialState, action) => {
    switch (action.type) {
        case constants.GET_DEPARTMENTS_SUCCESS:
            return state
                .set('list', fromJS(action.departments))
                .set('pageNumber', action.pageNumber)
                .set('itemsPerPage', action.itemsPerPage);

        case constants.GET_COUNT_OF_DEPARTMENTS_SUCCESS:
            return state.set('countOfDepartments', action.countOfDepartments);

        case constants.GET_DEPARTMENT_SUCCESS:
            return state.set('department', action.department);

        case constants.GET_VALIDATION_ERROR:
            return state.set('errorMessage', action.errorMessage);

        case constants.CLEAN_STATE:
            return initialState;

        default:
            return state
    }
}