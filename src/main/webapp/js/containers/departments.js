import React, {Component} from "react";
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import Pagination from "react-js-pagination";

export class Departments extends Component {

    constructor(props) {
        super(props);
        /*this.state = {
            departments: [],
            page: 1,
            itemsPerPage: 5,
            totalItemsCount: 0,
        };
        this.mapDepartments = this.mapDepartments.bind(this);
        this.getDepartments = this.getDepartments.bind(this);
        this.getCountOfDepartments = this.getCountOfDepartments.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSelectChange = this.handleSelectChange.bind(this);
        this.deleteDepartment = this.deleteDepartment.bind(this);*/
    }

    /*componentDidMount() {
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

    deleteDepartment(id){
        axios
            .post('/department/delete?departmentId=' + id)
            .then( () => {
                this.getDepartments(1, this.state.itemsPerPage);
                this.getCountOfDepartments();
                this.setState({page: 1});
            })
            .catch(err => console.log(err));
    }*/

    mapDepartments() {
        return this.props.departments.map((department, $index) =>
            <tr key={department.departmentId}>

                <td>{$index + 1}</td>
                <td>{department.name}</td>
                <td><Link to={'/department/save/' + department.departmentId}
                          className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Edit
                    </Link>
                </td>
                <td>
                    <button //onClick={() => this.deleteDepartment(department.departmentId)}
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

    render() {

        return (
            <div id="departmentsContent">

                <h3 align="center" className="label">Departments</h3>

                <MuiThemeProvider muiTheme={getMuiTheme()}>
                    <div id="item_per_page_div">
                        <SelectField
                            floatingLabelText="Item per page"
                            value={this.props.itemsPerPage}
                            //onChange={this.handleSelectChange}
                            >
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

                    {this.mapDepartments()}

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
                                activePage={this.props.pageNumber}
                                itemsCountPerPage={this.props.itemsPerPage}
                                totalItemsCount={this.props.totalItemsCount}
                                //onChange={this.handlePageChange}
                            />
                        </td>
                    </tr>

                    </tbody>

                </table>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        departments: state.departments.list ? state.departments.list : [],
        pageNumber: state.departments.pageNumber,
        itemsPerPage: state.departments.itemsPerPage
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        //createBook: book => dispatch(bookActions.createBook(book))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Departments);


