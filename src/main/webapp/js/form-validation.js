function validate(item) {
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
            name: "Please enter name"
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

