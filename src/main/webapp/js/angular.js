import "../pages/home.html";
import "../pages/about.html";
import "../pages/contact.html";
import departments from "../departments.html";
import departmentSave from "../departmentSave.html";

let mainApp = angular.module('mainApp', ['ngRoute']);

mainApp.config(function ($routeProvider, $locationProvider) {

    $routeProvider
        .when('/', {
            template: departments,
            controller: 'departmentController'
        })

        .when('/department/save/:departmentId?', {
            template: departmentSave,
            controller: 'departmentSaveController',
            resolve: {
                'department': function (departmentService, $route) {
                    if ($route.current.params.departmentId != null) {
                        return departmentService.getDepartmentSavePage($route.current.params.departmentId).then(function (response) {
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

mainApp.controller('departmentController', function ($scope, $http, departmentService) {

    let promise = $http.get('/department/getAll');
    promise.then(fulfilled, rejected);

    function fulfilled(response) {
        $scope.departments = response.data;
    }

    function rejected(error) {
        console.error(error.status);
        console.error(error.statusText);
    }

    $scope.deleteDepartment = function (id) {
        return departmentService.deleteDepartment($scope, id);
    };
});

