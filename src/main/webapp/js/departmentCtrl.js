let mainApp = angular.module('mainApp');

mainApp.controller('departmentSaveController', DepartmentSaveController);
mainApp.controller('departmentController', DepartmentController);

function DepartmentSaveController($scope, department, departmentService, config) {
    let vm = this;
    vm.department = department;
    vm.hasError = hasError;
    vm.scriptPattern = scriptPattern();
    vm.saveDepartment = saveDepartment;

    function hasError(field, validation){
        if(validation){
            return ($scope.form[field].$dirty && $scope.form[field].$error[validation]);
        }
        return ($scope.form[field].$dirty && $scope.form[field].$invalid);
    }

    function scriptPattern() {
        let regexp = /^(<script|<script>).*(\/>|<\/script>)$/;
        return {
            test: function(value) {
                return !(regexp.test(value));
            }
        };
    }

    function saveDepartment() {
        let name = vm.department.name;
        let id = vm.department.departmentId;
        let params;
        id == null ? (params = "&" + config.ns + "name=" + name) :
            (params = "&" + config.ns + "name=" + name + "&" + config.ns + "departmentId=" + id);
        return departmentService.saveDepartment(params, vm);
    }
}

function DepartmentController(departments, departmentService) {
    let vm = this;
    vm.departments = departments;
    vm.deleteDepartment = deleteDepartment;

    function deleteDepartment(id) {
        return departmentService.deleteDepartment(id);
    }
}
