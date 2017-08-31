let mainApp = angular.module('mainApp');

mainApp.controller('departmentSaveController', DepartmentSaveController);
mainApp.controller('departmentController', DepartmentController);
mainApp.service('pagerService', PagerService);

function DepartmentSaveController($scope, department, departmentService) {
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
        id == null ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
        return departmentService.saveDepartment(params, vm);
    }
}

function DepartmentController(departments, departmentService, pagerService) {
    let vm = this;
    vm.departments = departments;
    vm.deleteDepartment = deleteDepartment;

    function deleteDepartment(id) {
        return departmentService.deleteDepartment(id);
    }

    //vm.dummyItems = _.range(1, 151);
    vm.pager = {};
    vm.setPage = setPage;

    initController();

    function initController() {
        vm.setPage(1);
    }

    function setPage(page) {
        if (page < 1 || page > vm.pager.totalPages) {
            return;
        }
        vm.pager = pagerService.GetPager(vm.departments.length, page);
        vm.items = vm.departments.slice(vm.pager.startIndex, vm.pager.endIndex + 1);
    }
}

function PagerService() {
    let service = {};
    service.GetPager = GetPager;

    return service;

    function GetPager(totalItems, currentPage, pageSize) {
        currentPage = currentPage || 1;
        pageSize = pageSize || 10;

        // calculate total pages
        let totalPages = Math.ceil(totalItems / pageSize);

        let startPage, endPage;
        if (totalPages <= 10) {
            // less than 10 total pages so show all
            startPage = 1;
            endPage = totalPages;
        } else {
            // more than 10 total pages so calculate start and end pages
            if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
            } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
            }
        }

        // calculate start and end item indexes
        let startIndex = (currentPage - 1) * pageSize;
        let endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

        // create an array of pages to ng-repeat in the pager control
        let pages = _.range(startPage, endPage + 1);

        // return object with all pager properties required by the view
        return {
            totalItems: totalItems,
            currentPage: currentPage,
            pageSize: pageSize,
            totalPages: totalPages,
            startPage: startPage,
            endPage: endPage,
            startIndex: startIndex,
            endIndex: endIndex,
            pages: pages
        };
    }
}
