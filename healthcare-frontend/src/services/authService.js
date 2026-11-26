import axios from 'axios';

const API_URL = 'http://localhost:9966/api';

export const registerUser = async (userData) => {
    return await axios.post(`${API_URL}/users/register`, userData);
};

export const loginUser = async (loginData) => {
    return await axios.post(`${API_URL}/auth/login`, loginData);
};