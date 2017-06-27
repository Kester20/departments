
let mainApp = angular.module('mainApp');

mainApp.service('employeeService', function ($http, $location, $state) {
    return {

        getByDepartment: function (id) {
            return $http.get('/employee/getByDepartment?departmentId=' + id);
        },

        getEmployeeSavePage: function (id) {
            return $http.get('/employee/save?employeeId=' + id);
        },

        saveEmployee: function (params, departmentId) {
            let promise = $http.post('/employee/save?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('employees', {departmentId:departmentId});
            }

            function rejected(error) {
                console.error(error.status);
                console.error(error.statusText);
            }
        },

        deleteEmployee: function ($scope, employeeId, departmentId) {
            $http.post('/employee/delete?employeeId=' + employeeId + '&departmentId=' + departmentId).then(function (response) {
                $scope.employees = response.data;
            });
        }
    };
});
