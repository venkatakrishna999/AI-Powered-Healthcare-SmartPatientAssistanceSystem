import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { loginUser } from '../services/authService';

function Login() {

    const navigate = useNavigate();

    const [loginData, setLoginData] = useState({
        username: '',
        password: ''
    });

    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleChange = (e) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setMessage('');
        setError('');

        try {
            const response = await loginUser(loginData);

            localStorage.setItem('token', response.data.token);

            setMessage('Login successful!');

            console.log('JWT Token:', response.data.token);

            navigate('/dashboard');

        } catch (err) {
            console.error(err);

            setError(
                err.response?.data?.message ||
                'Login failed. Please check your username and password.'
            );
        }
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-5">
                    <div className="card shadow p-4">

                        <h2 className="text-center text-primary">
                            Healthcare Management System
                        </h2>

                        <h4 className="text-center mt-3">Login</h4>

                        {message && (
                            <div className="alert alert-success">
                                {message}
                            </div>
                        )}

                        {error && (
                            <div className="alert alert-danger">
                                {error}
                            </div>
                        )}

                        <form onSubmit={handleSubmit}>

                            <div className="mb-3">
                                <label className="form-label">Username</label>
                                <input
                                    type="text"
                                    name="username"
                                    className="form-control"
                                    placeholder="Enter username"
                                    value={loginData.username}
                                    onChange={handleChange}
                                    required
                                />
                            </div>

                            <div className="mb-3">
                                <label className="form-label">Password</label>
                                <input
                                    type="password"
                                    name="password"
                                    className="form-control"
                                    placeholder="Enter password"
                                    value={loginData.password}
                                    onChange={handleChange}
                                    required
                                />
                            </div>

                            <button type="submit" className="btn btn-primary w-100">
                                Login
                            </button>

                        </form>

                        <p className="text-center mt-3">
                            Don't have an account?{' '}
                            <Link to="/register">Register</Link>
                        </p>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;