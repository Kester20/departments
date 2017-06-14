function getFormatDate(date){
    var dd = date.getDate();
    var mm = date.getMonth()+1; //January is 0!
    var yyyy = date.getFullYear();
    var formatDate = yyyy + '-' + mm + '-' + dd;
    return formatDate;
}