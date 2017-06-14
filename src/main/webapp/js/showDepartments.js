$( document ).ready(function() {
    $.ajax({
        type: "GET",
        url: "/department/getAll",
        dataType: "json",
        success: function (result) {
            showDepartments(result);
        }
    });
});

function showDepartments(collection) {
    var table = "<table>";
    table +=
        '<th>' + '#' + '</th>' +
        '<th>' + 'Name' + '</th>' +
        '<th>' + 'Edit' + '</th>' +
        '<th>' + 'Delete' + '</th>' +
        '<th>' + 'Employees' + '</th>';

    var employees = '<a href="/employee/getByDepartment?departmentId=${department.departmentId}">Employees</a>';

    $.each(collection, function (index, department) {
        table += '<tr>' +
            '<td>' + (index + 1) + '</td>' +
            '<td>' + department.name + '</td>' +
            '<td>' + '<a href="" onclick="getSavePage(' + department.departmentId + ');return false;">Edit</a>' + '</td>' +
            '<td>' + '<a href="" onclick="deleteDepartment(' + department.departmentId + ');return false;" class="x">X</a>' + '</td>' +
            '<td>' + employees + '</td>' +
            '</tr>';
    });

    table += '<tr>' +
        '<td>' + '<a href="javascript:history.back()">Go Back</a>' + '</td>' +
        '<td colspan="2"></td>' +
        '<td colspan="2">' + '<a href="" onclick="showSavePage();return false;">Add new Department</a>' + '</td>' +
        '</tr>';
    table += "</table>";

    table = $(table).attr({cellSpacing: 10, border: 1, align: 'center'}).addClass('table');
    $('#app').append(table);
}
