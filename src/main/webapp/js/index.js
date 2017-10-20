import '../css/style.css';
import '../node_modules/material-design-lite/material.min.css';
import '../node_modules/material-design-lite/material.min';
import '../node_modules/lodash/lodash.min'
import React from "react";
import ReactDOM from "react-dom";
import {BrowserRouter, Route} from "react-router-dom";
import {Provider} from "react-redux";
import {createStore} from "redux";
import Departments from "./departments";
import DepartmentSave from "./departmentSave";

const store = createStore({});

ReactDOM.render((
    <Provider store={store}>
        <BrowserRouter>
            <div>
                <Route exact path="/" component={Departments}/>
                <Route path="/department/save/:id?" component={DepartmentSave}/>
            </div>
        </BrowserRouter>
    </Provider>
), document.getElementById('app'));



