import {combineReducers} from 'redux';
import departmentReducer from './departmentReducer';

export default combineReducers({

    departments: departmentReducer,

});