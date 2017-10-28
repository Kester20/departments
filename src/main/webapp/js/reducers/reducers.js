import {combineReducers} from 'redux';
import departmentReducer from './departmentReducer';
import { reducer as forms } from 'redux-form';

export default combineReducers({

    departments: departmentReducer,
    form: forms

});