import {fromJS} from 'immutable';

const initialState = fromJS({});

export default function reducer(state = initialState, action) {
    switch (action.type){
        case 'GET_DEPARTMENTS':
            return {
                departments: action.departments
            };

        default:
            return state
    }
}