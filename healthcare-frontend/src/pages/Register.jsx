import React, { useState } from 'react';
import { registerUser } from '../services/authService';

function Register() {

    const [userData, setUserData] = useState({
        username: '',
        email: '',
        password: '',
        role: ''
    });

    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleChange = (e) => {
        setUserData({
            ...userData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setMessage('');
        setError('');

        try {
            const response = await registerUser(userData);

            setMessage('Registration successful!');

            setUserData({
                username: '',
                email: '',
                password: '',
                role: ''
            });

            console.log(response.data);

        } catch (err) {
            console.error(err);
            setError(
                err.response?.data?.message ||
                'Registration failed. Please check your details.'
            );
        }
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">

                    <div className="card shadow p-4">

                        <h2 className="text-center text-primary">
                            Healthcare Management System
                        </h2>

                        <h4 className="text-center mt-3">
                            Create Account
                        </h4>

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
                                    value={userData.username}
                                    onChange={handleChange}
                                    required
                                />
                            </div>

                            <div className="mb-3">
                                <label className="form-label">Email</label>
                                <input
                                    type="email"
                                    name="email"
                                    className="form-control"
                                    placeholder="Enter email"
                                    value={userData.email}
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
                                    value={userData.password}
                                    onChange={handleChange}
                                    required
                                />
                            </div>

                            <div className="mb-3">
                                <label className="form-label">Role</label>
                                <select
                                    name="role"
                                    className="form-select"
                                    value={userData.role}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="">Select Role</option>
                                    <option value="PATIENT">Patient</option>
                                    <option value="DOCTOR">Doctor</option>
                                </select>
                            </div>

                            <button
                                type="submit"
                                className="btn btn-primary w-100"
                            >
                                Register
                            </button>

                        </form>

                    </div>

                </div>
            </div>
        </div>
    );
}

export default Register;