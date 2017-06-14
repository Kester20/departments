$(function() {
    $("form[name='departmentForm']").validate({
        rules: {
            name: "required"
        },

        messages: {
            name: "Please enter name"
        },

        submitHandler: function(form) {
            alert('ALERT');
            saveDepartment();
        }
    });
});
