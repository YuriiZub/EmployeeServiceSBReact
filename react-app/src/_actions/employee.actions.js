import { employeeConstants } from '../_constants';
import { employeeService } from '../_services';
import { alertActions } from './';

export const employeeActions = {
    addEmployee,
    editEmployee,
    removeEmployee
};

function addEmployee(employee) {
    return dispatch => {
        dispatch(request({ employee }));

        employeeService.addEmployee(employee)
            .then(
                employee => {
                    dispatch(success(employee));
                    console.log("empl add=",employee);
                    if(employee.status ===400){
                        alert(employee.status + 'employee already exist!');

                    }
                    if(employee.status ===200){
                        alert('employee added successfully!');
                    }
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(employee) { return { type: employeeConstants.EMPL_ADD_REQUEST, employee } }
    function success(employee) { return { type: employeeConstants.EMPL_ADD_SUCCESS, employee } }
    function failure(error) { return { type: employeeConstants.EMPL_ADD_FAILURE, error } }
}

function editEmployee(employee) {
    return dispatch => {
        dispatch(request({ employee }));

        employeeService.editEmployee(employee)
            .then(
                employee => {
                    dispatch(success(employee));
                    console.log("empl add=",employee);
                    if(employee.status ===400){
                        alert(employee.status + 'employee with this name already exist\'s!');

                    }
                    if(employee.status ===200){
                        alert('employee edited successfully!');
                    }
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(employee) { return { type: employeeConstants.EMPL_EDT_REQUEST, employee } }
    function success(employee) { return { type: employeeConstants.EMPL_EDT_SUCCESS, employee } }
    function failure(error) { return { type: employeeConstants.EMPL_EDT_FAILURE, error } }
}


function removeEmployee(id) {
    return dispatch => {
        dispatch(request({ id }));

        employeeService.removeEmployee(id)
            .then(
                employee => {
                   // console.log("empl add=",employee);
                    dispatch(success(id));
                    if(employee.status ===200){
                        alert('employee deleted successfully!');
                    }
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(id) { return { type: employeeConstants.EMPL_DEL_REQUEST, id } }
    function success(id) { return { type: employeeConstants.EMPL_DEL_SUCCESS, id } }
    function failure(error) { return { type: employeeConstants.EMPL_DEL_FAILURE, error } }
}
