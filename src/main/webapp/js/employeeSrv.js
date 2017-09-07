let mainApp = angular.module('mainApp');

mainApp.service('employeeService', EmployeeService);

function EmployeeService($http, $state, $window, toaster) {
    return {

        getByDepartment: function (id, page, itemsPerPage) {
            return $http.get('/employee/getByDepartment?departmentId=' + id + "&page=" + page + "&itemsPerPage=" + itemsPerPage);
        },

        getEmployeeSavePage: function (id) {
            return $http.get('/employee/save?employeeId=' + id);
        },

        saveEmployee: function (params, departmentId, vm) {
            let promise = $http.post('/employee/save?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                toaster.pop('success', 'Info', 'Employee successfully saved', null, 'trustedHtml');
                $state.go('employees', {departmentId:departmentId});
            }

            function rejected(error) {
                vm.errorName = error.data.name;
                vm.errorAge = error.data.age;
                vm.errorDateOfBirth = error.data.dateOfBirth;
                toaster.pop('error', 'Error', error.data.email, null, 'trustedHtml');
            }
        },

        deleteEmployee: function (employeeId, departmentId) {
            if ($window.confirm('Are you sure you want to delete this department?')) {
                $http.post('/employee/delete?employeeId=' + employeeId + '&departmentId=' + departmentId).then(function () {
                    $state.reload();
                    toaster.pop('success', 'Info', 'Employee successfully deleted', null, 'trustedHtml');
                });
            }
        },

        getTotalEmployees: function (departmentId) {
            return $http.get('/employee/getTotal?departmentId=' + departmentId);
        }
    };
}
