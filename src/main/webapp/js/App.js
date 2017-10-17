import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Link} from 'react-router-dom';
import Home from './departments';
import DepartmentSave from './departmentSave';
import Contact from './contact';


ReactDOM.render((
    <BrowserRouter>
        <div>
            <Route exact path="/" component={Home}/>
            <Route path="/department/save" component={DepartmentSave}/>
            <Route path="/contact" component={Contact}/>
        </div>
    </BrowserRouter>

), document.getElementById('app'));
