import {combineReducers} from 'redux';
import departmentReducer from './departmentReducer';
import employeeReducer from './employeeReducer';
import { reducer as forms } from 'redux-form';

export default combineReducers({
    departments: departmentReducer,
    employees: employeeReducer,
    form: forms
});