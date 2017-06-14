var departmentId;

function getEmployees(id) {
    departmentId = id;
    $.ajax({
        type: "GET",
        url: "/employee/getByDepartment",
        data: "departmentId=" + id,
        dataType: "json",
        success: function (result) {
            showEmployees(result);
        }
    });
}

function showEmployees(collection) {
    var table = "<table>";
    table +=
        '<th>' + '#' + '</th>' +
        '<th>' + 'Name' + '</th>' +
        '<th>' + 'Age' + '</th>' +
        '<th>' + 'Birthday' + '</th>' +
        '<th>' + 'Email' + '</th>' +
        '<th>' + 'Edit' + '</th>' +
        '<th>' + 'Delete' + '</th>';

    $.each(collection, function (index, employee) {
        table += '<tr>' +
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
            '</tr>';
    });

    table += '<tr>' +
        '<td>' + '<a href="javascript:history.back()">Go Back</a>' + '</td>' +
        '<td colspan="3"></td>' +
        '<td colspan="3">' + '<a href="" onclick="showEmployeeSavePage(null, '+ departmentId +');return false;">Add new Employee</a>' + '</td>' +
        '</tr>';
    table += "</table>";

    table = $(table).attr({cellSpacing: 10, border: 1, align: 'center'}).addClass('table');
    $('#app').empty();
    $('#app').append(table);
}