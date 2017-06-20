
import '../css/style.css';
import 'jquery-validation';
import DepartmentPageRender from './showDepartments';
import Dispatcher from './dispatcher';
import validate from "./form-validation";


$( document ).ready(function() {
    let departmentRender = new DepartmentPageRender();
    departmentRender.getDepartments();

    let dispatcher = new Dispatcher();
    dispatcher.start();
});


