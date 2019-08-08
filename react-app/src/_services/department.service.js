import { userConstants } from '../_constants';

export const departmentService = {
    getDepartments
};

function getDepartments() {
    let token =  userConstants.SECRET_WORLD + localStorage.getItem("token");
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": token
        }
    };

    return fetch( (userConstants.SERVER_ADDRESS + '/department/'), requestOptions)

        .then(res => res.json())
        .then((data) => {

            return data;
        })
        .catch((error) => {
            console.log("myerror=", error);
        });
}