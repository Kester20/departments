import sendRequest from "./controller";
import getFormatDate from "./util";
import {showEmployees} from "./showEmployees";

export default function saveEmployee() {
    let employeeId = $("input[name=employeeId]").val();
    let name = $("input[name=name]").val();
    let age = $("input[name=age]").val();
    let dateOfBirth = $("input[name=dateOfBirth]").val();
    let email = $("input[name=email]").val();
    let departmentId = $("input[name=departmentId]").val();
    let params = "";

    if(employeeId != 'undefined'){
        params="employeeId=" + employeeId + "&";
    }
    params+="name=" + name + "&age=" + age + "&dateOfBirth=" + dateOfBirth + "&email=" + email + "&departmentId=" + departmentId;

    sendRequest("POST", "/employee/save", "json", params, function (result) {
        showEmployees(result);
    })
}

export function deleteEmployee(employeeId, departmentId) {
    sendRequest("POST", "/employee/delete", "json", "employeeId=" + employeeId + "&departmentId=" + departmentId, function (result) {
        showEmployees(result);
    })
}

export function getEmployeeSavePage(id, departmentId) {
    sendRequest("GET", "/employee/save", "json", "employeeId=" + id, function (result) {
       showEmployeeSavePage(result, departmentId);
    });
}

export function showEmployeeSavePage(employee, departmentId) {
    let app = $('#app');
    let employeeId, name, age, birth, email;
    employee != null ? (
            employeeId = employee.employeeId,
            name = employee.name,
            age = employee.age,
            birth = getFormatDate(new Date(employee.dateOfBirth)),
            email = employee.email) : (name = "", age = "", birth = "", email = "");

    let page = $('<h2></h2>').text('Enter new values').attr('align', 'center');

    let form = $('<form></form>').attr('name', 'form');
    let table = $('<table></table>').addClass('table').attr({align: 'center', cellpadding: 10});
    table.append('<tr>' +
                    '<td colspan="2"><input type="text" name="name" placeholder="Name"  value="'+ name +'"></td>' +
                '</tr>');
    table.append('<tr>' +
                    '<td colspan="2"><input type="number" name="age" placeholder="Age"  value="'+ age +'"></td>' +
                '</tr>');
    table.append('<tr>' +
                    '<td colspan="2"><input type="text" name="dateOfBirth" placeholder="Date of Birth"  value="'+ birth +'"></td>' +
                '</tr>');
    table.append('<tr>' +
                    '<td colspan="2"><input type="email" name="email" placeholder="Email"  value="'+ email +'"></td>' +
                '</tr>');

    let save = $('<button></button>').text('Save');
    save.delegate(this, 'click', function () {
        //validate(`employee`);
        saveEmployee();
        return false;
    });

    table.append($('<tr>')
        .append($('<td>' + '<a href="javascript:history.back()">Go Back</a>' + '</td>'))
        .append($('<td></td>').append(save)));

    form.append(table);
    page.append(form);

    page.append('<input type="hidden" name="employeeId" value="' + employeeId + '">');
    page.append('<input type="hidden" name="departmentId" value="' + departmentId + '">');

    app.empty();
    app.append(page);
}

