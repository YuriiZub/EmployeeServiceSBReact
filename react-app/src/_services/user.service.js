
export const userService = {
	doLoginUser,
	addNewUser,
    logout
};

   function   doLoginUser(user) {
        console.log("try to Login");

           return fetch(`http://localhost:8085/login`, {

            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
    })

.then(handleResponse)
        .then(user => {
			console.log("usertoken", user);
            // login successful if there's a jwt token in the response
            if (user.token) {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('user', user.username);
		     	localStorage.setItem('token', user.token);
            }

            return user;
        });
    }


    async function addNewUser (user) {
        console.log("try to register user in Registration");
        await fetch(`http://localhost:8085/registration`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
		.then(() => {
              console.log("registered");
            })
            .catch(console.log);
    }

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('user');
	localStorage.removeItem('token');
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                // auto logout if 401 response returned from api
                logout();
               // location.reload(true);
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}