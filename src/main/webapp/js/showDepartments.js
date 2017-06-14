$( document ).ready(function() {
    sendRequest("GET", "/department/getAll", "json", null, function (result) {
        showDepartments(result);
    });
});

function showDepartments(collection) {
    var app = $('#app');
    var table = "<table>";
    table +=
        '<th>' + '#' + '</th>' +
        '<th>' + 'Name' + '</th>' +
        '<th>' + 'Edit' + '</th>' +
        '<th>' + 'Delete' + '</th>' +
        '<th>' + 'Employees' + '</th>';

    $.each(collection, function (index, department) {
        table += '<tr>' +
            '<td>' + (index + 1) + '</td>' +
            '<td>' + department.name + '</td>' +
            '<td>' + '<a href="" onclick="getDepartmentSavePage(' + department.departmentId + ');return false;">Edit</a>' + '</td>' +
            '<td>' + '<a href="" onclick="deleteDepartment(' + department.departmentId + ');return false;" class="x">X</a>' + '</td>' +
            '<td>' + '<a href="" onclick="getEmployees(' + department.departmentId + '); return false;">Employees</a>' + '</td>' +
            '</tr>';
    });

    table += '<tr>' +
        '<td>' + '<a href="javascript:history.back()">Go Back</a>' + '</td>' +
        '<td colspan="2"></td>' +
        '<td colspan="2">' + '<a href="" onclick="showDepartmentSavePage();return false;">Add new Department</a>' + '</td>' +
        '</tr>';
    table += "</table>";

    table = $(table).attr({cellSpacing: 10, border: 1, align: 'center'}).addClass('table');
    app.append(table);
}
