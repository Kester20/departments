export default function sendRequest(method, url, dataType, data, success, error) {
    $.ajax({
        type: method,
        url: url,
        dataType: dataType,
        data: data,
        success: success,
        error: error
    });
}