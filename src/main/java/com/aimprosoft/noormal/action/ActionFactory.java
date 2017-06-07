package com.aimprosoft.noormal.action;


import org.springframework.stereotype.Component;

/**
 * @author Arsalan
 */
@Component
public class ActionFactory {

    /*private Action defaultAction;
    private Action saveDepartmentAction;
    private Action deleteDepartmentAction;
    private Action getDepartmentSavePageAction;
    private Action getEmployeeSavePageAction;
    private Action getAllEmployeesPageAction;
    private Action saveEmployeeAction;
    private Action deleteEmployeeAction;

    @Autowired
    public ActionFactory(@Qualifier("defaultAction") Action defaultAction,
                         @Qualifier("saveDepartmentAction") Action saveDepartmentAction,
                         @Qualifier("deleteDepartmentAction") Action deleteDepartmentAction,
                         @Qualifier("getDepartmentSavePageAction") Action getDepartmentSavePageAction,
                         @Qualifier("getEmployeeSavePageAction") Action getEmployeeSavePageAction,
                         @Qualifier("getAllEmployeesPageAction") Action getAllEmployeesPageAction,
                         @Qualifier("saveEmployeeAction") Action saveEmployeeAction,
                         @Qualifier("deleteEmployeeAction") Action deleteEmployeeAction) {

        this.defaultAction = defaultAction;
        this.saveDepartmentAction = saveDepartmentAction;
        this.deleteDepartmentAction = deleteDepartmentAction;
        this.getDepartmentSavePageAction = getDepartmentSavePageAction;
        this.getEmployeeSavePageAction = getEmployeeSavePageAction;
        this.getAllEmployeesPageAction = getAllEmployeesPageAction;
        this.saveEmployeeAction = saveEmployeeAction;
        this.deleteEmployeeAction = deleteEmployeeAction;
    }

    private Map<String, Action> actions;

    @PostConstruct
    private void init() {
        actions = new HashMap<>();
        actions.put(ROOT_PATH, defaultAction);
        actions.put(DEPARTMENT_ACTION_PATH, saveDepartmentAction);
        actions.put(DELETE_DEPARTMENT_PATH, deleteDepartmentAction);

        actions.put(SAVE_DEPARTMENT_PATH, getDepartmentSavePageAction);
        actions.put(SAVE_EMPLOYEE_PATH, getEmployeeSavePageAction);

        actions.put(GET_ALL_EMPLOYEE_PATH, getAllEmployeesPageAction);
        actions.put(EMPLOYEE_ACTION_PATH, saveEmployeeAction);
        actions.put(DELETE_EMPLOYEE_PATH, deleteEmployeeAction);
    }

    public Action getDefaultAction() {
        return defaultAction;
    }

    public Action getAction(String action) {
        return actions.get(action);
    }*/
}
