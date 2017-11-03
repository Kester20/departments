import React from "react";
import axios from "axios";

export const renderField = ({input, label, type, meta: {touched, error, warning}}) => (
    <div>
        <div>
            <input {...input} placeholder={label} type={type} className="mdl-textfield__input"/>
            <label className="error">
                {touched && ((error && <span>{error}</span>))}
            </label>
        </div>
    </div>
);

export const getDate = (date) => {
    let inputDate = new Date(date);
    return `${inputDate.getFullYear()}-${inputDate.getMonth()}-${inputDate.getDate()}`;
};

export const API = (url, method) => {
    return axios
        [method](url)
        .then(response => {return response.data})
        .catch(error => {throw error});
};
