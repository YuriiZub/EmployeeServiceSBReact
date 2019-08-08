/**
 * Created by Yurii on 7/7/2019.
 */

import React from 'react'
import {connect} from 'react-redux';

const SelectField = ({name, selected, onChange, onBlur, error, label, objects}) => (
    <div>

        <label>
            {label}
            <select className="form-control"
                    name={name}
                    onChange={onChange}
                    onClick={onChange}
                    onBlur={onBlur}
                    selected={selected}
            >

                {objects.map((object) =>
                    <option value={object.id}>{object.name}</option>
                )}

            </select>

            {error && <div>{error}</div>}
        </label>
    </div>
);

const connectedSelectField = connect()(SelectField);
export {connectedSelectField  as SelectField};