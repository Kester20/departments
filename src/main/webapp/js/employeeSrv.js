let mainApp = angular.module('mainApp');

mainApp.service('employeeService', EmployeeService);

function EmployeeService($http, $state, config) {
    return {

        getByDepartment: function (id) {
            return $http.get(config.getAllEmployees + '&' + config.ns +  'departmentId=' + id);
        },

        getEmployeeSavePage: function (id) {
            return $http.get(config.saveEmployee + '&' + config.ns + 'employeeId=' + id);
        },

        saveEmployee: function (params, departmentId, vm) {
            let promise = $http.post(config.saveEmployee + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('employees', {departmentId:departmentId});
            }
            function rejected(error) {
                vm.errorName = error.data._name;
                vm.errorAge = error.data._age;
                vm.errorDateOfBirth = error.data._dateOfBirth;
                vm.errorEmail = error.data._email;
            }
        },

        deleteEmployee: function (employeeId, departmentId) {
            $http.post(config.deleteEmployee +
                '&' + config.ns +  'employeeId=' + employeeId +
                '&' + config.ns + 'department=' + departmentId)
                .then(function () {
                $state.reload();
            });
        }
    };
}
