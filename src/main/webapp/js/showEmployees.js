import sendRequest from "./controller";
import getFormatDate from "./util";
import DepartmentPageRender from "./showDepartments";

let instance = null;
let departmentId;

export default class EmployeePageRender {

    constructor() {
        if (!instance) {
            instance = this;
        }
        return instance;
    }

    getEmployees(event) {
        departmentId = event.target.value;
        sendRequest("GET", "/employee/getByDepartment", "json", "departmentId=" + departmentId, function (result) {
            new EmployeePageRender().showEmployees(result);
        });
    }

    showEmployees(collection) {
        let app = $('#app');
        app.empty();

        app.append($('<h3></h3>').text('Employees').attr('align', 'center'));

        let table = $('<table></table>').addClass('mdl-data-table').attr({align: 'center'});

        table.append('<th class="mdl-data-table__cell--non-numeric">' + '#' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Name' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Age' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Birthday' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Email' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Edit' + '</th>');
        table.append('<th class="mdl-data-table__cell--non-numeric">' + 'Delete' + '</th>');

        $.each(collection, function (index, employee) {
            let tr = $('<tr></tr>');
            tr.append($('<td></td>').text(index + 1));
            tr.append($('<td></td>').text(employee.name));
            tr.append($('<td></td>').text(employee.age));
            tr.append($('<td></td>').text(getFormatDate(new Date(employee.dateOfBirth))));
            tr.append($('<td></td>').text(employee.email));

            let edit = $('<button></button>');
            edit.text('Edit');
            edit.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect');
            edit.val(employee.employeeId + "|" + departmentId);
            edit.attr('name', 'getEmployeeSavePage');
            tr.append($('<td></td>').append(edit));


            let deleteEmp = $('<button></button>');
            deleteEmp.text('Delete');
            deleteEmp.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect');
            deleteEmp.val(employee.employeeId + "|" + departmentId);
            deleteEmp.attr('name', 'deleteEmployee');
            tr.append($('<td></td>').append(deleteEmp));

            table.append(tr);
        });


        let back = $('<button></button>').on('click', function () {
            new DepartmentPageRender().getDepartments();
            return false;
        });
        back.text('Go back');
        back.addClass('mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent');
        let tr = $('<tr></tr>')
            .append($('<td colspan="2"></td>').append(back))
            .append('<td colspan="3"></td>');

        let add = $('<button></button>');
        add.text('Add new Employee');
        add.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored');
        add.css('cursor', 'pointer');
        add.val(departmentId);
        add.attr('name', 'showEmployeeSavePage');
        tr.append($('<td colspan="3"></td>').append(add));

        table.append(tr);
        app.append(table);
    }

    getEmployeeSavePage(event) {
        let value = event.target.value.split('|');
        let id = value[0];
        let departmentId = value[1];
        sendRequest("GET", "/employee/save", "json", "employeeId=" + id, function (result) {
            new EmployeePageRender().showEmployeeSavePage(null, result, departmentId);
        });
    }

    showEmployeeSavePage(event, employee, departmentId) {
        if (departmentId == null) {
            departmentId = event.target.value;
        }
        let app = $('#app');
        let employeeId, name, age, birth, email;
        employee != null ? (
            employeeId = employee.employeeId,
                name = employee.name,
                age = employee.age,
                birth = getFormatDate(new Date(employee.dateOfBirth)),
                email = employee.email) : (name = "", age = "", birth = "", email = "");

        let page = $('<h3></h3>');
        page.text('Enter new values').attr('align', 'center');

        let form = $('<form></form>').attr('name', 'form');
        let table = $('<table class="mdl-data-table"></table>');
        table.addClass('table');
        table.attr({align: 'center', cellpadding: 10});
        table.append(
            '<tr>' +
            '<td colspan="2"><input class="mdl-textfield__input" type="text" name="name" placeholder="Name"  value="' + name + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorName" class="errorText"></span> </td>' +
            '</tr>');

        table.append(
            '<tr>' +
            '<td colspan="2"><input class="mdl-textfield__input" type="number" name="age" placeholder="Age"  value="' + age + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorAge" class="errorText"></span> </td>' +
            '</tr>');

        table.append(
            '<tr>' +
            '<td colspan="2"><input class="mdl-textfield__input" type="text" name="dateOfBirth" placeholder="Date of Birth"  value="' + birth + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorDateOfBirth" class="errorText"></span> </td>' +
            '</tr>');

        table.append(
            '<tr>' +
            '<td colspan="2"><input class="mdl-textfield__input" type="email" name="email" placeholder="Email"  value="' + email + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorEmail" class="errorText"></span> </td>' +
            '</tr>');

        let save = $('<button type="submit"></button>');
        save.text('Save');
        save.addClass('event event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored');
        save.attr('name', 'saveEmployee');

        let back = $('<button></button>');
        back.text('Go Back');
        back.addClass('event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent');
        back.val(departmentId);
        back.attr('name', 'getEmployees');

        table
            .append($('<tr>')
                .append($('<td></td>').append(back))
                .append($('<td colspan="2"></td>').append(save)));

        form.append(table);
        page.append(form);

        page.append('<input type="hidden" name="employeeId" value="' + employeeId + '">');
        page.append('<input type="hidden" name="departmentId" value="' + departmentId + '">');

        app.empty();
        app.append(page);
    }
}