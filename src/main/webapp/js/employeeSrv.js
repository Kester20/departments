let mainApp = angular.module('mainApp');

mainApp.service('employeeService', EmployeeService);

function EmployeeService($http, $state) {
    return {

        getByDepartment: function (id) {
            return $http.get('/employee/getByDepartment?departmentId=' + id);
        },

        getEmployeeSavePage: function (id) {
            return $http.get('/employee/save?employeeId=' + id);
        },

        saveEmployee: function (params, departmentId, vm) {
            let promise = $http.post('/employee/save?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('employees', {departmentId:departmentId});
            }

            function rejected(error) {
                vm.errorName = error.data.name;
                vm.errorAge = error.data.age;
                vm.errorDateOfBirth = error.data.dateOfBirth;
                vm.errorEmail = error.data.email;
            }
        },

        deleteEmployee: function (employeeId, departmentId) {
            $http.post('/employee/delete?employeeId=' + employeeId + '&departmentId=' + departmentId).then(function () {
                $state.reload();
            });
        }
    };
}
