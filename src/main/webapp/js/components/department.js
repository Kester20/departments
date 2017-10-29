import React, {Component} from "react";
import {Link} from 'react-router-dom';

export default class Department extends Component {

    constructor(props){
        super(props);
    }

    render(){
        const key = this.props.index;
        const value = this.props.value;
        const deleteDepartment = this.props.deleteDepartment;
        const id = value.get('departmentId');
        const name = value.get('name');
        return (
            <tr key={id}>
                <td>{key + 1}</td>
                <td>{name}</td>
                <td>
                    <Link to={'/department/save/' + id}
                          className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Edit
                    </Link>
                </td>
                <td>
                    <button onClick={deleteDepartment.bind(this, id)}
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