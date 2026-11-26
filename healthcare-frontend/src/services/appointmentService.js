import axios from 'axios';

const API_URL = 'http://localhost:9966/api/appointments';

const getAuthConfig = () => {
    const token = localStorage.getItem('token');

    return {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };
};

export const addAppointment = async (appointmentData) =>
    await axios.post(API_URL, appointmentData, getAuthConfig());

export const getAllAppointments = async () =>
    await axios.get(API_URL, getAuthConfig());

export const getAppointmentById = async (appointmentId) =>
    await axios.get(`${API_URL}/${appointmentId}`, getAuthConfig());

export const getPatientAppointments = async (patientId) =>
    await axios.get(`${API_URL}/patient/${patientId}`, getAuthConfig());

export const getDoctorAppointments = async (doctorId) =>
    await axios.get(`${API_URL}/doctor/${doctorId}`, getAuthConfig());

export const getAppointmentsByStatus = async (status) =>
    await axios.get(`${API_URL}/status/${status}`, getAuthConfig());

export const updateAppointment = async (appointmentId, appointmentData) =>
    await axios.put(`${API_URL}/${appointmentId}`, appointmentData, getAuthConfig());

export const deleteAppointment = async (appointmentId) =>
    await axios.delete(`${API_URL}/${appointmentId}`, getAuthConfig());