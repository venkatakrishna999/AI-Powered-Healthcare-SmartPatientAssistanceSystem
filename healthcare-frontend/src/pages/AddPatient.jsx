import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addPatient } from '../services/patientService';

function AddPatient() {

    const navigate = useNavigate();

   const [patient, setPatient] = useState({
        firstName: '',
        lastName: '',
        dateOfBirth: '',
        gender: '',
        phoneNumber: '',
        address: '',
        bloodGroup: '',
        emergencyContact: ''
    });

    const [error, setError] = useState('');

    const handleChange = (e) => {
        setPatient({
            ...patient,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

      const patientData = {
    userId: Number(localStorage.getItem('userId')),
    firstName: patient.firstName,
    lastName: patient.lastName,
    dateOfBirth: patient.dateOfBirth || null,
    gender: patient.gender,
    phoneNumber: patient.phoneNumber,
    address: patient.address,
    bloodGroup: patient.bloodGroup,
    emergencyContact: patient.emergencyContact
};
        console.log("Patient Request Data:", JSON.stringify(patientData, null, 2));
        try {
            await addPatient(patientData);
            alert('Patient added successfully!');
            navigate('/patients');
        } catch (err) {
            console.error(err);
            setError(
                err.response?.data?.message ||
                'Unable to add patient. Please check the User ID and details.'
            );
        }
    };

    return (
        <div className="container mt-5">

            <div className="card shadow p-4">

                <h2 className="text-center text-primary mb-4">
                    Add New Patient
                </h2>

                {error && (
                    <div className="alert alert-danger">{error}</div>
                )}

                <form onSubmit={handleSubmit}>

                    <div className="row">

                       

                        <div className="col-md-6 mb-3">
                            <label>First Name</label>
                            <input
                                type="text"
                                name="firstName"
                                className="form-control"
                                value={patient.firstName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label>Last Name</label>
                            <input
                                type="text"
                                name="lastName"
                                className="form-control"
                                value={patient.lastName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label>Date of Birth</label>
                            <input
                                type="date"
                                name="dateOfBirth"
                                className="form-control"
                                value={patient.dateOfBirth}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label>Gender</label>
                            <select
                                name="gender"
                                className="form-control"
                                value={patient.gender}
                                onChange={handleChange}
                            >
                                <option value="">Select Gender</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>

                        <div className="col-md-6 mb-3">
                            <label>Phone Number</label>
                            <input
                                type="tel"
                                name="phoneNumber"
                                className="form-control"
                                value={patient.phoneNumber}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label>Blood Group</label>
                            <select
                                name="bloodGroup"
                                className="form-control"
                                value={patient.bloodGroup}
                                onChange={handleChange}
                            >
                                <option value="">Select Blood Group</option>
                                <option value="A+">A+</option>
                                <option value="A-">A-</option>
                                <option value="B+">B+</option>
                                <option value="B-">B-</option>
                                <option value="O+">O+</option>
                                <option value="O-">O-</option>
                                <option value="AB+">AB+</option>
                                <option value="AB-">AB-</option>
                            </select>
                        </div>

                        <div className="col-md-6 mb-3">
                            <label>Emergency Contact</label>
                            <input
                                type="tel"
                                name="emergencyContact"
                                className="form-control"
                                value={patient.emergencyContact}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-12 mb-3">
                            <label>Address</label>
                            <textarea
                                name="address"
                                className="form-control"
                                rows="3"
                                value={patient.address}
                                onChange={handleChange}
                            />
                        </div>

                    </div>

                    <button type="submit" className="btn btn-primary">
                        Add Patient
                    </button>

                    <button
                        type="button"
                        className="btn btn-secondary ms-2"
                        onClick={() => navigate('/patients')}
                    >
                        Cancel
                    </button>

                </form>

            </div>

        </div>
    );
}

export default AddPatient;
