import React, {Component} from "react";
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import Pagination from "react-js-pagination";
import * as departmentActions from '../actions/departmentsActions';
import Department from '../components/department';

@connect(
    state => ({
        departments: state.departments.get('list'),
        pageNumber: state.departments.get('pageNumber'),
        itemsPerPage: state.departments.get('itemsPerPage'),
        countOfDepartments: state.departments.get('countOfDepartments')
    }),
    dispatch => ({
        dispatch
    }))
export default class Departments extends Component {

    constructor(props) {
        super(props);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSelectChange = this.handleSelectChange.bind(this);
        this.deleteDepartment = this.deleteDepartment.bind(this);
    }

    componentDidMount() {
        this.props.dispatch(departmentActions.getDepartments());
        this.props.dispatch(departmentActions.getCountOfDepartments());
    }

    handlePageChange(pageNumber) {
        this.props.dispatch(departmentActions.getDepartments(pageNumber, this.props.itemsPerPage));
    }

    handleSelectChange(event, index, value) {
        this.props.dispatch(departmentActions.getDepartments(this.props.pageNumber, value));
    }

    deleteDepartment(id){
        this.props.dispatch(departmentActions.deleteDepartment(id));
    }

    mapDepartments(departments) {
        return departments.map((value, key) =>
            <Department value={value} key={key}/>
        );
    }

    render() {
        const {departments, pageNumber, itemsPerPage, countOfDepartments} = this.props;
        if (!departments) {
            return null;
        }
        const departmentsView = this.mapDepartments(departments);
        return (
            <div id="departmentsContent">

                <h3 align="center" className="label">Departments</h3>

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
                        <th className="mdl-data-table__cell--non-numeric">Edit</th>
                        <th className="mdl-data-table__cell--non-numeric">Delete</th>
                        <th className="mdl-data-table__cell--non-numeric">Employees</th>
                    </tr>

                    {departmentsView}

                    <tr>
                        <td colSpan="5" id="centerTd">
                            <Link to={'/department/save/'}
                                  className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                                Add new department
                            </Link>
                        </td>
                    </tr>

                    <tr>
                        <td colSpan="5" id="centerTd">
                            <Pagination
                                activePage={pageNumber}
                                itemsCountPerPage={itemsPerPage}
                                totalItemsCount={countOfDepartments}
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


