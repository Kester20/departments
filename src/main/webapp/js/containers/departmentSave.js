import React, {Component} from "react";
import * as departmentActions from '../actions/departmentsActions';
import {connect} from 'react-redux';

@connect(
    state => ({
        department: state.departments.get('department')
    }),
    dispatch => ({
        dispatch
    })
)
export default class DepartmentSave extends Component {

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

    handleNameChange(event) {
        const id = this.props.department.departmentId;
        this.props.dispatch(departmentActions.getDepartmentSuccess({department: {
            departmentId: id,
            name: event.target.value
        }}));
    }

    saveDepartment(){
        const {department} = this.props.department;
        const params= !department.departmentId ?  `name=${department.name}` :
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
                                <input className="mdl-textfield__input" type="text" name="name" placeholder="Name" value={this.props.department.name} onChange={this.handleNameChange}/>
                                <label className="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td><a href="/"
                                   className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Back</a>
                            </td>
                            <td>
                                <button type="button" onClick={this.saveDepartment}
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