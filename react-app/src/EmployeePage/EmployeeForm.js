import React from 'react';
import {TextField} from '../fields';
import {CheckBoxField} from '../fields';
import {SelectField} from '../fields';
import {EmployeeTable} from "../EmployeePage";
import {connect} from 'react-redux';
import {employeeActions} from '../_actions';
import {departmentService} from '../_services';

class EmployeeForm extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            employeeNameError: "",
            activeError: "",
            departmentError: "",
            succesMessage: "",

            employeeId: null,
            employeeName: "",
            active: false,
            departmentId: 1,
            action: 0,

            departments: []

        }
    }

    componentWillMount() {
        this.getDepartments();
    }

    getDepartments() {
        departmentService.getDepartments()
            .then(data => {
                this.setState({departments: data});
            })
    }

    validateName = name => {
        const regex = /[A-Za-zА-я]{3,}/;

        return !regex.test(name)
            ? "Name must contain at least 3 letters. Numbers and special characters are not allowed."
            : "";
    };

    onEmployeeNameBlur = () => {
        const {employeeName} = this.state;
        const employeeNameError = this.validateName(employeeName);

        return this.setState({employeeNameError});
    };

    onEmployeeNameChange = event => {
        this.setState({
            employeeName: event.target.value,
            succesMessage: ""
        });
    }

    onActiveChange = event => {
        this.setState({
            active: event.target.checked,
            succesMessage: ""
        });
    }

    onDepartmentChange = event => {
        this.setState({
            departmentId: event.target.value,
            succesMessage: ""
        });
    }

    submitForm = (e) => {
        e.preventDefault();
        if (this.allowSubmit() === true) {
            this.sendData();
            this.refs.employeeForm.reset();
            this.refreshState();
        } else {
            this.setState({
                succesMessage: "Problems with input data!"

            });
            alert('Problems with input data!');
            this.onEmployeeNameBlur();
        }
    }

    allowSubmit() {

        if ((this.state.employeeNameError === "") && (this.state.activeError === "")
            && (this.state.departmentError === "") && (this.state.employeeName.length > 0))
            return true;
        else
            return false;
    }

    sendData() {

        let id = this.state.employeeId;
        let name = this.state.employeeName;
        let active = this.state.active;
        let departmentId = this.state.departmentId;

        let data = {
            id, name, active, departmentId
        }
        if (this.state.action === 0) {
            let id = null;

            let data = {
                id, name, active, departmentId
            }
            // this.addEmployee(data)
            const {dispatch} = this.props;
            dispatch(employeeActions.addEmployee(data));

            this.setState({
                succesMessage: name + " is added successfull!"
            });

        } else {

            const {dispatch} = this.props;
            dispatch(employeeActions.editEmployee(data));

            this.setState({
                succesMessage: id + "is edited successfull!"
            });
        }

        this.getPageData();
        // EmployeeTable.getPageData();
    }

    editForm = (employee) => {

        this.setState({
            action: 1,
            employeeId: employee.id,
            employeeName: employee.name,
            active: employee.active,
            departmentId: employee.department.id
        });
    }

    refreshState() {
        this.setState({
            employeeName: "",
            active: false,
            departmentId: 0,
            action: 0,
            succesMessage: ""
        });
        this.refs.employeeForm.reset();

    }

    getPageData = () => {
        // EmployeeTable.getPageData();
    }

    render() {

        const {
            employeeNameError, activeError, departmentError, employeeName,
            departmentId, departments, succesMessage
        } = this.state;

        return (
            <div className="col-md-6 col-md-offset-3">
                <h2>Employee</h2>

                <form ref="employeeForm" className="myForm">
                    <TextField onChange={this.onEmployeeNameChange}
                               onBlur={this.onEmployeeNameBlur}
                               error={employeeNameError}
                               defaultValue="will focus"
                               value={employeeName}
                               label="Name:"/>

                    <CheckBoxField onChange={this.onActiveChange}
                                   onBlur={this.onActiveBlur}
                                   error={activeError}
                                   checked={this.state.active}
                                   label="Active:"/>
                    <SelectField onChange={this.onDepartmentChange}
                                 onBlur={this.onDepartmentBlur}
                                 error={departmentError}
                                 selected={departmentId}
                                 objects={departments}
                                 value={departmentId}
                                 label="Department:"/>
                </form>

                <button className="btn btn-primary" onClick={(e) => this.submitForm(e)}>Submit</button>
                &nbsp;&nbsp;&nbsp;
                <button className="btn btn-default" onClick={() => this.refreshState()}>Clean</button>
                <p>{succesMessage}</p>
                <div>
                    <EmployeeTable
                        editForm={this.editForm}
                        getPageData={this.getPageData()}
                    />
                </div>

            </div>
        );
    }
}

const connectedEmployeeForm = connect()(EmployeeForm);
export {connectedEmployeeForm as EmployeeForm};