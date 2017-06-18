export default function getFormatDate(date){
    let dd = date.getDate();
    let mm = date.getMonth() + 1;
    let yyyy = date.getFullYear();
    return yyyy + '-' + mm + '-' + dd;
}