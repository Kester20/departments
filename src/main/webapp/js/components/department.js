import React, {Component} from "react";
import {Link} from 'react-router-dom';
import * as departmentActions from '../actions/departmentsActions';

export default class Department extends Component {

    constructor(props){
        super(props);
    }

    render(){
        const key = this.props.index;
        const value = this.props.value;
        const dispatch = this.props.dispatch;

        return (
            <tr key={value.get('departmentId')}>
                <td>{key + 1}</td>
                <td>{value.get('name')}</td>
                <td>
                    <Link to={'/department/save/' + value.get('departmentId')}
                          className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Edit
                    </Link>
                </td>
                <td>
                    <button onClick={ () => dispatch(departmentActions.deleteDepartment(value.get('departmentId')))}
                            className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Delete
                    </button>
                </td>
                <td>
                    <a href="employees({departmentId: department.departmentId})"
                       className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Employees
                    </a>
                </td>
            </tr>
        );
    }
};