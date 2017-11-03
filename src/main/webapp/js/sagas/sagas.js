import {takeEvery} from 'redux-saga/effects';
import * as departmentSagas from './departmentSaga';
import * as constants from '../util/constants';

export default function* rootSaga() {
    yield [
        takeEvery(constants.GET_DEPARTMENTS, departmentSagas.getDepartments),
        takeEvery(constants.GET_COUNT_OF_DEPARTMENTS, departmentSagas.getCountOfDepartments),
        takeEvery(constants.DELETE_DEPARTMENT, departmentSagas.deleteDepartment),
        takeEvery(constants.GET_DEPARTMENT, departmentSagas.getDepartment),
        takeEvery(constants.SAVE_DEPARTMENT, departmentSagas.saveDepartment),
    ];
}