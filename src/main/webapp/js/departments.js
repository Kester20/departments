import React, {Component} from "react";
import axios from "axios";

export default class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            departments: [],
            page: 1,
            itemsPerPage: 5,
            /*itemsPerPageOptions: [
                {value: '5', label: '5'},
                {value: '8', label: '8'},
                {value: '10', label: '10'}
            ]*/
        };
        this.logChange = this.logChange.bind(this);
    }

    componentDidMount() {
        axios
            .get('/department/getAll/' + this.state.page + '/' + this.state.itemsPerPage)
            .then(response => this.setState({departments: response.data}))
            .catch(err => console.log(err))
    }

    render() {
        return (
            <div id="departmentsContent">



                <table className="mdl-data-table table">

                    <tr>
                        <th className="mdl-data-table__cell--non-numeric">#</th>
                        <th className="mdl-data-table__cell--non-numeric">Name</th>
                        <th className="mdl-data-table__cell--non-numeric">Edit</th>
                        <th className="mdl-data-table__cell--non-numeric">Delete</th>
                        <th className="mdl-data-table__cell--non-numeric">Employees</th>
                    </tr>
                    {
                        this.state.departments.map((department, $index) =>
                            <tr key={department.departmentId}>
                                <td>{$index + 1}</td>
                                <td>{department.name}</td>
                                <td><a href="departmentSave({departmentId: department.departmentId})"
                                       className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                                    Edit</a>
                                </td>
                                <td>
                                    <button onClick="dc.deleteDepartment(department.departmentId)"
                                            className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                                        Delete
                                    </button>
                                </td>
                                <td>
                                    <a href="employees({departmentId: department.departmentId})"
                                       className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                        Employees
                                    </a>
                                </td>
                            </tr>
                        )
                    }

                </table>
            </div>
        )
    }
}

