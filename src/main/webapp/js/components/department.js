import React, {Component} from "react";
import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';

export default class Department extends Component {

    constructor(props){
        super(props);
    }

    static propTypes = {
        index: PropTypes.number.isRequired,
        value: PropTypes.object.isRequired,
        deleteDepartment: PropTypes.func.isRequired
    };

    render(){
        const {index, value, deleteDepartment} = this.props;
        if(!value) {
            return null;
        }
        return (
            <tr key={value.get('departmentId')}>
                <td>{index}</td>
                <td>{value.get('name')}</td>
                <td>
                    <Link to={'/department/save/' + value.get('departmentId')}
                          className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Edit
                    </Link>
                </td>
                <td>
                    <button onClick={deleteDepartment.bind(this, value.get('departmentId'))}
                            className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Delete
                    </button>
                </td>
                <td>
                    <Link to={'employee/getByDepartment/' + value.get('departmentId')}
                       className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Employees
                    </Link>
                </td>
            </tr>
        );
    }
};