import DepartmentService from "./saveDepartment";
import DepartmentPageRender from "./showDepartments";
import EmployeePageRender from "./showEmployees";
import EmployeeService from "./saveEmployee";

export default class Dispatcher {

    constructor(){
        this.departmentRender = new DepartmentPageRender();
        this.departmentService = new DepartmentService();
        this.employeeRender = new EmployeePageRender();
        this.employeeService = new EmployeeService();
        this.map = new Map();
        this.map.set('getDepartmentSavePage', () => this.departmentRender.getDepartmentSavePage(event));
        this.map.set('showDepartmentSavePage', () => this.departmentRender.showDepartmentSavePage());
        this.map.set('saveDepartment', () => this.departmentService.saveDepartment());
        this.map.set('deleteDepartment', () => this.departmentService.deleteDepartment(event));
        this.map.set('getEmployees', () => this.employeeRender.getEmployees(event));
        this.map.set('getEmployeeSavePage', () => this.employeeRender.getEmployeeSavePage(event));
        this.map.set('showEmployeeSavePage', () => this.employeeRender.showEmployeeSavePage(event));
        this.map.set('saveEmployee', () => this.employeeService.saveEmployee());
        this.map.set('deleteEmployee', () => this.employeeService.deleteEmployee(event));
    }

    start(){
        this.departmentRender.getDepartments();
        $('body').on('click', '.event', (event) => {
            this.map.get(event.target.name)(event);
            event.preventDefault();
        });
    }
}
