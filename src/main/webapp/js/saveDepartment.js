function getSavePage(id) {
    $.ajax({
        type: "GET",
        url: "/department/save",
        dataType: "json",
        data: "departmentId=" + id,
        success: function (result) {
            $('#app').empty();
            showSavePage(result);
        }
    });
}

function saveDepartment() {
    var name = $("input[name=name]").val();
    var id = $("input[name=departmentId]").val();
    $.ajax({
        type: "POST",
        url: "/department/save",
        dataType: "html",
        data: "name=" + name + "&departmentId=" + id,
        success: function (result) {
            $('#app').empty();
            $('#app').append(result);
        },
    });
}

function showSavePage(department) {
    if (department != null) {
        var name = department.name;
        var id = department.departmentId;
    } else {
        name = "";
    }
    var page = "<h2 align='center'>Enter new value</h2>";

    page += "<table class='table' align='center' cellpadding='10'>";
    page += '<tr>' +
                '<td><input type="text" name="name" placeholder="Name" value="' + name + '"></td>' +
                '<td><input type="submit" value="Save" onclick="saveDepartment()"></td>' +
            '</tr>';

    page += '<tr class="noBorder">' + '<td colspan="2"><span class="errorText"></span></td></tr>';
    page += "</table>";

    page += '<input type="hidden" name="departmentId" value="' + id + '">';
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