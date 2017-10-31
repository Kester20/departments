import React from "react";

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