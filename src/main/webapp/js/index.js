import '../css/style.css';
import '../node_modules/material-design-lite/material.min.css';
import '../node_modules/material-design-lite/material.min';
import '../node_modules/lodash/lodash.min'
import React from "react";
import ReactDOM from "react-dom";
import {Router, Route} from "react-router-dom";
import {Provider} from "react-redux";
import {createStore, applyMiddleware} from "redux";
import thunk from 'redux-thunk';
import rootReducer from './reducers/reducers';
import Departments from "./containers/departments";
import DepartmentSave from "./containers/departmentSave";
import history from './history';

const store = createStore(rootReducer, applyMiddleware(thunk));

ReactDOM.render((
    <Provider store={store}>
        <Router history={history}>
            <div>
                <Route exact path="/" component={Departments}/>
                <Route path="/department/save/:id?" component={DepartmentSave}/>
            </div>
        </Router>
    </Provider>
), document.getElementById('app'));



