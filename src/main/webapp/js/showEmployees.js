import sendRequest from "./controller";
import getFormatDate from "./util";
import {getEmployeeSavePage} from "./saveEmployee";
import {deleteEmployee} from "./saveEmployee";
import {showEmployeeSavePage} from "./saveEmployee";

let departmentId;

export default function getEmployees(id) {
    departmentId = id;
    sendRequest("GET", "/employee/getByDepartment", "json", "departmentId=" + id, function (result) {
        showEmployees(result);
    });
}

export function showEmployees(collection) {
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
        let tr = $('<tr></tr>');
        tr.append($('<td></td>').text(index + 1));
        tr.append($('<td></td>').text(employee.name));
        tr.append($('<td></td>').text(employee.age));
        tr.append($('<td></td>').text(getFormatDate(new Date(employee.dateOfBirth))));
        tr.append($('<td></td>').text(employee.email));

        let edit = $('<a></a>').text('Edit').addClass('edit').css( 'cursor', 'pointer' );
        edit.delegate(this, 'click', function () {
            getEmployeeSavePage(employee.employeeId, departmentId);
            return false;
        });
        tr.append($('<td></td>').append(edit));

        let deleteEmp = $('<a></a>').text('X').addClass('x').css( 'cursor', 'pointer' );;
        deleteEmp.delegate(this, 'click', function () {
            deleteEmployee(employee.employeeId, departmentId);
            return false;
        });
        tr.append($('<td></td>').append(deleteEmp));

        table.append(tr);
    });

    let tr = $('<tr></tr>')
        .append($('<td></td>').append('<a href="javascript:history.back()">Go Back</a>'))
        .append('<td colspan="3"></td>');

    let add = $('<a></a>').text('Add new Employee').addClass('add').css('cursor', 'pointer');
    add.delegate(this, 'click', function () {
        showEmployeeSavePage(null, departmentId);
        return false;
    });
    tr.append($('<td colspan="3"></td>').append(add));
    table.append(tr);

    app.empty();
    app.append(table);
}