
let mainApp = angular.module('mainApp');

mainApp.service('employeeService', function ($http, $location, $state, config) {
    return {

        getByDepartment: function (id) {
            return $http.get(config.getAllEmployees + '&' + config.ns +  'departmentId=' + id);
        },

        getEmployeeSavePage: function (id) {
            return $http.get(config.saveEmployee + '&' + config.ns + 'employeeId=' + id);
        },

        saveEmployee: function (params, departmentId, $scope) {
            let promise = $http.post(config.saveEmployee + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('employees', {departmentId:departmentId});
            }

            function rejected(error) {
                console.error(error.status);
                console.error(error.statusText);
                $scope.errorName = error.data._name;
                $scope.errorAge = error.data._age;
                $scope.errorDateOfBirth = error.data._dateOfBirth;
                $scope.errorEmail = error.data._email;
            }
        },

        deleteEmployee: function ($scope, employeeId, departmentId) {
            $http.post(config.deleteEmployee +
                '&' + config.ns +  'employeeId=' + employeeId +
                '&' + config.ns + 'departmentId=' + departmentId)
                .then(function () {
                $state.reload();
            });
        }
    };
});
