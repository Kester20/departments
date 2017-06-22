import '../css/style.css';
import '../node_modules/material-design-lite/material.min.css';
import '../node_modules/material-design-lite/material.min';
import 'jquery-validation';
import Dispatcher from './dispatcher';

$( document ).ready(function() {
    let dispatcher = new Dispatcher();
    dispatcher.start();
});


