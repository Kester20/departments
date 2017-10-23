import React, {Component} from "react";
import {Link} from 'react-router-dom';

export default class Departments extends Component {

    constructor(props){
        super(props);
    }

    render(){
        const key = this.props.key;
        const value = this.props.value;

        return (
            <tr key={key}>
                <td>{key + 1}</td>
                <td>{value.get('name')}</td>
                <td><Link to={'/department/save/' + value.get('departmentId')}
                          className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Edit
                </Link>
                </td>
                <td>
                    <button onClick={ () => this.deleteDepartment(value.get('departmentId'))}
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