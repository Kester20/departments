import sendRequest from "./controller";
import DepartmentPageRender from "./showDepartments";
import Validator from "./form-validation";

let instance = null;

export default class DepartmentService {

    constructor() {
        if (!instance) {
            instance = this;
        }
        this.validator = new Validator();
        return instance;
    }

    saveDepartment() {
        let app = $('#app');
        if (this.validator.validate()) {
            let name = $('input[name=name]').val();
            let id = $('input[name=departmentId]').val();
            let params;
            id == 'undefined' ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
            sendRequest("POST", "/department/save", "json", params,
                function (result) {
                    new DepartmentPageRender().showDepartments(result);
                },
                function (result) {
                    let errorLabel = $('#name-error');
                    let json_response = result.responseJSON;
                    errorLabel.append(json_response.name);
                    errorLabel.css('display', 'inline');
                });
        }
    }

    deleteDepartment(event) {
        let id = event.target.value;
        let app = $('#app');
        sendRequest("POST", "/department/delete", "json", "departmentId=" + id, function (result) {
            new DepartmentPageRender().showDepartments(result);
        });
    }
}


