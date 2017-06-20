import sendRequest from "./controller";
import getEmployees from "./showEmployees";

let instance = null;

export default class DepartmentPageRender {

    constructor() {
        if (!instance) {
            instance = this;
        }
        return instance;
    }

    getDepartments() {
        sendRequest("GET", "/department/getAll", "json", null, function (result) {
            new DepartmentPageRender().showDepartments(result);
        });
    }

    showDepartments(collection) {
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

            let edit = $('<a></a>');
            edit.text('Edit');
            edit.addClass('edit event');
            edit.css('cursor', 'pointer');
            edit.val(department.departmentId);
            edit.attr('name', 'getDepartmentSavePage');
            tr.append($('<td></td>').append(edit));

            let deleteDep = $('<a></a>');
            deleteDep.text('X');
            deleteDep.addClass('x event');
            deleteDep.css('cursor', 'pointer');
            deleteDep.val(department.departmentId);
            deleteDep.attr('name', 'deleteDepartment');
            tr.append($('<td></td>').append(deleteDep));

            let employees = $('<a></a>');
            employees.text('Employees');
            employees.addClass('emp');
            employees.css('cursor', 'pointer');
            employees.val(department.departmentId);
            employees.attr('name', 'getEmployees');
            tr.append($('<td></td>').append(employees));

            table.append(tr);
        });

        let tr = $('<tr></tr>').append('<td colspan="3" class="hide"></td>');
        let add = $('<a></a>');
        add.text('Add new Department');
        add.addClass('add event');
        add.css('cursor', 'pointer');
        add.attr('name', 'showDepartmentSavePage');
        tr.append($('<td colspan="2"></td>').append(add));
        table.append(tr);

        app.empty();
        app.append(table);
    }
}

