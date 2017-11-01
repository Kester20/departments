import React, {Component} from "react";
import * as employeeActions from '../actions/employeeActions';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import {Field, reduxForm} from 'redux-form';
import {renderField} from '../util/util';
import * as constants from '../util/constants';
import PropTypes from 'prop-types';
import {getDate} from '../util/util';

const validate = values => {
    const errors = {};
    if (!values.name || values.name.length > 15) {
        errors.name = 'Required and must be 15 characters or less'
    }
    if(!values.age || values.age.length > 2) {
        errors.age = 'Required and must be less then 100 years old'
    }
    if(!values.dateOfBirth || !/^\d\d\d\d-\d\d?-\d\d$/.test(values.dateOfBirth)){
        errors.dateOfBirth = 'Required and must be match to YYYY-MM-DD pattern';
    }
    if(!values.email || !/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-z]{2,4}$/.test(values.email)){
        errors.email = 'Required and must be match to email pattern';
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
        this.handleChange = this.handleChange.bind(this);
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
        this.props.dispatch(employeeActions.cleanState());
    }

    handleChange(event) {
        let employee = this.props.employee;
        employee[event.target.name] = event.target.value;
        this.props.dispatch(employeeActions.getEmployeeSuccess(employee));
    }

    saveEmployee() {
        const {employee} = this.props;
        let params = '';
        if (employee.employeeId) {
            params = `employeeId=${employee.employeeId}&`;
        }
        params += `name=${employee.name}&age=${employee.age}&dateOfBirth=${getDate(employee.dateOfBirth)}
        &email=${employee.email}&departmentId=${this.props.match.params.departmentId}`;
        this.props.dispatch(employeeActions.saveEmployee(params, this.props.match.params.departmentId));
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
                                    onChange={this.handleChange}
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
                                    onChange={this.handleChange}
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
                                    onChange={this.handleChange}
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
                                    onChange={this.handleChange}
                                />
                                <label className="error">
                                    {this.props.errorMessage}
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td><Link
                                to={'/employee/getByDepartment/' + this.props.match.params.departmentId}
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