import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';

import {userActions} from '../_actions';

class RegistrationPage extends React.Component {
    constructor(props) {
        super(props);

        this.props.dispatch(userActions.logout());

        this.state = {
            username: '',
            loginname: '',
            password: '',
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState({[name]: value});
    }

    handleSubmit(e) {
        e.preventDefault();

        this.setState({submitted: true});
        const {username, loginname, password} = this.state;
        const {dispatch} = this.props;
        if (username && loginname && password) {
            dispatch(userActions.registration(username, loginname, password));
        }
    }

    render() {
        const {username, loginname, password, submitted} = this.state;
        return (
            <div className="col-md-6 col-md-offset-3">

                <h2>Registration</h2>
                <form name="form" onSubmit={this.handleSubmit}>
                    <div className={'form-group' + (submitted && !username ? ' has-error' : '')}>
                        <label htmlFor="username">Username</label>
                        <input type="text" className="form-control" name="username" value={username}
                               onChange={this.handleChange}/>
                        {submitted && !username &&
                        <div className="help-block">Username is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !username ? ' has-error' : '')}>
                        <label htmlFor="username">Login</label>
                        <input type="text" className="form-control" name="loginname" value={loginname}
                               onChange={this.handleChange}/>
                        {submitted && !loginname &&
                        <div className="help-block">Login is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !password ? ' has-error' : '')}>
                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" name="password" value={password}
                               onChange={this.handleChange}/>
                        {submitted && !password &&
                        <div className="help-block">Password is required</div>
                        }
                    </div>
                    <div className="form-group">
                        <button className="btn btn-primary">Registration</button>

                    </div>
                </form>
                <p>
                    <Link to="/login">Login</Link>
                </p>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const {loggingIn} = state.authentication;
    return {
        loggingIn
    };
}

const connectedRegistrationPage = connect(mapStateToProps)(RegistrationPage);
export {connectedRegistrationPage as RegistrationPage};