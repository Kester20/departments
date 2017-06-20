import DepartmentService from "./saveDepartment";
import Validator from "./form-validation";

export default class Dispatcher {

    constructor(){
        this.departmentService = new DepartmentService();
        this.validator = new Validator();
        this.map = new Map();
        this.map.set('getDepartmentSavePage', () => this.departmentService.getDepartmentSavePage(event));
        this.map.set('showDepartmentSavePage', () => this.departmentService.showDepartmentSavePage());
        this.map.set('saveDepartment', () => this.validator.validate(`department`));
        this.map.set('deleteDepartment', () => this.departmentService.deleteDepartment(event));
    }

    start(){
        $('body').on('click', '.event', (event) => {
            this.map.get(event.target.name)(event);
            event.preventDefault();
        });
    }


}
