function saveEmployee() {
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

function deleteEmployee(employeeId, departmentId) {
    sendRequest("POST", "/employee/delete", "json", "employeeId=" + employeeId + "&departmentId=" + departmentId, function (result) {
        showEmployees(result);
    })
}

function getEmployeeSavePage(id, departmentId) {
    sendRequest("GET", "/employee/save", "json", "employeeId=" + id, function (result) {
       showEmployeeSavePage(result, departmentId);
    });
}

function showEmployeeSavePage(employee, departmentId) {
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
                    '<td><input type="text" name="name" placeholder="Name"  value="'+ name +'"></td>' +
                '</tr>');
    table.append('<tr>' +
                    '<td><input type="number" name="age" placeholder="Age"  value="'+ age +'"></td>' +
                '</tr>');
    table.append('<tr>' +
                    '<td><input type="text" name="dateOfBirth" placeholder="Date of Birth"  value="'+ birth +'"></td>' +
                '</tr>');
    table.append('<tr>' +
                    '<td><input type="email" name="email" placeholder="Email"  value="'+ email +'"></td>' +
                '</tr>');
    table.append('<tr>' +
                    '<td><input type="submit" value="Save" onclick="validate(`employee`)"></td>' +
                '</tr>');

    form.append(table);
    page.append(form);

    page.append('<input type="hidden" name="employeeId" value="' + employeeId + '">');
    page.append('<input type="hidden" name="departmentId" value="' + departmentId + '">');

    app.empty();
    app.append(page);
}

