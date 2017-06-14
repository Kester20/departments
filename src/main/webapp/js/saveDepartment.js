function getDepartmentSavePage(id) {
    $.ajax({
        type: "GET",
        url: "/department/save",
        dataType: "json",
        data: "departmentId=" + id,
        success: function (result) {
            showDepartmentSavePage(result);
        }
    });
}

function saveDepartment() {
    var name = $("input[name=name]").val();
    var id = $("input[name=departmentId]").val();
    var params;
    id == 'undefined' ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
    $.ajax({
        type: "POST",
        url: "/department/save",
        dataType: "html",
        data: params,
        success: function (result) {
            $('#app').empty();
            $('#app').append(result);
        },
    });
}

function showDepartmentSavePage(department) {
    var name, id;
    department != null ? (name = department.name, id = department.departmentId) : (name = "");

    var page = "<h2 align='center'>Enter new value</h2>";
    page += "<table class='table' align='center' cellpadding='10'>";
    page += '<tr>' +
                '<td><input type="text" name="name" placeholder="Name" value="' + name + '"></td>' +
                '<td><input type="submit" value="Save" onclick="saveDepartment()"></td>' +
            '</tr>';

    page += '<tr class="noBorder">' + '<td colspan="2"><span class="errorText"></span></td></tr>';
    page += "</table>";

    page += '<input type="hidden" name="departmentId" value="' + id + '">';

    $('#app').empty();
    $('#app').append(page);
}

function deleteDepartment(id) {
    $.ajax({
        type: "POST",
        url: "/department/delete",
        dataType: "html",
        data: "departmentId=" + id,
        success: function (result) {
            $('#app').empty();
            $('#app').append(result);
        },
    });
}