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

        .when('/department/save', {
            template: departmentSave,
            controller: 'departmentSaveController',
            resolve: {
                "department": function($http) {
                    return {
                        promise: function () {
                            return $http.get('/department/save?departmentId=' + 8)
                        }
                    };
                }
            }
        });

    $locationProvider.html5Mode(true);
});


mainApp.controller('departmentSaveController', function ($scope, $http, department) {
    department.promise().then(function(promise) {
        $scope.department = promise.data;
    });

});

mainApp.controller('departmentController', function ($scope, $http) {

    let promise = $http.get('/department/getAll');
    promise.then(fulfilled, rejected);

    function fulfilled(response) {
        $scope.departments = response.data;
    }

    function rejected(error) {
        console.error(error.status);
        console.error(error.statusText);
    }
});

