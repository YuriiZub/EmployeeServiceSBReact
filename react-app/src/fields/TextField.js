/**
 * Created by Yurii on 7/7/2019.
 */

import React from 'react'
import { connect } from 'react-redux';

const TextField = ({name, value, onChange, onBlur, onKeyDown, error, label, ref}) => (
    <div className="form-group">
        <label>
            {label}
            <input className="form-control"
                value={value}
                type="text"
                name={name}
                onChange={onChange}
                onBlur={onBlur}
                onKeyDown={onKeyDown}
                /*ref = {ref}*/
            />
            {error && <div className="help-block" >{error}</div>}
        </label>
    </div>
);

const connectedTextField  = connect()(TextField );
export { connectedTextField  as TextField  };
