let mainApp = angular.module('mainApp');

mainApp.service('departmentService', DepartmentService);

function DepartmentService($http, $state) {
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
                console.error(error.status);
                console.error(error.statusText);
                vm.errorName = error.data.name;
            }
        },

        deleteDepartment: function (id) {
            $http.post('/department/delete?departmentId=' + id).then(function () {
                $state.reload();
            });
        }
    };
}

