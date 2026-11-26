import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import {
    getAllDoctors,
    deleteDoctor
} from '../services/doctorService';

function DoctorList() {

    const navigate = useNavigate();

    const [doctors, setDoctors] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);

    const fetchDoctors = async () => {
        try {
            const response = await getAllDoctors();
            setDoctors(response.data);
            setError('');
        } catch (err) {
            console.error(err);
            setError('Unable to load doctors.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchDoctors();
    }, []);

    const handleDelete = async (doctorId) => {

        if (!window.confirm('Are you sure you want to delete this doctor?')) {
            return;
        }

        try {
            await deleteDoctor(doctorId);
            alert('Doctor deleted successfully!');
            fetchDoctors();
        } catch (err) {
            console.error(err);
            alert('Unable to delete doctor.');
        }
    };

    if (loading) {
        return <h4 className="text-center mt-5">Loading doctors...</h4>;
    }

    return (
        <div className="container mt-5">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2 className="text-primary">Doctor Management</h2>

                <Link to="/doctors/add" className="btn btn-success">
                    + Add Doctor
                </Link>

            </div>

            {error && (
                <div className="alert alert-danger">{error}</div>
            )}

            <div className="table-responsive">

                <table className="table table-bordered table-striped">

                    <thead className="table-dark">
                        <tr>
                            <th>Doctor ID</th>
                            <th>User ID</th>
                            <th>Specialization</th>
                            <th>Qualification</th>
                            <th>Experience</th>
                            <th>Consultation Fee</th>
                            <th>Hospital</th>
                            <th>Availability</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>

                        {doctors.length > 0 ? (

                            doctors.map((doctor) => (

                                <tr key={doctor.doctorId}>

                                    <td>{doctor.doctorId}</td>
                                    <td>{doctor.user?.userId}</td>
                                    <td>{doctor.specialization}</td>
                                    <td>{doctor.qualification}</td>
                                    <td>{doctor.experienceYears} years</td>
                                    <td>₹{doctor.consultationFee}</td>
                                    <td>{doctor.hospitalName}</td>

                                    <td>
                                        <span className={
                                            doctor.available
                                                ? 'badge bg-success'
                                                : 'badge bg-danger'
                                        }>
                                            {doctor.available ? 'Available' : 'Unavailable'}
                                        </span>
                                    </td>

                                    <td>

                                        <button
                                            className="btn btn-warning btn-sm me-2"
                                            onClick={() => navigate(`/doctors/edit/${doctor.doctorId}`)}
                                        >
                                            Edit
                                        </button>

                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={() => handleDelete(doctor.doctorId)}
                                        >
                                            Delete
                                        </button>

                                    </td>

                                </tr>

                            ))

                        ) : (

                            <tr>
                                <td colSpan="9" className="text-center">
                                    No doctors found.
                                </td>
                            </tr>

                        )}

                    </tbody>

                </table>

            </div>

            <Link to="/dashboard" className="btn btn-secondary">
                Back to Dashboard
            </Link>

        </div>
    );
}

export default DoctorList;