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
            employees.addClass('emp event');
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

    getDepartmentSavePage(event) {
        let id = event.target.value;
        sendRequest("GET", "/department/save", "json", "departmentId=" + id, function (result) {
            new DepartmentPageRender().showDepartmentSavePage(result);
        });
    }

    showDepartmentSavePage(department) {
        let app = $('#app');
        let name, id;
        department != null ? (name = department.name, id = department.departmentId) : (name = "");

        let page = $('<h2></h2>');
        page.text('Enter new value');
        page.attr('align', 'center');

        let form = $('<form></form>').attr('name', 'form');
        let table = $('<table></table>');
        table.addClass('table');
        table.attr({align: 'center', cellpadding: 10});
        table.append(
            '<tr>' +
            '<td colspan="2"><input type="text" name="name" placeholder="Name" value="' + name + '"></td>' +
            '</tr>');

        let save = $('<button type="submit"></button>');
        save.text('Save');
        save.addClass('event');
        save.attr('name', 'saveDepartment');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="error" class="errorText"></span> </td>' +
            '</tr>');

        let back = $('<a></a>').on('click', function () {
            new DepartmentPageRender().getDepartments();
            return false;
        });
        back.text('Go back');
        back.css('cursor', 'pointer');
        back.addClass('edit');

        table.append($('<tr>')
            .append($('<td></td>').append(back))
            .append($('<td></td>').append(save)));
        form.append(table);

        page.append(form);
        page.append('<input type="hidden" name="departmentId" value="' + id + '">');

        app.empty();
        app.append(page);
    }
}

