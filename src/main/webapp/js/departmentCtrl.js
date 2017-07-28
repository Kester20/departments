
let mainApp = angular.module('mainApp');

mainApp.controller('departmentSaveController', function ($scope, department, departmentService, config) {

    $scope.department = department;

    $scope.hasError = function(field, validation){
        if(validation){
            return ($scope.form[field].$dirty && $scope.form[field].$error[validation]);
        }
        return ($scope.form[field].$dirty && $scope.form[field].$invalid);
    };

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

    $scope.saveDepartment = function () {
        let name = $scope.department.name;
        let id = $scope.department.departmentId;
        let params;
        id == null ? (params = "&" + config.ns + "name=" + name) :
            (params = "&" + config.ns + "name=" + name + "&" + config.ns + "departmentId=" + id);
        return departmentService.saveDepartment(params, $scope);
    };
});

mainApp.controller('departmentController', function ($scope, departments, departmentService) {

    $scope.departments = departments;

    $scope.deleteDepartment = function (id) {
        return departmentService.deleteDepartment($scope, id);
    };
});
