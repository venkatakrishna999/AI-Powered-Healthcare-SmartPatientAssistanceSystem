import axios from 'axios';

const API_URL = `${import.meta.env.VITE_API_URL}/api/patients`;

// Get JWT token
const getAuthConfig = () => {
    const token = localStorage.getItem('token');

    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
};

// Add Patient
export const addPatient = async (patientData) => {
    return await axios.post(API_URL, patientData, getAuthConfig());
};

// Get All Patients
export const getAllPatients = async () => {
    return await axios.get(API_URL, getAuthConfig());
};

// Get Patient By ID
export const getPatientById = async (patientId) => {
    return await axios.get(`${API_URL}/${patientId}`, getAuthConfig());
};

// Get Patient By User ID
export const getPatientByUserId = async (userId) => {
    return await axios.get(`${API_URL}/user/${userId}`, getAuthConfig());
};

// Update Patient
export const updatePatient = async (patientId, patientData) => {
    return await axios.put(
        `${API_URL}/${patientId}`,
        patientData,
        getAuthConfig()
    );
};

// Delete Patient
export const deletePatient = async (patientId) => {
    return await axios.delete(
        `${API_URL}/${patientId}`,
        getAuthConfig()
    );
};
