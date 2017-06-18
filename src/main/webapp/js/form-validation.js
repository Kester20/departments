import saveDepartment from "./saveDepartment";

export default function validate(item) {
    $("form[name='form']").validate({
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

        submitHandler: function() {
            if(item == "department"){
                saveDepartment();
            }else{
                saveEmployee();
            }
        }
    });
}

