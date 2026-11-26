import axios from 'axios';

const API_URL = 'http://localhost:9966/api/doctors';

const getAuthConfig = () => {
    const token = localStorage.getItem('token');

    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
};

// Add Doctor
export const addDoctor = async (doctorData) =>
    await axios.post(API_URL, doctorData, getAuthConfig());

// Get All Doctors
export const getAllDoctors = async () =>
    await axios.get(API_URL, getAuthConfig());

// Get Doctor By ID
export const getDoctorById = async (doctorId) =>
    await axios.get(`${API_URL}/${doctorId}`, getAuthConfig());

// Get Doctor By User ID
export const getDoctorByUserId = async (userId) =>
    await axios.get(`${API_URL}/user/${userId}`, getAuthConfig());

// Get Doctors By Specialization
export const getDoctorsBySpecialization = async (specialization) =>
    await axios.get(
        `${API_URL}/specialization/${encodeURIComponent(specialization)}`,
        getAuthConfig()
    );

// Get Available Doctors
export const getAvailableDoctors = async () =>
    await axios.get(`${API_URL}/available`, getAuthConfig());

// Update Doctor
export const updateDoctor = async (doctorId, doctorData) =>
    await axios.put(
        `${API_URL}/${doctorId}`,
        doctorData,
        getAuthConfig()
    );

// Delete Doctor
export const deleteDoctor = async (doctorId) =>
    await axios.delete(`${API_URL}/${doctorId}`, getAuthConfig());