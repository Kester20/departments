import React, {Component} from "react";
import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';

export default class Employee extends Component {

    constructor(props){
        super(props);
    }

    static propTypes = {
        index: PropTypes.number.isRequired,
        value: PropTypes.object.isRequired,
        deleteEmployee: PropTypes.func.isRequired
    };

    render(){
        const key = this.props.index;
        const value = this.props.value;
        const deleteDepartment = this.props.deleteDepartment;
        const id = value.get('employeeId');
        const name = value.get('name');
        const age = value.get('age');
        const dateOfBirth = value.get('dateOfBirth');
        const email = value.get('email');
        return (
            <tr key={id}>
                <td>{key + 1}</td>
                <td>{name}</td>
                <td>{age}</td>
                <td>{dateOfBirth}</td>
                <td>{email}</td>
                <td><a
                    className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Edit</a>
                </td>
                <td>
                    <button
                        className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                        Delete
                    </button>
                </td>
            </tr>
        );
    }
};