import sendRequest from "./controller";
import validate from "./form-validation";

export default function saveDepartment() {
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

export function deleteDepartment(id) {
    let app = $('#app');
    sendRequest("POST", "/department/delete", "html", "departmentId=" + id, function (result) {
        app.empty();
        app.append(result);
    });
}

export function getDepartmentSavePage(id) {
    sendRequest("GET", "/department/save", "json", "departmentId=" + id, function (result) {
        showDepartmentSavePage(result);
    });
}

export function showDepartmentSavePage(department) {
    let app = $('#app');
    let name, id;
    department != null ? (name = department.name, id = department.departmentId) : (name = "");

    let page = $('<h2></h2>').text('Enter new value').attr('align', 'center');

    let form = $('<form></form>').attr('name', 'form');
    let table = $('<table></table>').addClass('table').attr({align:'center', cellpadding: 10});
    table.append('<tr>' +
                    '<td colspan="2"><input type="text" name="name" placeholder="Name" value="' + name + '"></td>' +
                '</tr>');

    let save = $('<button></button>').text('Save');
    save.delegate(this, 'click', function () {
        //validate(`department`);
        saveDepartment();
        return false;
    });

    table.append($('<tr>')
                .append($('<td>' + '<a href="javascript:history.back()">Go Back</a>' + '</td>'))
                .append($('<td></td>').append(save)));
    form.append(table);

    page.append(form);
    page.append('<input type="hidden" name="departmentId" value="' + id + '">');

    app.empty();
    app.append(page);
}

