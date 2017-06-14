function sendRequest(method, url, dataType, data, success) {
    $.ajax({
        type: method,
        url: url,
        dataType: dataType,
        data: data,
        success: success
    });
}