
let mainApp = angular.module('mainApp');

mainApp.controller('employeeSaveController', function ($scope, $http, $location, employee, employeeService) {

    $scope.employee = employee;

    $scope.saveEmployee = function () {
        let employeeId = $("input[name=employeeId]").val();
        let name = $("input[name=name]").val();
        let age = $("input[name=age]").val();
        let dateOfBirth = $("input[name=dateOfBirth]").val();
        let email = $("input[name=email]").val();
        let departmentId = $("input[name=departmentId]").val();
        let params = "";

        if (employeeId != 'undefined') {
            params = "employeeId=" + employeeId + "&";
        }
        params += "name=" + name + "&age=" + age + "&dateOfBirth=" + dateOfBirth + "&email=" + email + "&departmentId=" + departmentId;
        return employeeService.saveEmployee(params, departmentId);
    };
});

mainApp.controller('employeeController', function ($rootScope, $scope, $http, employees, departmentId, employeeService) {

    $scope.employees = employees;
    $rootScope.departmentId = departmentId;

    $scope.deleteEmployee = function (employeeId, departmentId) {
        return employeeService.deleteEmployee($scope, employeeId, departmentId);
    };
});
