import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import {
    getAllPatients,
    deletePatient
} from '../services/patientService';

function PatientList() {

    const navigate = useNavigate();

    const [patients, setPatients] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);

    const fetchPatients = async () => {
        try {
            const response = await getAllPatients();
            setPatients(response.data);
            setError('');
        } catch (err) {
            console.error(err);
            setError('Unable to load patients.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchPatients();
    }, []);

    const handleDelete = async (patientId) => {

        if (!window.confirm('Are you sure you want to delete this patient?')) {
            return;
        }

        try {
            await deletePatient(patientId);
            alert('Patient deleted successfully!');
            fetchPatients();
        } catch (err) {
            console.error(err);
            alert('Unable to delete patient.');
        }
    };

    if (loading) {
        return <h4 className="text-center mt-5">Loading patients...</h4>;
    }

    return (
        <div className="container mt-5">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2 className="text-primary">Patient Management</h2>

                <Link to="/patients/add" className="btn btn-success">
                    + Add Patient
                </Link>

            </div>

            {error && (
                <div className="alert alert-danger">{error}</div>
            )}

            <div className="table-responsive">

                <table className="table table-bordered table-striped">

                    <thead className="table-dark">
                        <tr>
                            <th>Patient ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Date of Birth</th>
                            <th>Gender</th>
                            <th>Phone</th>
                            <th>Blood Group</th>
                            <th>Address</th>
                            <th>Emergency Contact</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>

                        {patients.length > 0 ? (

                            patients.map((patient) => (

                                <tr key={patient.patientId}>

                                    <td>{patient.patientId}</td>
                                    <td>{patient.firstName}</td>
                                    <td>{patient.lastName}</td>
                                    <td>{patient.dateOfBirth}</td>
                                    <td>{patient.gender}</td>
                                    <td>{patient.phoneNumber}</td>
                                    <td>{patient.bloodGroup}</td>
                                    <td>{patient.address}</td>
                                    <td>{patient.emergencyContact}</td>

                                    <td>
                                        <button
                                            className="btn btn-warning btn-sm me-2"
                                            onClick={() => navigate(`/patients/edit/${patient.patientId}`)}
                                        >
                                            Edit
                                        </button>

                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={() => handleDelete(patient.patientId)}
                                        >
                                            Delete
                                        </button>
                                    </td>

                                </tr>

                            ))

                        ) : (

                            <tr>
                                <td colSpan="10" className="text-center">
                                    No patients found.
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

export default PatientList;