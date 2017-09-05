let mainApp = angular.module('mainApp');

mainApp.directive('date', DateFilter);
mainApp.controller('employeeSaveController', EmployeeSaveController);
mainApp.controller('employeeController', EmployeeController);

function DateFilter (dateFilter) {
    return {
        require:'ngModel',
        link:function (scope, elm, attrs, ctrl) {
            let dateFormat = attrs['date'] || 'yyyy-MM-dd';
            ctrl.$formatters.unshift(function (modelValue) {
                return dateFilter(modelValue, dateFormat);
            });
        }
    };
}

function EmployeeSaveController($rootScope, $scope, $filter, employee, employeeService) {
    let vm = this;
    vm.employee = employee;
    vm.hasError = hasError;
    vm.datePattern = datePattern();
    vm.scriptPattern = scriptPattern();
    vm.saveEmployee = saveEmployee;

    function hasError(field, validation){
        if(validation){
            return ($scope.form[field].$dirty && $scope.form[field].$error[validation]);
        }
        return ($scope.form[field].$dirty && $scope.form[field].$invalid);
    }
    function datePattern() {
        let regexp = /^\d\d\d\d-\d\d?-\d\d$/;
        return {
            test: function(value) {
                return regexp.test(value);
            }
        };
    }
    function scriptPattern() {
        let regexp = /^(<script|<script>).*(\/>|<\/script>)$/;
        return {
            test: function(value) {
                return !(regexp.test(value));
            }
        };
    }

    function saveEmployee() {
        let employeeId = vm.employee.employeeId;
        let name = vm.employee.name;
        let age = vm.employee.age;
        let dateOfBirth = $filter('date')(vm.employee.dateOfBirth, 'yyyy-MM-dd');
        let email = vm.employee.email;
        let departmentId = $rootScope.departmentId;
        let params = "";

        if (employeeId) {
            params = "employeeId=" + employeeId + "&";
        }
        params += "name=" + name + "&age=" + age + "&dateOfBirth=" + dateOfBirth + "&email=" + email + "&departmentId=" + departmentId;
        return employeeService.saveEmployee(params, departmentId, vm);
    }
}

function EmployeeController($rootScope, employees, totalEmployees, departmentId, employeeService, $state, currentPage) {
    let vm = this;
    vm.employees = employees;
    vm.totalEmployees = totalEmployees;
    vm.currentPage = currentPage;
    vm.selectPage = selectPage;
    vm.deleteEmployee = deleteEmployee;
    vm.orderBy = orderBy;
    $rootScope.departmentId = departmentId;

    function selectPage(page) {
        $state.go("employees", { page: page });
    }
    function deleteEmployee(employeeId, departmentId) {
        return employeeService.deleteEmployee(employeeId, departmentId);
    }
    function orderBy(criteria) {
        vm.criteria = criteria;
    }
}
