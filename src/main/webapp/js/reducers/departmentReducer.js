import {fromJS} from 'immutable';

const initialState = fromJS({
    list: [],
    pageNumber: 1,
    itemsPerPage: 5,
    countOfDepartments: 0
});

export default (state = initialState, action) => {
    switch (action.type) {
        case 'GET_DEPARTMENTS_SUCCESS':
            return state
                .set('list', fromJS(action.departments))
                .set('pageNumber', action.pageNumber)
                .set('itemsPerPage', action.itemsPerPage);

        case 'GET_COUNT_OF_DEPARTMENTS_SUCCESS':
            return state
                .set('countOfDepartments', action.countOfDepartments);

        default:
            return state
    }
}