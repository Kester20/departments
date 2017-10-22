import '../css/style.css';
import '../node_modules/material-design-lite/material.min.css';
import '../node_modules/material-design-lite/material.min';
import '../node_modules/lodash/lodash.min'
import React from "react";
import ReactDOM from "react-dom";
import {BrowserRouter, Route} from "react-router-dom";
import {Provider} from "react-redux";
import {createStore, applyMiddleware} from "redux";
import thunk from 'redux-thunk';
import rootReducer from './reducers/reducers';
import * as departmentActions from './actions/actions';
import Departments from "./containers/departments";
import DepartmentSave from "./departmentSave";

const store = createStore(rootReducer, applyMiddleware(thunk));
store.dispatch(departmentActions.getDepartments());

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



