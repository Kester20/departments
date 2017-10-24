import {fromJS} from 'immutable';

const initialState = fromJS({
    list: [],
    pageNumber: 1,
    itemsPerPage: 5,
    countOfDepartments: 0,
    department: {
        departmentId: null,
        name: null
    }
});

export default (state = initialState, action) => {
    switch (action.type) {
        case 'GET_DEPARTMENTS_SUCCESS':
            return state
                .set('list', fromJS(action.departments))
                .set('pageNumber', action.pageNumber)
                .set('itemsPerPage', action.itemsPerPage);

        case 'GET_COUNT_OF_DEPARTMENTS_SUCCESS':
            return state.set('countOfDepartments', action.countOfDepartments);

        case 'GET_DEPARTMENT_SUCCESS':
            return state.set('department', action.department);

        default:
            return state
    }
}