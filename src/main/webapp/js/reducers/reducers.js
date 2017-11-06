import {combineReducers} from 'redux';
import departmentReducer from './departmentReducer';
import employeeReducer from './employeeReducer';
import {reducer as forms} from 'redux-form';
import {reducer as toastrReducer} from 'react-redux-toastr'

export default combineReducers({
    departments: departmentReducer,
    employees: employeeReducer,
    form: forms,
    toastr: toastrReducer
});