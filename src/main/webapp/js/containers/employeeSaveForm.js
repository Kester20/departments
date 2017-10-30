import React, {Component} from "react";
import * as employeeActions from '../actions/employeeActions';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import {Field, reduxForm} from 'redux-form';
import {renderField} from '../util/util';
import * as constants from '../util/constants';
import PropTypes from 'prop-types';

const validate = values => {
    const errors = {};
    if (!values.name) {
        errors.name = 'Required!'
    } else if (values.name.length > 15) {
        errors.name = 'Must be 15 characters or less'
    }
    return errors
};

@reduxForm({
    form: constants.EMPLOYEE_SAVE_FORM,
    validate
})
@connect(
    state => ({
        employee: state.employees.get('employee'),
        errorMessage: state.employees.get('errorMessage')
    }),
    dispatch => ({
        dispatch
    })
)
export default class EmployeeSaveForm extends Component {

    constructor(props) {
        super(props);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleAgeChange = this.handleAgeChange.bind(this);
        this.handleDateOfBirthChange = this.handleDateOfBirthChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.saveEmployee = this.saveEmployee.bind(this);
    }

    static propTypes = {
        employee: PropTypes.object,
        errorMessage: PropTypes.string
    };

    componentDidMount() {
        let id = this.props.match.params.id;
        if (id) {
            this.props.dispatch(employeeActions.getEmployee(id));
        }
    }

    componentWillUnmount() {
        this.props.dispatch(employeeActions.getEmployeeSuccess({employeeId: null, name: null}));
        this.props.dispatch(employeeActions.handleError(null));
    }

    handleNameChange(event) {
        let employee = this.props;
        this.props.dispatch(employeeActions.getEmployeeSuccess({
            employeeId: employee.employeeId,
            name: event.target.value,
            age: employee.age,
            dateOfBirth: employee.dateOfBirth,
            email: employee.email,
            department: employee.department
        }));
    }

    handleAgeChange(event) {
        let employee = this.props;
        this.props.dispatch(employeeActions.getEmployeeSuccess({
            employeeId: employee.employeeId,
            name: employee.name,
            age: event.target.value,
            dateOfBirth: employee.dateOfBirth,
            email: employee.email,
            department: employee.department
        }));
    }

    handleDateOfBirthChange(event) {
        let employee = this.props;
        this.props.dispatch(employeeActions.getEmployeeSuccess({
            employeeId: employee.employeeId,
            name: employee.name,
            age: employee.age,
            dateOfBirth: event.target.value,
            email: employee.email,
            department: employee.department
        }));
    }

    handleEmailChange(event) {
        let employee = this.props;
        this.props.dispatch(employeeActions.getEmployeeSuccess({
            employeeId: employee.employeeId,
            name: employee.name,
            age: employee.age,
            dateOfBirth: employee.dateOfBirth,
            email: event.target.value,
            department: employee.department
        }));
    }

    saveEmployee() {
        const {employee} = this.props;
        let params = '';
        if (employee.employeeId) {
            params = `employeeId=${employee.employeeId}&`;
        }
        params += `name=${employee.name}&age=${employee.age}&dateOfBirth=${employee.dateOfBirth}
        &email=${employee.email}&departmentId=${employee.departmentId}`;
        this.props.dispatch(employeeActions.saveEmployee(params, employee.departmentId));
    }

    getDepartmentId() {
        return this.props.employee.department ? this.props.employee.department.departmentId : 0;
    }

    render() {
        return (
            <div id="saveContent">

                <h3 align="center" className="label">Enter new value</h3>

                <form name="form">
                    <table className="mdl-data-table table" cellPadding="10">
                        <tbody>
                        <tr>
                            <td colSpan="2">
                                <Field
                                    name="name"
                                    type="text"
                                    component={renderField}
                                    label="Name"
                                    onChange={this.handleNameChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan="2">
                                <Field
                                    name="age"
                                    type="number"
                                    component={renderField}
                                    label="Age"
                                    onChange={this.handleAgeChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan="2">
                                <Field
                                    name="dateOfBirth"
                                    type="text"
                                    component={renderField}
                                    label="Date of birth"
                                    onChange={this.handleDateOfBirthChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan="2">
                                <Field
                                    name="email"
                                    type="text"
                                    component={renderField}
                                    label="Email"
                                    onChange={this.handleEmailChange}
                                />
                            </td>
                        </tr>
                        <tr>
                            <td><Link
                                to={'/employee/getByDepartment/' + this.getDepartmentId()}
                                className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                Back
                            </Link>
                            </td>
                            <td>
                                <button
                                    type="button"
                                    disabled={!this.props.valid}
                                    onClick={this.saveEmployee}
                                    className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                                    Save
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

            </div>
        )
    }
}