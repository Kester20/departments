let departmentId;

function getEmployees(id) {
    departmentId = id;
    sendRequest("GET", "/employee/getByDepartment", "json", "departmentId=" + id, function (result) {
        showEmployees(result);
    });
}

function showEmployees(collection) {
    let app = $('#app');
    let table = $('<table></table>').addClass('table').attr({cellSpacing: 10, border: 1, align: 'center'});

    table.append('<th>' + '#' + '</th>');
    table.append('<th>' + 'Name' + '</th>');
    table.append('<th>' + 'Age' + '</th>');
    table.append('<th>' + 'Birthday' + '</th>');
    table.append('<th>' + 'Email' + '</th>');
    table.append('<th>' + 'Edit' + '</th>');
    table.append('<th>' + 'Delete' + '</th>');

    $.each(collection, function (index, employee) {
        table.append('<tr>' +
            '<td>' + (index + 1) + '</td>' +
            '<td>' + employee.name + '</td>' +
            '<td>' + employee.age + '</td>' +
            '<td>' + getFormatDate(new Date(employee.dateOfBirth)) + '</td>' +
            '<td>' + employee.email + '</td>' +
            '<td>' +
                '<a href="" onclick="getEmployeeSavePage(' + employee.employeeId + ', ' + departmentId +');' +
                'return false;">Edit</a>' +
            '</td>' +
            '<td>' + '<a href="" onclick="deleteEmployee(' + employee.employeeId + ', ' + departmentId +');' +
                'return false;" class="x">X</a>' +
            '</td>' +
            '</tr>');
    });

    table.append('<tr>' +
        '<td>' + '<a href="javascript:history.back()">Go Back</a>' + '</td>' +
        '<td colspan="3"></td>' +
        '<td colspan="3">' + '<a href="" onclick="showEmployeeSavePage(null, '+ departmentId +');return false;">Add new Employee</a>' + '</td>' +
        '</tr>');

    app.empty();
    app.append(table);
}