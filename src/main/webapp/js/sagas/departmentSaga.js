import {put, call, select} from 'redux-saga/effects';
import * as departmentActions from '../actions/departmentActions';
import {API} from '../util/util';
import {change} from 'redux-form';
import * as constants from '../util/constants';
import history from '../util/history';
import {toastr} from 'react-redux-toastr';

export function* getDepartments(action) {
    try {
        const departments = yield call(API, '/department/getAll/' + action.pageNumber + '/' + action.itemsPerPage, 'get');
        yield put(departmentActions.getDepartmentsSuccess(departments, action.pageNumber, action.itemsPerPage));
    } catch (e) {
        toastr.error('Error', e.message);
    }
}

export function* getCountOfDepartments() {
    try {
        const count = yield call(API, '/department/getTotal/', 'get');
        yield put(departmentActions.getCountOfDepartmentsSuccess(count));
    } catch (e) {
        toastr.error('Error', 'Try later');
    }
}

export function* deleteDepartment(action) {
    try {
        const state = yield select();
        const departments = state.departments;
        yield call(API, '/department/delete?departmentId=' + action.id, 'post');
        yield put(departmentActions.getDepartments(departments.get('pageNumber'), departments.get('itemsPerPage')));
        yield put(departmentActions.getCountOfDepartments());
        toastr.success('Success', 'Department was successfully deleted');
    } catch (e) {
        toastr.error('Error', 'Try later');
    }
}

export function* getDepartment(action) {
    try {
        const department = yield call(API, '/department/save?departmentId=' + action.id, 'get');
        yield put(departmentActions.getDepartmentSuccess(department));
        yield put(change(constants.DEPARTMENT_SAVE_FORM, 'name', department.name));
    } catch (e) {
        toastr.error('Error', 'Try later');
    }
}

export function* saveDepartment(action) {
    try {
        yield call(API, '/department/save?' + action.params, 'post');
        history.push('/');
        toastr.success('Success', 'Department was successfully saved');
    } catch (e) {
        yield put(departmentActions.handleError(e.response.data.name));
        toastr.error('Error', 'Choose another name');
    }
}