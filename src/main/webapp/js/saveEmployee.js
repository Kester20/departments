function saveEmployee() {
    var employeeId = $("input[name=employeeId]").val();
    var name = $("input[name=name]").val();
    var age = $("input[name=age]").val();
    var dateOfBirth = $("input[name=dateOfBirth]").val();
    var email = $("input[name=email]").val();
    var departmentId = $("input[name=departmentId]").val();

    var params;
    employeeId == 'undefined' ?
            (params="name=" + name +
            "&age=" + age +
            "&dateOfBirth=" + dateOfBirth +
            "&email=" + email +
            "&departmentId=" + departmentId)
            :
            (params="employeeId=" + employeeId +
            "&name=" + name +
            "&age=" + age +
            "&dateOfBirth=" + dateOfBirth +
            "&email=" + email +
            "&departmentId=" + departmentId);

    $.ajax({
        type: "POST",
        url: "/employee/save",
        dataType: "json",
        data: params,
        success: function (result) {
            showEmployees(result);
        },
    });
}

function deleteEmployee(employeeId, departmentId) {
    $.ajax({
        type: "POST",
        url: "/employee/delete",
        dataType: "json",
        data: "employeeId=" + employeeId + "&departmentId=" + departmentId,
        success: function (result) {
            showEmployees(result);
        }
    });
}

function getEmployeeSavePage(id, departmentId) {
    $.ajax({
        type: "GET",
        url: "/employee/save",
        dataType: "json",
        data: "employeeId=" + id,
        success: function (result) {
            showEmployeeSavePage(result, departmentId);
        }
    });
}

function showEmployeeSavePage(employee, departmentId) {
    var employeeId, name, age, birth, email;
    employee != null ? (
            employeeId = employee.employeeId,
            name = employee.name,
            age = employee.age,
            birth = getFormatDate(new Date(employee.dateOfBirth)),
            email = employee.email) : (name = "", age = "", birth = "", email = "");

    var page = "<h2 align='center'>Enter new values</h2>";

    page += "<table class='table' align='center' cellpadding='10'>";
    page += '<tr>' +
                '<td><input type="text" name="name" placeholder="Name"  value="'+ name +'"></td>' +
            '</tr>';
    page += '<tr>' +
                '<td><input type="number" name="age" placeholder="Age"  value="'+ age +'"></td>' +
            '</tr>';
    page += '<tr>' +
                '<td><input type="text" name="dateOfBirth" placeholder="Date of Birth"  value="'+ birth +'"></td>' +
            '</tr>';
    page += '<tr>' +
                '<td><input type="email" name="email" placeholder="Email"  value="'+ email +'"></td>' +
            '</tr>';
    page += '<tr>' +
                '<td><input type="submit" value="Save" onclick="saveEmployee()"></td>' +
            '</tr>';
    page += "</table>";

    page += '<input type="hidden" name="employeeId" value="' + employeeId + '">';
    page += '<input type="hidden" name="departmentId" value="' + departmentId + '">';

    $('#app').empty();
    $('#app').append(page);
}

