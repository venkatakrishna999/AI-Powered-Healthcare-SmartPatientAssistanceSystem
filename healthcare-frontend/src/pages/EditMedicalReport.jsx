import React, { useEffect, useState } from 'react';

import { useNavigate, useParams, Link } from 'react-router-dom';

import {
    getMedicalReportById,
    updateMedicalReport
} from '../services/medicalReportService';

function EditMedicalReport() {

    const { reportId } = useParams();
    const navigate = useNavigate();

    const [report, setReport] = useState({
        patientId: '',
        doctorId: '',
        reportName: '',
        reportType: '',
        description: '',
        fileUrl: '',
        reportDate: ''
    });

    useEffect(() => {
        loadReport();
    }, []);

    const loadReport = async () => {

        try {

            const response = await getMedicalReportById(reportId);

            const data = response.data;

            setReport({

                patientId: data.patientId || '',
                doctorId: data.doctorId || '',
                reportName: data.reportName || '',
                reportType: data.reportType || '',
                description: data.description || '',
                fileUrl: data.fileUrl || '',
                reportDate: data.reportDate || ''

            });

        } catch (error) {

            console.error(error);

            alert('Unable to load medical report');
        }
    };

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
                fileUrl: report.fileUrl,
                reportDate: report.reportDate

            };

            await updateMedicalReport(reportId, reportData);

            alert('Medical report updated successfully');

            navigate('/medical-reports');

        } catch (error) {

            console.error(error.response?.data || error);

            alert('Unable to update medical report');
        }
    };

    return (

        <div className="container mt-4">

            <h2 className="text-center mb-4">
                Edit Medical Report
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
                        <label className="form-label">File URL</label>

                        <input
                            type="text"
                            name="fileUrl"
                            className="form-control"
                            value={report.fileUrl}
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

                    <button type="submit" className="btn btn-primary me-2">
                        Update Medical Report
                    </button>

                    <Link to="/medical-reports" className="btn btn-secondary">
                        Cancel
                    </Link>

                </form>

            </div>

        </div>
    );
}

export default EditMedicalReport;