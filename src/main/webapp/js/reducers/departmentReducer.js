import {fromJS} from 'immutable';

const initialState = {};

export default (state = [], action) => {
    switch (action.type){
        case 'GET_DEPARTMENTS_SUCCESS':
            return {
                list: action.departments,
                pageNumber: action.pageNumber,
                itemsPerPage: action.itemsPerPage
            };

        default:
            return state
    }
}