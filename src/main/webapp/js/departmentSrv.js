let mainApp = angular.module('mainApp');

mainApp.service('departmentService', DepartmentService);

function DepartmentService($http, $state, toaster) {
    return {

        getAllDepartments: function () {
            return $http.get('/department/getAll');
        },

        getDepartmentSavePage: function (id) {
            return $http.get('/department/save?departmentId=' + id);
        },

        saveDepartment: function (params, vm) {
            let promise = $http.post('/department/save?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('root');
            }

            function rejected(error) {
                toaster.pop('error', 'Error', error.data.name, null, 'trustedHtml');
            }
        },

        deleteDepartment: function (id) {
            $http.post('/department/delete?departmentId=' + id).then(function () {
                $state.reload();
            });
        }
    };
}

