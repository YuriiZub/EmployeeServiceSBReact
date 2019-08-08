import { userConstants } from '../_constants';
import { userService } from '../_services';
import { alertActions } from './';
import { history } from '../_helpers';

export const userActions = {
    login,
	registration,
    logout
};

function login(loginname, userpassword) {
    return dispatch => {
        dispatch(request({ loginname }));
		
        let userLogin = loginname;
        let password = userpassword;

        let loginuser = {
            userLogin, password
        }

        userService.doLoginUser(loginuser)
            .then(
                user => { 
                    dispatch(success(user));
                    history.push('/');
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(user) { return { type: userConstants.LOGIN_REQUEST, user } }
    function success(user) { return { type: userConstants.LOGIN_SUCCESS, user } }
    function failure(error) { return { type: userConstants.LOGIN_FAILURE, error } }
}

function registration(username, loginname, userpassword) {
    return dispatch => {
        dispatch(request({ login }));
		
		let name = username;
        let loginId = loginname;
        let password = userpassword;

        let newuser = {
            name, loginId, password
        }

		userService.addNewUser(newuser)
            .then(
                user => { 
                    dispatch(success(user));
                    history.push('/login');
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(user) { return { type: userConstants.LOGIN_REQUEST, user } }
    function success(user) { return { type: userConstants.LOGIN_SUCCESS, user } }
    function failure(error) { return { type: userConstants.LOGIN_FAILURE, error } }
}

function logout() {
    userService.logout();
    return { type: userConstants.LOGOUT };
}

