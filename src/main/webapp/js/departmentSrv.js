let mainApp = angular.module('mainApp');

mainApp.service('departmentService', DepartmentService);

function DepartmentService($http, $state, $window, toaster, config) {
    return {
        getAllDepartments: function (page, itemsPerPage) {
            return $http.get(config.getAllDepartments +
                '&' + config.ns + 'page=' + page +
                '&' + config.ns + 'itemsPerPage=' + itemsPerPage);
        },

        getDepartmentSavePage: function (id) {
            return $http.get(config.saveDepartment + '&' + config.ns +  'departmentId=' + id);
        },

        saveDepartment: function (params) {
            let promise = $http.post(config.saveDepartment + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('root');
                toaster.pop('success', 'Info', 'Department successfully saved', null, 'trustedHtml');
            }
            function rejected(error) {
                toaster.pop('error', 'Error', error.data._name, null, 'trustedHtml');
            }
        },

        deleteDepartment: function (id) {
            if ($window.confirm('Are you sure you want to delete this department?')) {
                $http.post(config.deleteDepartment + '&' + config.ns + 'departmentId=' + id).then(function () {
                    $state.reload();
                    toaster.pop('success', 'Info', 'Department successfully deleted', null, 'trustedHtml');
                });
            }
        },

        getTotalDepartments: function () {
            return $http.get(config.getTotalDepartments);
        }
    };
}

