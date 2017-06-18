import sendRequest from "./controller";
import {getDepartmentSavePage} from "./saveDepartment";
import {showDepartmentSavePage} from "./saveDepartment";
import {deleteDepartment} from "./saveDepartment";
import getEmployees from "./showEmployees";

export default function start() {
    sendRequest("GET", "/department/getAll", "json", null, function (result) {
        showDepartments(result);
    });
};

function showDepartments(collection) {
    let app = $('#app');

    app.append($('<h2></h2>').text('Departments').attr('align', 'center'));

    let table = $('<table></table>').addClass('table').attr({cellSpacing: 15, border: 1, align: 'center'});

    table.append('<th>' + '#' + '</th>');
    table.append('<th>' + 'Name' + '</th>');
    table.append('<th>' + 'Edit' + '</th>');
    table.append('<th>' + 'Delete' + '</th>');
    table.append('<th>' + 'Employees' + '</th>');

    $.each(collection, function (index, department) {
        let tr = $('<tr></tr>');
        tr.append($('<td></td>').text(index + 1));
        tr.append($('<td></td>').text(department.name));

        let edit = $('<a></a>').text('Edit').addClass('edit').css( 'cursor', 'pointer' );
        edit.delegate(this, 'click', function () {
            getDepartmentSavePage(department.departmentId);
            return false;
        });
        tr.append($('<td></td>').append(edit));

        let deleteDep = $('<a></a>').text('X').addClass('x').css( 'cursor', 'pointer' );;
        deleteDep.delegate(this, 'click', function () {
            deleteDepartment(department.departmentId);
            return false;
        });
        tr.append($('<td></td>').append(deleteDep));

        let employees = $('<a></a>').text('Employees').addClass('emp').css( 'cursor', 'pointer' );
        employees.delegate(this, 'click', function () {
            getEmployees(department.departmentId);
        });
        tr.append($('<td></td>').append(employees));

        table.append(tr);
    });

    let tr = $('<tr></tr>').append('<td colspan="3" class="hide"></td>');
    let add = $('<a></a>').text('Add new Department').addClass('add').css('cursor', 'pointer');
    add.delegate(this, 'click', function () {
        showDepartmentSavePage();
        return false;
    });
    tr.append($('<td colspan="2"></td>').append(add));
    table.append(tr);

    app.append(table);
}
