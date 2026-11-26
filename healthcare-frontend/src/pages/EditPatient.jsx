import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

import {
    getPatientById,
    updatePatient
} from '../services/patientService';

function EditPatient() {

    const { patientId } = useParams();
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
    const [loading, setLoading] = useState(true);

    useEffect(() => {

        const fetchPatient = async () => {
            try {
                const response = await getPatientById(patientId);
                setPatient(response.data);
            } catch (err) {
                console.error(err);
                setError('Unable to load patient details.');
            } finally {
                setLoading(false);
            }
        };

        fetchPatient();

    }, [patientId]);

    const handleChange = (e) => {
        setPatient({
            ...patient,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            await updatePatient(patientId, patient);

            alert('Patient updated successfully!');
            navigate('/patients');

        } catch (err) {
            console.error(err);
            setError(
                err.response?.data?.message ||
                'Unable to update patient.'
            );
        }
    };

    if (loading) {
        return <h4 className="text-center mt-5">Loading patient details...</h4>;
    }

    return (
        <div className="container mt-5">

            <div className="card shadow p-4">

                <h2 className="text-center text-primary mb-4">
                    Edit Patient
                </h2>

                {error && (
                    <div className="alert alert-danger">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit}>

                    <div className="row">

                        <div className="col-md-6 mb-3">
                            <label className="form-label">First Name</label>
                            <input
                                type="text"
                                name="firstName"
                                className="form-control"
                                value={patient.firstName || ''}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Last Name</label>
                            <input
                                type="text"
                                name="lastName"
                                className="form-control"
                                value={patient.lastName || ''}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Date of Birth</label>
                            <input
                                type="date"
                                name="dateOfBirth"
                                className="form-control"
                                value={patient.dateOfBirth || ''}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Gender</label>
                            <select
                                name="gender"
                                className="form-select"
                                value={patient.gender || ''}
                                onChange={handleChange}
                            >
                                <option value="">Select Gender</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Phone Number</label>
                            <input
                                type="text"
                                name="phoneNumber"
                                className="form-control"
                                value={patient.phoneNumber || ''}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Blood Group</label>
                            <select
                                name="bloodGroup"
                                className="form-select"
                                value={patient.bloodGroup || ''}
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

                        <div className="col-md-12 mb-3">
                            <label className="form-label">Address</label>
                            <textarea
                                name="address"
                                className="form-control"
                                value={patient.address || ''}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Emergency Contact</label>
                            <input
                                type="text"
                                name="emergencyContact"
                                className="form-control"
                                value={patient.emergencyContact || ''}
                                onChange={handleChange}
                            />
                        </div>

                    </div>

                    <button type="submit" className="btn btn-primary me-2">
                        Update Patient
                    </button>

                    <button
                        type="button"
                        className="btn btn-secondary"
                        onClick={() => navigate('/patients')}
                    >
                        Cancel
                    </button>

                </form>

            </div>

        </div>
    );
}

export default EditPatient;