import departments from "../templates/departments.html";
import departmentSave from "../templates/departmentSave.html";
import employeeSave from "../templates/employeeSave.html";
import employees from "../templates/employees.html";

let mainApp = angular.module('mainApp', ['ui.router']);

mainApp.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('root', {
            url: "/",
            template: departments,
            controller: 'departmentController',
            resolve: {
                'departments': function (departmentService) {
                    return departmentService.getAllDepartments().then(function (response) {
                        return response.data;
                    });
                }
            }
        })

        .state('departmentSave', {
            url: "/department/save/?departmentId",
            template: departmentSave,
            controller: 'departmentSaveController',
            resolve: {
                'department': function (departmentService, $stateParams) {
                    if ($stateParams.departmentId != null) {
                        return departmentService.getDepartmentSavePage($stateParams.departmentId).then(function (response) {
                            return response.data;
                        });
                    }
                }
            }
        })

        .state('employees', {
            url: "/employee/getByDepartment/:departmentId",
            template: employees,
            controller: 'employeeController',
            resolve: {
                'employees': function (employeeService, $stateParams) {
                    return employeeService.getByDepartment($stateParams.departmentId).then(function (response) {
                        return response.data;
                    });
                },
                'departmentId': function ($stateParams) {
                    return $stateParams.departmentId;
                }
            }
        })

        .state('employeeSave', {
            url: "/employee/save/?employeeId",
            template: employeeSave,
            controller: 'employeeSaveController',
            resolve: {
                'employee': function (employeeService, $stateParams) {
                    if ($stateParams.employeeId != null) {
                        return employeeService.getEmployeeSavePage($stateParams.employeeId).then(function (response) {
                            return response.data;
                        });
                    }
                }
            }
        });

    $locationProvider.html5Mode(true);
});

mainApp.service('employeeService', function ($http, $location, $state) {
    return {

        getByDepartment: function (id) {
            return $http.get('/employee/getByDepartment?departmentId=' + id);
        },

        getEmployeeSavePage: function (id) {
            return $http.get('/employee/save?employeeId=' + id);
        },

        saveEmployee: function (params, departmentId) {
            let promise = $http.post('/employee/save?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('employees', {departmentId:departmentId});
            }

            function rejected(error) {
                console.error(error.status);
                console.error(error.statusText);
            }
        },

        deleteEmployee: function ($scope, employeeId, departmentId) {
            $http.post('/employee/delete?employeeId=' + employeeId + '&departmentId=' + departmentId).then(function (response) {
                $scope.employees = response.data;
            });
        }
    };
});

mainApp.service('departmentService', function ($http, $state) {
    return {

        getAllDepartments: function () {
            return $http.get('/department/getAll');
        },

        getDepartmentSavePage: function (id) {
            return $http.get('/department/save?departmentId=' + id);
        },

        saveDepartment: function (params) {
            let promise = $http.post('/department/save?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('root');
            }

            function rejected(error) {
                console.error(error.status);
                console.error(error.statusText);
            }
        },

        deleteDepartment: function ($scope, id) {
            $http.post('/department/delete?departmentId=' + id).then(function (response) {
                $scope.departments = response.data;
            });
        }
    };
});


mainApp.controller('departmentSaveController', function ($scope, $http, $location, department, departmentService) {

    $scope.department = department;

    $scope.saveDepartment = function () {
        let name = $('input[name=name]').val();
        let id = $('input[name=departmentId]').val();
        let params;
        id == 'undefined' ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
        return departmentService.saveDepartment(params);
    };
});

mainApp.controller('departmentController', function ($scope, $http, departments, departmentService) {

    $scope.departments = departments;

    $scope.deleteDepartment = function (id) {
        return departmentService.deleteDepartment($scope, id);
    };
});

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

