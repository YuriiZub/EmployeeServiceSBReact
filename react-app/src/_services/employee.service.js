import {userConstants} from '../_constants';

export const employeeService = {
    removeEmployee,
    addEmployee,
    editEmployee,
    selectEmployeePage
};

function removeEmployee(id) {

    let token = userConstants.SECRET_WORLD + localStorage.getItem("token");
    let ref = userConstants.SERVER_ADDRESS + `/employee/${id}`;
    console.log("delrequsest=", ref)
    const requestOptions = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": token
        }
    };

    return fetch(ref, requestOptions)
        .then(response => {

            return response;
        });
}

function addEmployee(employee) {
    console.log("addEmpl=", employee);

    let token = userConstants.SECRET_WORLD + localStorage.getItem("token");
    let ref = userConstants.SERVER_ADDRESS + '/employee/';

    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": token
        },
        body: JSON.stringify(employee)
    };

    return fetch(ref, requestOptions)
        .then(response => {
            return response;
        });
}

function editEmployee(employee) {

    let token = userConstants.SECRET_WORLD + localStorage.getItem("token");
    let ref = userConstants.SERVER_ADDRESS + '/employee/';

    const requestOptions = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": token
        },
        body: JSON.stringify(employee)
    };

    return fetch(ref, requestOptions)
        .then(response => {
            return response;
        });
}

function selectEmployeePage(recordsOnPage, currentPage, pageCountOnBook, sortingField, findValue) {

    let token = userConstants.SECRET_WORLD + localStorage.getItem("token");
    let ref = userConstants.SERVER_ADDRESS + `/employee/page/${recordsOnPage}/${pageCountOnBook}/${currentPage}/${sortingField}/${findValue}`;
    if (findValue == null) ref =  userConstants.SERVER_ADDRESS + `/employee/page/${recordsOnPage}/${pageCountOnBook}/${currentPage}/${sortingField}`;

    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": token
        },
    };

    return fetch(ref, requestOptions)

        .then(res => res.json())
        .then((data) => {

            return data;
        })
        .catch((error) => {
            console.log("myerror=", error);
        });
}
