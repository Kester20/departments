import {put, call, select} from 'redux-saga/effects';
import {API} from '../util/util';
import {change} from 'redux-form';
import * as constants from '../util/constants'
import * as employeeActions from '../actions/employeeActions';
import {getDate} from '../util/util';
import history from '../util/history';
import {toastr} from 'react-redux-toastr';

export function* getEmployees(action) {
    try {
        const employees = yield call(API,
            `/employee/getByDepartment/${action.departmentId}/${action.pageNumber}/${action.itemsPerPage}`, 'get');
        yield put(employeeActions.getEmployeesSuccess(employees, action.pageNumber, action.itemsPerPage));
    } catch (e) {
        toastr.error('Error', e.message);
    }
}

export function* getCountOfEmployees(action) {
    try {
        const count = yield call(API, `/employee/getTotal/${action.id}`, 'get');
        yield put(employeeActions.getCountOfEmployeesSuccess(count));
    } catch (e) {
        toastr.error('Error', e.message);
    }
}

export function* getEmployee(action) {
    try {
        const employee = yield call(API, `/employee/save?employeeId=${action.id}`, 'get');
        yield put(employeeActions.getEmployeeSuccess(employee));
        yield put(change(constants.EMPLOYEE_SAVE_FORM, 'name', employee.name));
        yield put(change(constants.EMPLOYEE_SAVE_FORM, 'age', employee.age));
        yield put(change(constants.EMPLOYEE_SAVE_FORM, 'dateOfBirth', getDate(employee.dateOfBirth)));
        yield put(change(constants.EMPLOYEE_SAVE_FORM, 'email', employee.email));
    } catch (e) {
        toastr.error('Error', e.message);
    }
}

export function* saveEmployee(action) {
    try {
        yield call(API, `/employee/save?${action.params}`, 'post');
        history.push(`/employee/getByDepartment/${action.departmentId}`);
        toastr.success('Success', 'Employee was successfully saved');
    } catch (e) {
        yield put(employeeActions.handleError(e.response.data.email));
        toastr.error('Error', 'Choose another email');
    }
}

export function* deleteEmployee(action) {
    try {
        const state = yield select();
        const employees = state.employees;
        const departmentId = employees.get('list').get(0).get('department').get('departmentId');
        yield call(API, `/employee/delete?employeeId=${action.id}`, 'post');
        yield put(employeeActions.getEmployees(departmentId, employees.get('pageNumber'), employees.get('itemsPerPage')));
        yield put(employeeActions.getCountOfEmployees(departmentId));
        toastr.success('Success', 'Employee was successfully deleted');
    } catch (e) {
        toastr.error('Error', e.message);
    }
}