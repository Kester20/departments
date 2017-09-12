let mainApp = angular.module('mainApp');

mainApp.service('employeeService', EmployeeService);

function EmployeeService($http, $state, $window, toaster, config) {
    return {

        getByDepartment: function (id, page, itemsPerPage) {
            return $http.get(config.getAllEmployees +
                '&' + config.ns +  'departmentId=' + id +
                '&' + config.ns + "page=" + page +
                '&' + config.ns + "itemsPerPage=" + itemsPerPage);
        },

        getEmployeeSavePage: function (id) {
            return $http.get(config.saveEmployee + '&' + config.ns + 'employeeId=' + id);
        },

        saveEmployee: function (params, departmentId, vm) {
            let promise = $http.post(config.saveEmployee + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                toaster.pop('success', 'Info', 'Employee successfully saved', null, 'trustedHtml');
                $state.go('employees', {departmentId:departmentId});
            }
            function rejected(error) {
                vm.errorName = error.data._name;
                vm.errorAge = error.data._age;
                vm.errorDateOfBirth = error.data._dateOfBirth;
                vm.errorEmail = error.data._email;
                toaster.pop('error', 'Error', error.data._email, null, 'trustedHtml');
            }
        },

        deleteEmployee: function (employeeId, departmentId) {
            if ($window.confirm('Are you sure you want to delete this department?')) {
                $http.post(config.deleteEmployee +
                '&' + config.ns +  'employeeId=' + employeeId +
                '&' + config.ns + 'department=' + departmentId)
                .then(function () {
                    $state.reload();
                    toaster.pop('success', 'Info', 'Employee successfully deleted', null, 'trustedHtml');
                });
            }
        },

        getTotalEmployees: function (departmentId) {
            return $http.get(config.getTotalEmployees + '&' + config.ns + 'departmentId=' + departmentId);
        }
    };
}
