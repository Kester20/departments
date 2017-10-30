import React, {Component} from "react";
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import * as employeeActions from '../actions/employeeActions';
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import Employee from '../components/employee';
import Pagination from "react-js-pagination";

@connect(
    state => ({
        employees: state.employees.get('list'),
        pageNumber: state.employees.get('pageNumber'),
        itemsPerPage: state.employees.get('itemsPerPage'),
        countOfEmployees: state.employees.get('countOfEmployees')
    }),
    dispatch => ({
        dispatch
    }))
export default class EmployeeList extends Component {

    constructor(props) {
        super(props);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSelectChange = this.handleSelectChange.bind(this);
    }

    componentDidMount() {
        this.props.dispatch(employeeActions.getEmployees(this.props.match.params.departmentId, this.props.pageNumber, this.props.itemsPerPage));
        this.props.dispatch(employeeActions.getCountOfEmployees(this.props.match.params.departmentId));
    }

    handlePageChange(pageNumber) {
        this.props.dispatch(employeeActions.getEmployees(this.props.match.params.departmentId, pageNumber, this.props.itemsPerPage));
    }

    handleSelectChange(event, index, value) {
        this.props.dispatch(employeeActions.getEmployees(this.props.match.params.departmentId, this.props.pageNumber, value));
    }

    mapEmployees(employees) {
        return employees.map((value, key) =>
            <Employee value={value} key={key} index={key} deleteEmployee={this.deleteEmployee}/>
        );
    }

    render() {
        const {employees, pageNumber, itemsPerPage, countOfEmployees} = this.props;
        if (!employees) {
            return null;
        }
        const employeesView = this.mapEmployees(employees);
        return (
            <div id="employeesContent">

                <h3 align="center" className="label">Employees</h3>

                <MuiThemeProvider muiTheme={getMuiTheme()}>
                    <div id="item_per_page_div">
                        <SelectField
                            floatingLabelText="Item per page"
                            value={itemsPerPage}
                            onChange={this.handleSelectChange}>
                            <MenuItem value={5} primaryText="5"/>
                            <MenuItem value={8} primaryText="8"/>
                            <MenuItem value={10} primaryText="10"/>
                        </SelectField>
                    </div>
                </MuiThemeProvider>

                <table className="mdl-data-table table">
                    <tbody>

                    <tr>
                        <th className="mdl-data-table__cell--non-numeric">#</th>
                        <th className="mdl-data-table__cell--non-numeric">Name</th>
                        <th className="mdl-data-table__cell--non-numeric">Age</th>
                        <th className="mdl-data-table__cell--non-numeric">Birthday</th>
                        <th className="mdl-data-table__cell--non-numeric">Email</th>
                        <th className="mdl-data-table__cell--non-numeric">Edit</th>
                        <th className="mdl-data-table__cell--non-numeric">Delete</th>
                    </tr>

                    {employeesView}

                    <tr>
                        <td colSpan="2">
                            <Link
                                to={'/'}
                                className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                Back
                            </Link>
                        </td>
                        <td colSpan="3"/>
                        <td colSpan="2">
                            <Link to="employeeSave"
                                  className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                                Add new employee
                            </Link>
                        </td>
                    </tr>

                    <tr>
                        <td colSpan="7" id="centerTd">
                            <Pagination
                                activePage={pageNumber}
                                itemsCountPerPage={itemsPerPage}
                                totalItemsCount={countOfEmployees}
                                onChange={this.handlePageChange}
                            />
                        </td>
                    </tr>

                    </tbody>
                </table>

            </div>
        )
    }
}