
export default class Validator {

    constructor() {
        $.validator.addMethod(
            "validDate",
            function(value) {
                return value.match(/^\d\d\d\d-\d\d?-\d\d$/);
            },
            "Please enter a date in the format yyyy-dd-mm."
        );

        $.validator.addMethod(
            "notValidName",
            function(value) {
                if(value.match(/^(<script|<script>).*(\/>|<\/script>)$/)){
                    return false;
                }
                return true;
            },
            "Please enter valid name."
        );
    }

    validate() {
        let form = $("form[name='form']");
        form.validate({
            rules: {
                name: {
                    required: true,
                    maxlength: 30,
                    notValidName: true
                },
                age: {
                    required: true,
                    maxlength: 2
                },
                dateOfBirth: {
                    required: true,
                    validDate : true
                },
                email: {
                    required: true,
                    email: true,
                    maxlength: 30
                }
            },

            messages: {
                name: {
                    required: "Please enter name",
                    maxlength: "Name must be less then 30 characters"
                },
                age: {
                    required: "Please enter age",
                    maxlength: "Age must be less then 3 characters"
                },
                dateOfBirth:{
                    required: "Please enter date of birth",
                } ,
                email: {
                    required: "Please enter email",
                    email: "Please enter valid email",
                    maxlength: "Email must be less then 30 characters"
                }
            },
        });

        if (form.valid()) {
            return true;
        }else{
            return false;
        }
    }
}

