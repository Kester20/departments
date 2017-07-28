
let mainApp = angular.module('mainApp');

mainApp.directive('date', function (dateFilter) {
    return {
        require:'ngModel',
        link:function (scope, elm, attrs, ctrl) {

            let dateFormat = attrs['date'] || 'yyyy-MM-dd';

            ctrl.$formatters.unshift(function (modelValue) {
                return dateFilter(modelValue, dateFormat);
            });
        }
    };
});

mainApp.controller('employeeSaveController', function ($rootScope, $scope, $http, $filter, employee, employeeService, config) {

    $scope.employee = employee;

    $scope.hasError = function(field, validation){
        if(validation){
            return ($scope.form[field].$dirty && $scope.form[field].$error[validation]);
        }
        return ($scope.form[field].$dirty && $scope.form[field].$invalid);
    };

    $scope.datePattern = (function() {
        let regexp = /^\d\d\d\d-\d\d?-\d\d$/;
        return {
            test: function(value) {
                return regexp.test(value);
            }
        };
    })();

    $scope.scriptPattern = (function() {
        let regexp = /^(<script|<script>).*(\/>|<\/script>)$/;
        return {
            test: function(value) {
                if(regexp.test(value)){
                    return false;
                }
                return true;
            }
        };
    })();

    $scope.saveEmployee = function () {
        let employeeId = $scope.employee.employeeId;
        let name = $scope.employee.name;
        let age = $scope.employee.age;
        let dateOfBirth = $filter('date')($scope.employee.dateOfBirth, 'yyyy-MM-dd');
        let email = $scope.employee.email;
        let departmentId = $rootScope.departmentId;
        let params = "";

        if (employeeId != null) {
            params = "&" + config.ns + "employeeId=" + employeeId;
        }
        params +=
            "&" + config.ns + "name=" + name +
            "&" + config.ns + "age=" + age +
            "&" + config.ns + "dateOfBirth=" + dateOfBirth +
            "&" + config.ns + "email=" + email +
            "&" + config.ns + "departmentId=" + departmentId;
        return employeeService.saveEmployee(params, departmentId, $scope);
    };
});

mainApp.controller('employeeController', function ($rootScope, $scope, $http, employees, departmentId, employeeService) {

    $scope.employees = employees;
    $rootScope.departmentId = departmentId;

    $scope.deleteEmployee = function (employeeId, departmentId) {
        return employeeService.deleteEmployee($scope, employeeId, departmentId);
    };
});
