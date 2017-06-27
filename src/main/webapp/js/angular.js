import departments from "../templates/departments.html";
import departmentSave from "../templates/departmentSave.html";

let mainApp = angular.module('mainApp', ['ui.router']);

mainApp.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {

    $urlRouterProvider.otherwise("/")

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
        });

    $locationProvider.html5Mode(true);
});

mainApp.service('departmentService', function ($http, $location) {
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
                $location.path('/');
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

