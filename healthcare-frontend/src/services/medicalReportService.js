import axios from 'axios';

const API_URL = 'http://localhost:9966/api/reports';

const getAuthConfig = () => {
    const token = localStorage.getItem('token');

    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
};

export const addMedicalReport = async (reportData) =>
    await axios.post(API_URL, reportData, getAuthConfig());

export const getAllMedicalReports = async () =>
    await axios.get(API_URL, getAuthConfig());

export const getMedicalReportById = async (reportId) =>
    await axios.get(`${API_URL}/${reportId}`, getAuthConfig());

export const getPatientReports = async (patientId) =>
    await axios.get(`${API_URL}/patient/${patientId}`, getAuthConfig());

export const getDoctorReports = async (doctorId) =>
    await axios.get(`${API_URL}/doctor/${doctorId}`, getAuthConfig());

export const getReportsByType = async (reportType) =>
    await axios.get(`${API_URL}/type/${encodeURIComponent(reportType)}`, getAuthConfig());

export const updateMedicalReport = async (reportId, reportData) =>
    await axios.put(`${API_URL}/${reportId}`, reportData, getAuthConfig());

export const deleteMedicalReport = async (reportId) =>
    await axios.delete(`${API_URL}/${reportId}`, getAuthConfig());