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
import DepartmentsList from "./containers/departmentsList";
import DepartmentSaveForm from "./containers/departmentSaveForm";
import history from './history';

const store = createStore(rootReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(),
    applyMiddleware(thunk));

ReactDOM.render((
    <Provider store={store}>
        <Router history={history}>
            <div>
                <Route exact path="/" component={DepartmentsList}/>
                <Route path="/department/save/:id?" component={DepartmentSaveForm}/>
            </div>
        </Router>
    </Provider>
), document.getElementById('app'));



