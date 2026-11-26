import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import {
    getAllMedicalHistory,
    deleteMedicalHistory
} from '../services/medicalHistoryService';

function MedicalHistoryList() {

    const [histories, setHistories] = useState([]);
    const navigate = useNavigate();

    const loadHistory = async () => {
        try {
            const response = await getAllMedicalHistory();
            setHistories(response.data);
        } catch (error) {
            console.error(error);
            alert('Unable to load medical history');
        }
    };

    useEffect(() => {
        loadHistory();
    }, []);

    const handleDelete = async (id) => {
        if (window.confirm('Are you sure you want to delete this record?')) {
            try {
                await deleteMedicalHistory(id);
                alert('Medical history deleted successfully');
                loadHistory();
            } catch (error) {
                console.error(error);
                alert('Unable to delete medical history');
            }
        }
    };

    return (
        <div className="container mt-4">

            <h2 className="text-center mb-4">Medical History Management</h2>

            <Link to="/medical-history/add" className="btn btn-success mb-3">
                Add Medical History
            </Link>

            <div className="table-responsive">
                <table className="table table-bordered table-striped">

                    <thead className="table-primary">
                        <tr>
                            <th>History ID</th>
                            <th>Patient ID</th>
                            <th>Doctor ID</th>
                            <th>Condition</th>
                            <th>Diagnosis Date</th>
                            <th>Treatment</th>
                            <th>Medications</th>
                            <th>Notes</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>
                        {histories.map((history) => (
                            <tr key={history.historyId}>
                                <td>{history.historyId}</td>
                                <td>{history.patientId}</td>
                                <td>{history.doctorId || 'N/A'}</td>
                                <td>{history.conditionName}</td>
                                <td>{history.diagnosisDate}</td>
                                <td>{history.treatment}</td>
                                <td>{history.medications}</td>
                                <td>{history.notes}</td>

                                <td>
                                    <button
                                        className="btn btn-primary btn-sm me-2"
                                        onClick={() => navigate(
                                            `/medical-history/edit/${history.historyId}`
                                        )}
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDelete(history.historyId)}
                                    >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>

                </table>
            </div>

            <Link to="/dashboard" className="btn btn-secondary">
                Back to Dashboard
            </Link>

        </div>
    );
}

export default MedicalHistoryList;