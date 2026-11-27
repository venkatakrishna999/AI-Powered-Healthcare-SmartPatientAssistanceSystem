import axios from 'axios';

const API_URL = `${import.meta.env.VITE_API_URL}/api/ai`;

export const sendAIMessage = async (message) => {

    const token = localStorage.getItem('token');

    const response = await axios.post(
        `${API_URL}/chat`,
        {
            message: message
        },
        {
            headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        }
    );

    return response.data;
};
