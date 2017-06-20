import sendRequest from "./controller";
import DepartmentPageRender from "./showDepartments";

let instance = null;

export default class DepartmentService {

    constructor() {
        if(!instance){
            instance = this;
        }
        return instance;
    }

    saveDepartment() {
        let app = $('#app');
        let name = $('input[name=name]').val();
        let id = $('input[name=departmentId]').val();
        let params;
        id == 'undefined' ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
        sendRequest("POST", "/department/save", "json", params, function (result) {
            new DepartmentPageRender().showDepartments(result);
        });
    }

    deleteDepartment(event) {
        let id = event.target.value;
        let app = $('#app');
        sendRequest("POST", "/department/delete", "json", "departmentId=" + id, function (result) {
            new DepartmentPageRender().showDepartments(result);
        });
    }

    getDepartmentSavePage(event) {
        let id = event.target.value;
        sendRequest("GET", "/department/save", "json", "departmentId=" + id, function (result) {
            new DepartmentService().showDepartmentSavePage(result);
        });
    }

    showDepartmentSavePage(department) {
        let app = $('#app');
        let name, id;
        department != null ? (name = department.name, id = department.departmentId) : (name = "");

        let page = $('<h2></h2>').text('Enter new value').attr('align', 'center');

        let form = $('<form></form>').attr('name', 'form');
        let table = $('<table></table>').addClass('table').attr({align: 'center', cellpadding: 10});
        table.append('<tr>' +
            '<td colspan="2"><input type="text" name="name" placeholder="Name" value="' + name + '"></td>' +
            '</tr>');

        let save = $('<button type="submit"></button>');
        save.text('Save');
        save.addClass('event');
        save.attr('name', 'saveDepartment');


        table.append($('<tr>')
            .append($('<td>' + '<a href="javascript:history.back()">Go Back</a>' + '</td>'))
            .append($('<td></td>').append(save)));
        form.append(table);

        page.append(form);
        page.append('<input type="hidden" name="departmentId" value="' + id + '">');

        app.empty();
        app.append(page);
    }
}


