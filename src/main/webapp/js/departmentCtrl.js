
let mainApp = angular.module('mainApp');

mainApp.controller('departmentSaveController', function ($scope, department, departmentService) {

    $scope.department = department;

    $scope.saveDepartment = function () {
        let name = $('input[name=name]').val();
        let id = $('input[name=departmentId]').val();
        let params;
        id == 'undefined' ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
        return departmentService.saveDepartment(params);
    };
});

mainApp.controller('departmentController', function ($scope, departments, departmentService) {

    $scope.departments = departments;

    $scope.deleteDepartment = function (id) {
        return departmentService.deleteDepartment($scope, id);
    };
});
