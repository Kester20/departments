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

            let edit = $('<a></a>');
            edit.text('Edit');
            edit.addClass('edit event');
            edit.css( 'cursor', 'pointer' );
            edit.val(employee.employeeId + "|" + departmentId);
            edit.attr('name', 'getEmployeeSavePage');
            tr.append($('<td></td>').append(edit));


            let deleteEmp = $('<a></a>');
            deleteEmp.text('X');
            deleteEmp.addClass('x event');
            deleteEmp.css( 'cursor', 'pointer' );
            deleteEmp.val(employee.employeeId + "|" + departmentId);
            deleteEmp.attr('name', 'deleteEmployee');
            tr.append($('<td></td>').append(deleteEmp));

            table.append(tr);
        });


        let back = $('<a></a>').on('click', function () {
            new DepartmentPageRender().getDepartments();
            return false;
        });
        back.text('Go back');
        back.css('cursor', 'pointer');
        back.addClass('edit');
        let tr = $('<tr></tr>')
            .append($('<td></td>').append(back))
            .append('<td colspan="3"></td>');

        let add = $('<a></a>');
        add.text('Add new Employee');
        add.addClass('add event');
        add.css('cursor', 'pointer');
        add.val(departmentId);
        add.attr('name', 'showEmployeeSavePage');
        tr.append($('<td colspan="3"></td>').append(add));

        table.append(tr);

        app.empty();
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

        let page = $('<h2></h2>');
        page.text('Enter new values').attr('align', 'center');

        let form = $('<form></form>').attr('name', 'form');
        let table = $('<table></table>');
        table.addClass('table');
        table.attr({align: 'center', cellpadding: 10});
        table.append(
            '<tr>' +
            '<td colspan="2"><input type="text" name="name" placeholder="Name"  value="' + name + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorName" class="errorText"></span> </td>' +
            '</tr>');

        table.append(
            '<tr>' +
            '<td colspan="2"><input type="number" name="age" placeholder="Age"  value="' + age + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorAge" class="errorText"></span> </td>' +
            '</tr>');

        table.append(
            '<tr>' +
            '<td colspan="2"><input type="text" name="dateOfBirth" placeholder="Date of Birth"  value="' + birth + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorDateOfBirth" class="errorText"></span> </td>' +
            '</tr>');

        table.append(
            '<tr>' +
            '<td colspan="2"><input type="email" name="email" placeholder="Email"  value="' + email + '"></td>' +
            '</tr>');

        table.append(
            '<tr class="noBorder">' +
            '<td colspan="2"><span id="errorEmail" class="errorText"></span> </td>' +
            '</tr>');

        let save = $('<button type="submit"></button>');
        save.text('Save');
        save.addClass('event');
        save.attr('name', 'saveEmployee');

        let back = $('<a></a>');
        back.text('Go Back');
        back.addClass('edit event');
        back.css('cursor', 'pointer');
        back.val(departmentId);
        back.attr('name', 'getEmployees');

        table
            .append($('<tr>')
                .append($('<td></td>').append(back))
                .append($('<td></td>').append(save)));

        form.append(table);
        page.append(form);

        page.append('<input type="hidden" name="employeeId" value="' + employeeId + '">');
        page.append('<input type="hidden" name="departmentId" value="' + departmentId + '">');

        app.empty();
        app.append(page);
    }
}