function getSavePage(id) {
    $.ajax({
        type: "GET",
        url: "/department/save",
        dataType: "html",
        data: "departmentId=" + id,
        success: function (result) {
            $('#app').empty();
            $('#app').append(result);
        }
    });
}

function showSavePage() {
    
}