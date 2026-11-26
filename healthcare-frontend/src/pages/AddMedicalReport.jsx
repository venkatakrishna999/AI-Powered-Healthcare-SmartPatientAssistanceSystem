import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

import { addMedicalReport } from '../services/medicalReportService';

function AddMedicalReport() {

    const navigate = useNavigate();

    const [report, setReport] = useState({
        patientId: '',
        doctorId: '',
        reportName: '',
        reportType: '',
        description: '',
       
        reportDate: ''
    });

    const handleChange = (e) => {
        setReport({
            ...report,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const reportData = {

                patientId: Number(report.patientId),

                doctorId: Number(report.doctorId),

                reportName: report.reportName,
                reportType: report.reportType,
                description: report.description,
                
                reportDate: report.reportDate

            };

            await addMedicalReport(reportData);

            alert('Medical report added successfully');

            navigate('/medical-reports');

        } catch (error) {

            console.error(error.response?.data || error);

            alert('Unable to add medical report');
        }
    };

    return (

        <div className="container mt-4">

            <h2 className="text-center mb-4">
                Add Medical Report
            </h2>

            <div className="card shadow p-4">

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                        <label className="form-label">Patient ID</label>

                        <input
                            type="number"
                            name="patientId"
                            className="form-control"
                            value={report.patientId}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Doctor ID</label>

                        <input
                            type="number"
                            name="doctorId"
                            className="form-control"
                            value={report.doctorId}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Report Name</label>

                        <input
                            type="text"
                            name="reportName"
                            className="form-control"
                            value={report.reportName}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Report Type</label>

                        <input
                            type="text"
                            name="reportType"
                            className="form-control"
                            value={report.reportType}
                            onChange={handleChange}
                            placeholder="Example: Blood Test, X-Ray"
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Description</label>

                        <textarea
                            name="description"
                            className="form-control"
                            value={report.description}
                            onChange={handleChange}
                        />
                    </div>

                    

                    <div className="mb-3">
                        <label className="form-label">Report Date</label>

                        <input
                            type="date"
                            name="reportDate"
                            className="form-control"
                            value={report.reportDate}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <button type="submit" className="btn btn-success me-2">
                        Save Medical Report
                    </button>

                    <Link to="/medical-reports" className="btn btn-secondary">
                        Cancel
                    </Link>

                </form>

            </div>

        </div>
    );
}

export default AddMedicalReport;