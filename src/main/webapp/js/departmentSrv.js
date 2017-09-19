let mainApp = angular.module('mainApp');

mainApp.service('departmentService', DepartmentService);

function DepartmentService($http, $state, $window, toaster) {
    return {

        getAllDepartments: function (page, itemsPerPage) {
            return $http.get('getAllDepartments.action?page=' + page + '&itemsPerPage=' + itemsPerPage);
        },

        getDepartmentSavePage: function (id) {
            return $http.get('saveDepartment.action?department.departmentId=' + id);
        },

        saveDepartment: function (params) {
            let promise = $http.post('saveDepartment.action?' + params);
            promise.then(fulfilled, rejected);

            function fulfilled() {
                $state.go('root');
                toaster.pop('success', 'Info', 'Department successfully saved', null, 'trustedHtml');
            }

            function rejected(error) {
                toaster.pop('error', 'Error', error.data.fieldErrors.name.toString(), null, 'trustedHtml');
            }
        },

        deleteDepartment: function (id) {
            if ($window.confirm('Are you sure you want to delete this department?')) {
                $http.post('deleteDepartment.action?department.departmentId=' + id).then(function () {
                    $state.reload();
                    toaster.pop('success', 'Info', 'Department successfully deleted', null, 'trustedHtml');
                });
            }
        },

        getTotalDepartments: function () {
            return $http.get('getTotalDepartments.action');
        }
    };
}

