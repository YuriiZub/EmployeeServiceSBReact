/**
 * Created by Yurii on 7/7/2019.
 */
import React from 'react'
import { connect } from 'react-redux';

const CheckBoxField = ({name, value, onChange, onBlur, error, label, cheked}) => (
    <div >
        <label>
            {label}
            <input className="form-control"
                value={value}
                type="checkbox"
                name={name}
                onClick={onChange}
                onBlur={onBlur}
                checked={cheked}
            />
            {error && <div >{error}</div>}
        </label>
    </div>
);

const connectedCheckBoxField = connect()(CheckBoxField);
export { connectedCheckBoxField as CheckBoxField };