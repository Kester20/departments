import React, {Component} from "react";
import * as departmentActions from '../actions/departmentActions';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';

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

    componentDidMount() {
        let id = this.props.match.params.id;
        if(id){
            this.props.dispatch(departmentActions.getDepartment(id));
        }
    }

    componentWillUnmount(){
        this.props.dispatch(departmentActions.getDepartmentSuccess({departmentId: null, name: null}));
        this.props.dispatch(departmentActions.handleError(null));
    }

    handleNameChange(event) {
        const id = this.props.department.departmentId;
        this.props.dispatch(departmentActions.getDepartmentSuccess({departmentId: id, name: event.target.value}));
    }

    saveDepartment(){
        const {department} = this.props;
        const params= !department.departmentId ?  `name=${department.name}` :
            `name=${department.name}&departmentId=${department.departmentId}`;
        this.props.dispatch(departmentActions.saveDepartment(params));
    }

    validate(value){
        if(!value){
            this.props.dispatch(departmentActions.handleError('Required!'));
        }
        if(value.length > 30){
            this.props.dispatch(departmentActions.handleError('Must be less then 30 characters'));
        }
    }

    render() {
        const name = this.props.department.name ? this.props.department.name : '';
        return (
            <div id="saveContent">

                <h3 align="center" className="label">Enter new value</h3>

                <form name="form">
                    <table className="mdl-data-table table" cellPadding="10">
                        <tbody>
                        <tr>
                            <td colSpan="2">
                                <input
                                    className="mdl-textfield__input"
                                    type="text"
                                    name="name"
                                    placeholder="Name"
                                    value={name}
                                    onChange={this.handleNameChange}
                                    onBlur={this.validate.bind(this, name)}
                                />
                                <label className="error">{this.props.errorMessage}</label>
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