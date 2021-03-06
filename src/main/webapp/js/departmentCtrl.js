let mainApp = angular.module('mainApp');

mainApp.controller('departmentSaveController', DepartmentSaveController);
mainApp.controller('departmentController', DepartmentController);

function DepartmentSaveController($scope, department, departmentService) {
    let vm = this;
    vm.department = department;
    vm.hasError = hasError;
    vm.saveDepartment = saveDepartment;

    function hasError(field, validation) {
        if (validation) {
            return ($scope.form[field].$dirty && $scope.form[field].$error[validation]);
        }
        return ($scope.form[field].$dirty && $scope.form[field].$invalid);
    }

    function saveDepartment() {
        let name = vm.department.name;
        let id = vm.department.departmentId;
        let params;
        !id ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
        return departmentService.saveDepartment(params, vm);
    }
}

function DepartmentController(departments, totalDepartments, departmentService, $state, currentPage, itemsPerPage) {
    let vm = this;
    vm.departments = departments;
    vm.totalDepartments = totalDepartments;
    vm.currentPage = currentPage;
    vm.itemsPerPage = itemsPerPage;
    vm.selectPage = selectPage;
    vm.deleteDepartment = deleteDepartment;

    function selectPage(page, itemsPerPage) {
        $state.go("root", { page: page, itemsPerPage: itemsPerPage });
    }
    function deleteDepartment(id) {
        return departmentService.deleteDepartment(id);
    }
}

