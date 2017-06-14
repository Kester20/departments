function saveDepartment() {
    var app = $('#app');
    var name = $('input[name=name]').val();
    var id = $('input[name=departmentId]').val();
    var params;
    id == 'undefined' ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
    sendRequest("POST", "/department/save", "html", params, function (result) {
        app.empty();
        app.append(result);
    });
}

function deleteDepartment(id) {
    var app = $('#app');
    sendRequest("POST", "/department/delete", "html", "departmentId=" + id, function (result) {
        app.empty();
        app.append(result);
    });
}

function getDepartmentSavePage(id) {
    sendRequest("GET", "/department/save", "json", "departmentId=" + id, function (result) {
        showDepartmentSavePage(result);
    });
}

function showDepartmentSavePage(department) {
    var app = $('#app');
    var name, id;
    department != null ? (name = department.name, id = department.departmentId) : (name = "");

    var page = "<h2 align='center'>Enter new value</h2>";

    page += '<form name="form">';
    page += "<table class='table' align='center' cellpadding='10'>";
    page += '<tr>' +
                '<td><input type="text" name="name" placeholder="Name" value="' + name + '"></td>' +
                '<td><button type="submit" onclick="validate(`department`)">Save</button></td>' +
            '</tr>';
    page += "</table>";
    page += '</form>';

    page += '<input type="hidden" name="departmentId" value="' + id + '">';

    app.empty();
    app.append(page);
}

