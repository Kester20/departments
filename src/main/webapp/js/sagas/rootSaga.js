import {takeEvery} from 'redux-saga/effects';
import * as departmentSagas from './departmentSaga';
import * as employeeSagas from './employeeSaga';
import * as constants from '../util/constants';

export default function* rootSaga() {
    yield [
        takeEvery(constants.GET_DEPARTMENTS, departmentSagas.getDepartments),
        takeEvery(constants.GET_COUNT_OF_DEPARTMENTS, departmentSagas.getCountOfDepartments),
        takeEvery(constants.DELETE_DEPARTMENT, departmentSagas.deleteDepartment),
        takeEvery(constants.GET_DEPARTMENT, departmentSagas.getDepartment),
        takeEvery(constants.SAVE_DEPARTMENT, departmentSagas.saveDepartment),
        takeEvery(constants.GET_EMPLOYEES, employeeSagas.getEmployees),
        takeEvery(constants.GET_COUNT_OF_EMPLOYEES, employeeSagas.getCountOfEmployees),
        takeEvery(constants.GET_EMPLOYEE, employeeSagas.getEmployee),
        takeEvery(constants.SAVE_EMPLOYEE, employeeSagas.saveEmployee),
        takeEvery(constants.DELETE_EMPLOYEE, employeeSagas.deleteEmployee),
    ];
}