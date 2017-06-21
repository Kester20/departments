import '../css/style.css';
import 'jquery-validation';
import Dispatcher from './dispatcher';

$( document ).ready(function() {
    let dispatcher = new Dispatcher();
    dispatcher.start();
});


