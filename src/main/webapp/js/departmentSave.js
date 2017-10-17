import React, {Component} from "react";
import axios from "axios";

export default class DepartmentSave extends Component {

    constructor(props) {
        super(props);
        this.state = {
            department: {
                departmentId: null,
                name: null
            },
        };
        this.handleNameChange = this.handleNameChange.bind(this);
        this.saveDepartment = this.saveDepartment.bind(this);
    }

    componentDidMount() {
        let id = this.props.match.params.id;
        if(id){
            axios
                .get('/department/save?departmentId=' + id)
                .then(response => this.setState({department: response.data}))
                .catch(err => console.log(err));
            this.setState({department: {
                departmentId: id
            }});
        }
    }

    handleNameChange(event) {
        this.setState({department: {
            departmentId: this.state.department.departmentId,
            name: event.target.value
        }});
    }

    saveDepartment(){
        let name = this.state.department.name;
        let id = this.state.department.departmentId;
        let params;
        !this.state.department.departmentId ? (params = "name=" + name) : (params = "name=" + name + "&departmentId=" + id);
        axios
            .post('/department/save?' + params)
            .then( () => { this.props.history.push("/"); })
            .catch(err => console.log(err));
    }

    render() {
        return (
            <div id="saveContent">

                <h3 align="center" className="label">Enter new value</h3>

                <form name="form">
                    <table className="mdl-data-table table" cellPadding="10">
                        <tbody>
                        <tr>
                            <td colSpan="2">
                                <input className="mdl-textfield__input" type="text" name="name" placeholder="Name" value={this.state.department.name} onChange={this.handleNameChange}/>
                                <label className="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td><a href="/"
                                   className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Back</a>
                            </td>
                            <td>
                                <button type="button" onClick={this.saveDepartment}
                                        className="event mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                                    Save
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

            </div>
        )
    }
}