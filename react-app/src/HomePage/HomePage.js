import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import {EmployeeForm} from "../EmployeePage";


class HomePage extends React.Component {

    render() {
        return (
            <div className="col-md-6 col-md-offset-3">

                <h2>Hi, {localStorage.getItem("user")}!&nbsp;&nbsp;<Link to="/login">Logout</Link></h2>

                <div>
                    <EmployeeForm/>

                </div>
            </div>
        );
    }
}


const connectedHomePage = connect()(HomePage);
export {connectedHomePage as HomePage};