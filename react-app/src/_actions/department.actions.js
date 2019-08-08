import { departmentConstants } from '../_constants';
import { departmentService } from '../_services';
import { alertActions } from './';
import {history} from "../_helpers";
//import { history } from '../_helpers';

export const departmentActions = {
    getDepartments,

};

function getDepartments() {
    return dispatch => {
        dispatch(request());


        departmentService.getDepartments()
            .then(
                departments => {
                    dispatch(success(departments));
                    localStorage.setItem('departments', departments);
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                    history.push('/login');
                }
            );
    };

    function request(departments) { return { type: departmentConstants.DEPTH_REQUEST, departments } }
    function success(departments) { return { type: departmentConstants.DEPTH_SUCCESS, departments } }
    function failure(error) { return { type: departmentConstants.DEPTH_FAILURE, error } }

}


