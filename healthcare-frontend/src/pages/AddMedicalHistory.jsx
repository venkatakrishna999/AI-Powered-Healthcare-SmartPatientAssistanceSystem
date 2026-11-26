import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

import { addMedicalHistory } from '../services/medicalHistoryService';

function AddMedicalHistory() {

    const navigate = useNavigate();

    const [history, setHistory] = useState({
        patientId: '',
        doctorId: '',
        conditionName: '',
        diagnosisDate: '',
        treatment: '',
        medications: '',
        notes: ''
    });

    const handleChange = (e) => {
        setHistory({
            ...history,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const historyData = {
                patientId: Number(history.patientId),

                doctorId: history.doctorId ? Number(history.doctorId) : null,

                conditionName: history.conditionName,
                diagnosisDate: history.diagnosisDate,
                treatment: history.treatment,
                medications: history.medications,
                notes: history.notes
            };

            await addMedicalHistory(historyData);

            alert('Medical history added successfully');

            navigate('/medical-history');

        } catch (error) {
            console.error(error.response?.data || error);
            alert('Unable to add medical history');
        }
    };

    return (
        <div className="container mt-4">

            <h2 className="text-center mb-4">Add Medical History</h2>

            <div className="card shadow p-4">

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                        <label className="form-label">Patient ID</label>
                        <input
                            type="number"
                            name="patientId"
                            className="form-control"
                            value={history.patientId}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Doctor ID (Optional)</label>
                        <input
                            type="number"
                            name="doctorId"
                            className="form-control"
                            value={history.doctorId}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Condition Name</label>
                        <input
                            type="text"
                            name="conditionName"
                            className="form-control"
                            value={history.conditionName}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Diagnosis Date</label>
                        <input
                            type="date"
                            name="diagnosisDate"
                            className="form-control"
                            value={history.diagnosisDate}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Treatment</label>
                        <textarea
                            name="treatment"
                            className="form-control"
                            value={history.treatment}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Medications</label>
                        <textarea
                            name="medications"
                            className="form-control"
                            value={history.medications}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Notes</label>
                        <textarea
                            name="notes"
                            className="form-control"
                            value={history.notes}
                            onChange={handleChange}
                        />
                    </div>

                    <button type="submit" className="btn btn-success me-2">
                        Save Medical History
                    </button>

                    <Link to="/medical-history" className="btn btn-secondary">
                        Cancel
                    </Link>

                </form>

            </div>
        </div>
    );
}

export default AddMedicalHistory;