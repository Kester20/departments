let mainApp = angular.module('mainApp');

mainApp.service('employeeService', EmployeeService);

function EmployeeService($http, $state, $window, toaster) {
    return {

        getByDepartment: function (id, page, itemsPerPage) {
            return $http.get('getEmployeesByDepartment.action?department.departmentId=' + id + "&page=" + page + "&itemsPerPage=" + itemsPerPage);
        },

        getEmployeeSavePage: function (id) {
            return $http.get('saveEmployee.action?employee.employeeId=' + id);
        },

        saveEmployee: function (params, departmentId, vm) {
            let promise = $http.post('saveEmployee.action?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                toaster.pop('success', 'Info', 'Employee successfully saved', null, 'trustedHtml');
                $state.go('employees', {departmentId:departmentId});
            }

            function rejected(error) {
                vm.errorName = error.data.fieldErrors.name ? error.data.fieldErrors.name.toString() : null;
                vm.errorAge = error.data.fieldErrors.age ? error.data.fieldErrors.age.toString() : null;
                vm.errorDateOfBirth = error.data.fieldErrors.dateOfBirth ? error.data.fieldErrors.dateOfBirth.toString() : null;
                toaster.pop('error', 'Error', error.data.fieldErrors.email.toString(), null, 'trustedHtml');
            }
        },

        deleteEmployee: function (employeeId, departmentId) {
            if ($window.confirm('Are you sure you want to delete this department?')) {
                $http.post('deleteEmployee?employee.employeeId=' + employeeId + '&employee.department.departmentId=' + departmentId).then(function () {
                    $state.reload();
                    toaster.pop('success', 'Info', 'Employee successfully deleted', null, 'trustedHtml');
                });
            }
        },

        getTotalEmployees: function (departmentId) {
            return $http.get('getTotalEmployees?department.departmentId=' + departmentId);
        }
    };
}
