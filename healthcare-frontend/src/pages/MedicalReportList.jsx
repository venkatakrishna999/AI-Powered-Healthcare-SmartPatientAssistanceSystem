import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import {
    getAllMedicalReports,
    deleteMedicalReport
} from '../services/medicalReportService';

function MedicalReportList() {

    const [reports, setReports] = useState([]);
    const navigate = useNavigate();

    const loadReports = async () => {
        try {
            const response = await getAllMedicalReports();
            setReports(response.data);
        } catch (error) {
            console.error(error);
            alert('Unable to load medical reports');
        }
    };

    useEffect(() => {
        loadReports();
    }, []);

    const handleDelete = async (id) => {

        if (window.confirm('Are you sure you want to delete this report?')) {

            try {
                await deleteMedicalReport(id);

                alert('Medical report deleted successfully');

                loadReports();

            } catch (error) {
                console.error(error);
                alert('Unable to delete medical report');
            }
        }
    };

    return (
        <div className="container mt-4">

            <h2 className="text-center mb-4">
                Medical Reports Management
            </h2>

            <Link to="/medical-reports/add" className="btn btn-success mb-3">
                Add Medical Report
            </Link>

            <div className="table-responsive">

                <table className="table table-bordered table-striped">

                    <thead className="table-primary">
                        <tr>
                            <th>Report ID</th>
                            <th>Patient ID</th>
                            <th>Doctor ID</th>
                            <th>Report Name</th>
                            <th>Report Type</th>
                            <th>Description</th>
                            <th>Report Date</th>
                           
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>

                        {reports.map((report) => (

                            <tr key={report.reportId}>

                                <td>{report.reportId}</td>
                                <td>{report.patientId}</td>
                                <td>{report.doctorId}</td>
                                <td>{report.reportName}</td>
                                <td>{report.reportType}</td>
                                <td>{report.description}</td>
                                <td>{report.reportDate}</td>

                               

                                <td>

                                    <button
                                        className="btn btn-primary btn-sm me-2"
                                        onClick={() => navigate(
                                            `/medical-reports/edit/${report.reportId}`
                                        )}
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDelete(report.reportId)}
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

export default MedicalReportList;