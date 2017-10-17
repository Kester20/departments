import React, {Component} from "react";
import axios from "axios";
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import Pagination from "react-js-pagination";

export default class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            departments: [],
            page: 1,
            itemsPerPage: 5,
            totalItemsCount: 0,
        };
        this.getDepartments = this.getDepartments.bind(this);
        this.getCountOfDepartments = this.getCountOfDepartments.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSelectChange = this.handleSelectChange.bind(this);
    }

    componentDidMount() {
        this.getDepartments(this.state.page, this.state.itemsPerPage);
        this.getCountOfDepartments();
    }

    getDepartments(pageNumber, itemsPerPage) {
        axios
            .get('/department/getAll/' + pageNumber + '/' + itemsPerPage)
            .then(response => this.setState({departments: response.data}))
            .catch(err => console.log(err));
    }

    getCountOfDepartments() {
        axios
            .get('/department/getTotal/')
            .then(response => this.setState({totalItemsCount: response.data}))
            .catch(err => console.log(err));
    }

    handlePageChange(pageNumber) {
        this.setState({page: pageNumber});
        this.getDepartments(pageNumber, this.state.itemsPerPage);
    }

    handleSelectChange(event, index, value) {
        this.setState({itemsPerPage: value});
        this.getDepartments(this.state.page, value);
    }

    render() {
        return (
            <div id="departmentsContent">

                <h3 align="center" className="label">Departments</h3>

                <MuiThemeProvider muiTheme={getMuiTheme()}>
                    <div id="item_per_page_div">
                        <SelectField
                            floatingLabelText="Item per page"
                            value={this.state.itemsPerPage}
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
                                    <button onClick={this.deleteDepartment}
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

                    <tr>
                        <td colSpan="5" id="centerTd">
                            <a href="departmentSave"
                               className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                                Add new department
                            </a>
                        </td>
                    </tr>

                    <tr>
                        <td colSpan="5" id="centerTd">
                            <Pagination
                                activePage={this.state.page}
                                itemsCountPerPage={this.state.itemsPerPage}
                                totalItemsCount={this.state.totalItemsCount}
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


