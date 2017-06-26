import "../pages/home.html";
import "../pages/about.html";
import "../pages/contact.html";
import departments from "../departments.html";
import departmentSave from "../departmentSave.html";

let mainApp = angular.module('mainApp', ['ngRoute']);

mainApp
    .config(function ($routeProvider, $locationProvider) {

        $routeProvider
            .when('/', {
                template: departments,
                controller: 'departmentController'
            })

            .when('/department/save', {
                template: departmentSave,
                controller: 'departmentController',
                resolve: {
                    function ($http) {
                        let promise = $http.get('/department/save?departmentId=' + 9);
                        promise.then(fulfilled, rejected);
                        function fulfilled(response) {
                            $scope.department = response.data;
                            /*let angularElement = angular.element(departmentSave);
                            let html = $compile(angularElement);
                            angular.element('#app').empty();
                            angular.element('#app').append(html($scope));*/
                        }
                    }
                }
            })

            .when('/about', {
                templateUrl: 'pages/about.html',
                controller: 'aboutController'
            })

            .when('/contact', {
                templateUrl: 'pages/contact.html',
                controller: 'contactController'
            });

        $locationProvider.html5Mode(true);
    });


mainApp.controller('departmentController', function ($scope, $http, $compile) {

    let promise = $http.get('/department/getAll');
    promise.then(fulfilled, rejected);

    function fulfilled(response) {
        $scope.departments = response.data;
    }

    function rejected(error) {
        console.error(error.status);
        console.error(error.statusText);
    }

    /*$scope.getDepartmentSavePage = function (id) {
        let promise = $http.get('/department/save?departmentId=' + id);
        promise.then(fulfilled, rejected);
        function fulfilled(response) {
            $scope.department = response.data;
            let angularElement = angular.element(departmentSave);
            let html = $compile(angularElement);
            angular.element('#app').empty();
            angular.element('#app').append(html($scope));
        }
    }*/
});

