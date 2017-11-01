import React, {Component} from "react";
import * as departmentActions from '../actions/departmentActions';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import {Field, reduxForm} from 'redux-form';
import {renderField} from '../util/util';
import * as constants from '../util/constants';
import PropTypes from 'prop-types';

const validate = values => {
    const errors = {};
    if (!values.name || values.name.length > 15) {
        errors.name = 'Required and must be less then 15 characters'
    }
    return errors
};

@reduxForm({
    form: constants.DEPARTMENT_SAVE_FORM,
    validate
})
@connect(
    state => ({
        department: state.departments.get('department'),
        errorMessage: state.departments.get('errorMessage')
    }),
    dispatch => ({
        dispatch
    })
)
export default class DepartmentSaveForm extends Component {

    constructor(props) {
        super(props);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.saveDepartment = this.saveDepartment.bind(this);
    }

    static propTypes = {
        department: PropTypes.object,
        errorMessage: PropTypes.string
    };

    componentDidMount() {
        let id = this.props.match.params.id;
        if (id) {
            this.props.dispatch(departmentActions.getDepartment(id));
        }
    }

    componentWillUnmount() {
        this.props.dispatch(departmentActions.getDepartmentSuccess({departmentId: null, name: null}));
        this.props.dispatch(departmentActions.handleError(null));
    }

    handleNameChange(event) {
        const id = this.props.department.departmentId;
        this.props.dispatch(departmentActions.getDepartmentSuccess({departmentId: id, name: event.target.value}));
    }

    saveDepartment() {
        const {department} = this.props;
        const params = !department.departmentId ? `name=${department.name}` :
            `name=${department.name}&departmentId=${department.departmentId}`;
        this.props.dispatch(departmentActions.saveDepartment(params));
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
                                <label className="error">
                                    {this.props.errorMessage}
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td><Link
                                to={'/'}
                                className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                Back</Link>
                            </td>
                            <td>
                                <button
                                    type="button"
                                    disabled={!this.props.valid}
                                    onClick={this.saveDepartment}
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