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
                'departments': function (departmentService, $timeout) {
                    angular.element(document.querySelector('#loading')).addClass('loading');
                    return $timeout(function () {
                        return departmentService.getAllDepartments().then(function (response) {
                            angular.element(document.querySelector('#loading')).removeClass('loading');
                            return response.data;
                        });
                    }, 300);
                }
            }
        })

        .state('departmentSave', {
            url: "/department/save/?departmentId",
            template: departmentSave,
            controller: 'departmentSaveController',
            controllerAs: 'dsc',
            resolve: {
                'department': function (departmentService, $stateParams, $timeout) {
                    angular.element(document.querySelector('#loading')).addClass('loading');
                    return $timeout(function () {
                        if ($stateParams.departmentId != null) {
                            return departmentService.getDepartmentSavePage($stateParams.departmentId).then(function (response) {
                                angular.element(document.querySelector('#loading')).removeClass('loading');
                                return response.data;
                            });
                        }
                        angular.element(document.querySelector('#loading')).removeClass('loading');
                    }, 300);
                }
            }
        })

        .state('employees', {
            url: "/employee/getByDepartment/:departmentId",
            template: employees,
            controller: 'employeeController',
            controllerAs: 'ec',
            resolve: {
                'employees': function (employeeService, $stateParams, toaster, $q, $timeout) {
                    angular.element(document.querySelector('#loading')).addClass('loading');
                    return $timeout(function () {
                        return employeeService.getByDepartment($stateParams.departmentId).then(function (response) {
                            angular.element(document.querySelector('#loading')).removeClass('loading');
                            if(response.data.length === 0){
                                toaster.pop('note', 'Info', 'There are not employees in this department', null, 'trustedHtml');
                                return $q.reject("There are not employees in this department");
                            }else{
                                return response.data;
                            }
                        });
                    }, 300);
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
                'employee': function (employeeService, $stateParams, $timeout) {
                    angular.element(document.querySelector('#loading')).addClass('loading');
                    return $timeout(function () {
                        if ($stateParams.employeeId != null) {
                            return employeeService.getEmployeeSavePage($stateParams.employeeId).then(function (response) {
                                angular.element(document.querySelector('#loading')).removeClass('loading');
                                return response.data;
                            });
                        }
                        angular.element(document.querySelector('#loading')).removeClass('loading');
                    }, 300);
                }
            }
        });

    $locationProvider.html5Mode(true);
});


