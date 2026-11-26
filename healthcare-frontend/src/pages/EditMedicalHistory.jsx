import React, { useEffect, useState } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';

import {
    getMedicalHistoryById,
    updateMedicalHistory
} from '../services/medicalHistoryService';

function EditMedicalHistory() {

    const { historyId } = useParams();
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

    useEffect(() => {
        loadHistory();
    }, []);

    const loadHistory = async () => {
        try {
            const response = await getMedicalHistoryById(historyId);
            const data = response.data;

            setHistory({
                patientId: data.patientId || '',
                doctorId: data.doctorId || '',
                conditionName: data.conditionName || '',
                diagnosisDate: data.diagnosisDate || '',
                treatment: data.treatment || '',
                medications: data.medications || '',
                notes: data.notes || ''
            });

        } catch (error) {
            console.error(error);
            alert('Unable to load medical history');
        }
    };

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

            await updateMedicalHistory(historyId, historyData);

            alert('Medical history updated successfully');

            navigate('/medical-history');

        } catch (error) {
            console.error(error.response?.data || error);
            alert('Unable to update medical history');
        }
    };

    return (
        <div className="container mt-4">

            <h2 className="text-center mb-4">Edit Medical History</h2>

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

                    <button type="submit" className="btn btn-primary me-2">
                        Update Medical History
                    </button>

                    <Link to="/medical-history" className="btn btn-secondary">
                        Cancel
                    </Link>

                </form>

            </div>
        </div>
    );
}

export default EditMedicalHistory;