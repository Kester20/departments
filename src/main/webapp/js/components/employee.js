import React, {Component} from "react";
import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';
import {getDate} from '../util/util';

export default class Employee extends Component {

    constructor(props) {
        super(props);
    }

    static propTypes = {
        index: PropTypes.number.isRequired,
        value: PropTypes.object.isRequired,
        deleteEmployee: PropTypes.func.isRequired
    };

    render() {
        const {index, value, deleteEmployee} = this.props;
        if(!value) {
            return null;
        }
        return (
            <tr key={value.get('employeeId')}>
                <td>{index}</td>
                <td>{value.get('name')}</td>
                <td>{value.get('age')}</td>
                <td>{getDate(value.get('dateOfBirth'))}</td>
                <td>{value.get('email')}</td>
                <td><Link to={'/employee/save/' + value.get('department').get('departmentId') + '/' + value.get('employeeId')}
                    className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                    Edit
                </Link>
                </td>
                <td>
                    <button onClick={deleteEmployee.bind(this, value.get('employeeId'))}
                        className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                        Delete
                    </button>
                </td>
            </tr>
        );
    }
};