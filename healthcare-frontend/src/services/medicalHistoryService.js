import axios from 'axios';

const API_URL = `${import.meta.env.VITE_API_URL}/api/medical-history`;

const getAuthConfig = () => {
    const token = localStorage.getItem('token');

    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
};

export const addMedicalHistory = async (historyData) =>
    await axios.post(API_URL, historyData, getAuthConfig());

export const getAllMedicalHistory = async () =>
    await axios.get(API_URL, getAuthConfig());

export const getMedicalHistoryById = async (historyId) =>
    await axios.get(`${API_URL}/${historyId}`, getAuthConfig());

export const getPatientHistory = async (patientId) =>
    await axios.get(`${API_URL}/patient/${patientId}`, getAuthConfig());

export const getDoctorHistory = async (doctorId) =>
    await axios.get(`${API_URL}/doctor/${doctorId}`, getAuthConfig());

export const getHistoryByCondition = async (conditionName) =>
    await axios.get(
        `${API_URL}/condition/${encodeURIComponent(conditionName)}`,
        getAuthConfig()
    );

export const updateMedicalHistory = async (historyId, historyData) =>
    await axios.put(`${API_URL}/${historyId}`, historyData, getAuthConfig());

export const deleteMedicalHistory = async (historyId) =>
    await axios.delete(`${API_URL}/${historyId}`, getAuthConfig());
