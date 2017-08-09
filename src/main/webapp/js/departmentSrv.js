
let mainApp = angular.module('mainApp');

mainApp.service('departmentService', function ($http, $state, config) {
    return {

        getAllDepartments: function () {
            return $http.get(config.getAllDepartments);
        },

        getDepartmentSavePage: function (id) {
            return $http.get(config.saveDepartment + '&' + config.ns +  'departmentId=' + id);
        },

        saveDepartment: function (params, $scope) {
            let promise = $http.post(config.saveDepartment + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('root');
            }

            function rejected(error) {
                console.error(error.status);
                console.error(error.statusText);
                $scope.errorName = error.data._name;
            }
        },

        deleteDepartment: function ($scope, id) {
            $http.post(config.deleteDepartment + '&' + config.ns + 'departmentId=' + id).then(function () {
                $state.reload();
            });
        }
    };
});

