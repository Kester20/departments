import DepartmentService from "./saveDepartment";
import DepartmentPageRender from "./showDepartments";

export default class Dispatcher {

    constructor(){
        this.departmentRender = new DepartmentPageRender();
        this.departmentService = new DepartmentService();
        this.map = new Map();
        this.map.set('getDepartmentSavePage', () => this.departmentService.getDepartmentSavePage(event));
        this.map.set('showDepartmentSavePage', () => this.departmentService.showDepartmentSavePage());
        this.map.set('saveDepartment', () => this.departmentService.saveDepartment());
        this.map.set('deleteDepartment', () => this.departmentService.deleteDepartment(event));
    }

    start(){
        this.departmentRender.getDepartments();
        $('body').on('click', '.event', (event) => {
            this.map.get(event.target.name)(event);
            event.preventDefault();
        });
    }


}
