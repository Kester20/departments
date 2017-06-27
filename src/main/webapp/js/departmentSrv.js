
let mainApp = angular.module('mainApp');

mainApp.service('departmentService', function ($http, $state) {
    return {

        getAllDepartments: function () {
            return $http.get('/department/getAll');
        },

        getDepartmentSavePage: function (id) {
            return $http.get('/department/save?departmentId=' + id);
        },

        saveDepartment: function (params) {
            let promise = $http.post('/department/save?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('root');
            }

            function rejected(error) {
                console.error(error.status);
                console.error(error.statusText);
            }
        },

        deleteDepartment: function ($scope, id) {
            $http.post('/department/delete?departmentId=' + id).then(function (response) {
                $scope.departments = response.data;
            });
        }
    };
});

