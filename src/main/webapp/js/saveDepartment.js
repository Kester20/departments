function saveDepartment() {
    let app = $('#app');
    let name = $('input[name=name]').val();
    let id = $('input[name=departmentId]').val();
    let params;
    id == 'undefined' ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
    sendRequest("POST", "/department/save", "html", params, function (result) {
        app.empty();
        app.append(result);
    });
}

function deleteDepartment(id) {
    let app = $('#app');
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
    let app = $('#app');
    let name, id;
    department != null ? (name = department.name, id = department.departmentId) : (name = "");

    let page = $('<h2></h2>').text('Enter new value').attr('align', 'center');

    let form = $('<form></form>').attr('name', 'form');
    let table = $('<table></table>').addClass('table').attr({align:'center', cellpadding: 10});
    table.append('<tr>' +
                    '<td><input type="text" name="name" placeholder="Name" value="' + name + '"></td>' +
                    '<td><button type="submit" onclick="validate(`department`)">Save</button></td>' +
                '</tr>');
    form.append(table);

    page.append(form);
    page.append('<input type="hidden" name="departmentId" value="' + id + '">');

    app.empty();
    app.append(page);
}

