import sendRequest from "./controller";
import EmployeePageRender from "./showEmployees";
import Validator from "./form-validation";

let instance = null;

export default class EmployeeService {

    constructor() {
        if (!instance) {
            instance = this;
        }
        this.validator = new Validator();
        return instance;
    }

    saveEmployee() {
        if (this.validator.validate()) {
            let employeeId = $("input[name=employeeId]").val();
            let name = $("input[name=name]").val();
            let age = $("input[name=age]").val();
            let dateOfBirth = $("input[name=dateOfBirth]").val();
            let email = $("input[name=email]").val();
            let departmentId = $("input[name=departmentId]").val();
            let params = "";

            if (employeeId != 'undefined') {
                params = "employeeId=" + employeeId + "&";
            }
            params += "name=" + name + "&age=" + age + "&dateOfBirth=" + dateOfBirth + "&email=" + email + "&departmentId=" + departmentId;

            sendRequest("POST", "/employee/save", "json", params,
                function (result) {
                    new EmployeePageRender().showEmployees(result);
                },
                function (result) {
                    let json_response = result.responseJSON;
                    $("#errorName").text(json_response.name);
                    $("#errorAge").text(json_response.age);
                    $("#errorDateOfBirth").text(json_response.dateOfBirth);
                    $("#errorEmail").text(json_response.email);
                })
        }
    }

    deleteEmployee(event) {
        let value = event.target.value.split('|');
        let employeeId = value[0];
        let departmentId = value[1];
        sendRequest("POST", "/employee/delete", "json", "employeeId=" + employeeId + "&departmentId=" + departmentId, function (result) {
            new EmployeePageRender().showEmployees(result);
        })
    }
}
