import departments from "../templates/departments.html";
import departmentSave from "../templates/departmentSave.html";
import employeeSave from "../templates/employeeSave.html";
import employees from "../templates/employees.html";

let mainApp = angular.module('mainApp', ['ui.router', 'toaster', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngMaterial']);

mainApp.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {

    $urlRouterProvider.otherwise("root");

    $stateProvider

        .state('root', {
            url: "/?page/?itemsPerPage",
            template: departments,
            controller: 'departmentController',
            controllerAs: 'dc',
            resolve: {
                'totalDepartments': function (departmentService) {
                    return departmentService.getTotalDepartments().then(function (response) {
                        return response.data;
                    })
                },
                'departments': function (departmentService, $timeout, $stateParams) {
                    angular.element(document.querySelector('#loading')).addClass('loading');
                    return $timeout(function () {
                        let page = $stateParams.page ? $stateParams.page : 1;
                        let itemsPerPage = $stateParams.itemsPerPage ? $stateParams.itemsPerPage : 5;
                        return departmentService.getAllDepartments(page, itemsPerPage).then(function (response) {
                            angular.element(document.querySelector('#loading')).removeClass('loading');
                            return response.data;
                        });
                    }, 300);
                },
                'currentPage': function ($stateParams) {
                    return $stateParams.page ? $stateParams.page : 1;
                },
                'itemsPerPage': function ($stateParams) {
                    return $stateParams.itemsPerPage ? $stateParams.itemsPerPage : 5;
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
                        if ($stateParams.departmentId) {
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
            url: "/employee/getByDepartment/:departmentId/?page/?itemsPerPage",
            template: employees,
            controller: 'employeeController',
            controllerAs: 'ec',
            resolve: {
                'totalEmployees': function (employeeService, $stateParams) {
                    return employeeService.getTotalEmployees($stateParams.departmentId).then(function (response) {
                        return response.data;
                    })
                },
                'employees': function (employeeService, $stateParams, toaster, $timeout) {
                    angular.element(document.querySelector('#loading')).addClass('loading');
                    return $timeout(function () {
                        let page = $stateParams.page ? $stateParams.page : 1;
                        let itemsPerPage = $stateParams.itemsPerPage ? $stateParams.itemsPerPage : 5;
                        return employeeService.getByDepartment($stateParams.departmentId, page, itemsPerPage).then(function (response) {
                            angular.element(document.querySelector('#loading')).removeClass('loading');
                            if (response.data.length === 0) {
                                toaster.pop('note', 'Info', 'There are not employees in this department', null, 'trustedHtml');
                            }
                            return response.data;
                        });
                    }, 300);
                },
                'departmentId': function ($stateParams) {
                    return $stateParams.departmentId;
                },
                'currentPage': function ($stateParams) {
                    return $stateParams.page ? $stateParams.page : 1;
                },
                'itemsPerPage': function ($stateParams) {
                    return $stateParams.itemsPerPage ? $stateParams.itemsPerPage : 5;
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
                        if ($stateParams.employeeId) {
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


