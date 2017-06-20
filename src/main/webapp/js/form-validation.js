import DepartmentService from "./saveDepartment";
import saveEmployee from "./saveEmployee";


export default class Validator {

    constructor() {
    }

    validate(item) {
        let form = $("form[name='form']");
        form.validate({
            rules: {
                name: "required",
                age: "required",
                dateOfBirth: "required",
                email: {
                    required: true,
                    email: true
                }
            },

            messages: {
                name: "Please enter name",
                age: "Please enter age",
                dateOfBirth: "Please enter date of birth",
                email: {
                    required: "Please enter email",
                    email: "Please enter valid email"
                }
            },

            submitHandler: function () {
            }
        });

        if (form.valid()) {
            if (item == "department") {
                new DepartmentService().saveDepartment();
            } else {
                saveEmployee();
            }
        }
    }
}

