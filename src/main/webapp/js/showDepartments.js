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
        app.empty();

        app.append($('<h3></h3>').text('Departments').attr('align', 'center'));

        let table = $('<table></table>').addClass('mdl-data-table').attr({align: 'center'});

        table.append('<th class="mdl-data-table__cell--non-numeric">' + '#' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Name' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Edit' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Delete' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Employees' + '</th>');


        $.each(collection, function (index, department) {
            let tr = $('<tr></tr>');
            tr.append($('<td></td>').text(index + 1));
            tr.append($('<td></td>').text(department.name));

            let edit = $('<button></button>');
            edit.text('Edit');
            edit.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect');
            edit.val(department.departmentId);
            edit.attr('name', 'getDepartmentSavePage');
            tr.append($('<td></td>').append(edit));

            let deleteDep = $('<button></button>');
            deleteDep.text('Delete');
            deleteDep.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect');
            deleteDep.val(department.departmentId);
            deleteDep.attr('name', 'deleteDepartment');
            tr.append($('<td align="center"></td>').append(deleteDep));

            let employees = $('<button></button>');
            employees.text('Employees');
            employees.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent');
            employees.val(department.departmentId);
            employees.attr('name', 'getEmployees');
            tr.append($('<td></td>').append(employees));

            table.append(tr);
        });

        let tr = $('<tr></tr>').append('<td colspan="3" class="hide"></td>');
        let add = $('<button></button>');
        add.text('Add new Department');
        add.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored');
        add.attr('name', 'showDepartmentSavePage');
        tr.append($('<td colspan="2"></td>').append(add));
        table.append(tr);

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

        let page = $('<h3></h3>');
        page.text('Enter new value');
        page.attr('align', 'center');

        let form = $('<form></form>').attr('name', 'form');
        let table = $('<table></table>');
        table.css('margin-top', '100px');
        table.addClass('mdl-data-table');
        table.attr({align: 'center', cellpadding: 10});
        table.append(
            '<tr>' +
            '<td colspan="2">' +
            '<input class="mdl-textfield__input" type="text" name="name" placeholder="Name" value="' + name + '">' +
            '<label id="name-error" class="error" for="name"></label>' +
            '</td>' +
            '</tr>');

        let save = $('<button type="submit"></button>');
        save.text('Save');
        save.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored');
        save.attr('name', 'saveDepartment');

        /*table.append(
            '<tr class="hide">' +
            '<td colspan="2"><span id="error" class="error"></span> </td>' +
            '</tr>');*/

        let back = $('<button></button>').on('click', function () {
            new DepartmentPageRender().getDepartments();
            return false;
        });
        back.text('Go back');
        back.css('cursor', 'pointer');
        back.addClass('mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent');

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

