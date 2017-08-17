import departments from "../templates/departments.html";
import departmentSave from "../templates/departmentSave.html";
import employeeSave from "../templates/employeeSave.html";
import employees from "../templates/employees.html";

let mainApp = angular.module('mainApp', ['ui.router', 'toaster']);

mainApp.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider

        .state('root', {
            url: "/",
            template: departments,
            controller: 'departmentController',
            controllerAs: 'dc',
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
            controllerAs: 'dsc',
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
            controllerAs: 'ec',
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
            controllerAs: 'esc',
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


