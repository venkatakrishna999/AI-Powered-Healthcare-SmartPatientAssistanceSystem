import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { sendAIMessage } from '../services/aiAssistantService';

function AIAssistant() {

    const [message, setMessage] = useState('');

    const [messages, setMessages] = useState([
        {
            sender: 'ai',
            text: 'Hello! Welcome to the AI Healthcare Assistant. How can I help you today?'
        }
    ]);

    const [loading, setLoading] = useState(false);

    const handleSend = async (e) => {

        e.preventDefault();

        if (!message.trim() || loading) {
            return;
        }

        const userMessage = message.trim();

        setMessages(prev => [
            ...prev,
            {
                sender: 'user',
                text: userMessage
            }
        ]);

        setMessage('');
        setLoading(true);

        try {

            const response = await sendAIMessage(userMessage);

            setMessages(prev => [
                ...prev,
                {
                    sender: 'ai',
                    text: response.response
                }
            ]);

        } catch (error) {

            console.error('AI Error:', error);

            setMessages(prev => [
                ...prev,
                {
                    sender: 'ai',
                    text: 'Unable to get a response right now. Please try again later.'
                }
            ]);

        } finally {

            setLoading(false);

        }
    };

    return (

        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>AI Healthcare Assistant</h2>

                <Link to="/dashboard" className="btn btn-secondary">
                    Back to Dashboard
                </Link>

            </div>

            <div className="card shadow">

                <div className="card-header bg-primary text-white">
                    <h5 className="mb-0">Healthcare Chat Assistant</h5>
                </div>

                <div
                    className="card-body"
                    style={{
                        height: '450px',
                        overflowY: 'auto',
                        backgroundColor: '#f8f9fa'
                    }}
                >

                    {messages.map((msg, index) => (

                        <div
                            key={index}
                            className={`d-flex mb-3 ${
                                msg.sender === 'user'
                                    ? 'justify-content-end'
                                    : 'justify-content-start'
                            }`}
                        >

                            <div
                                className={`p-3 rounded ${
                                    msg.sender === 'user'
                                        ? 'bg-primary text-white'
                                        : 'bg-white border'
                                }`}
                                style={{
                                    maxWidth: '75%',
                                    whiteSpace: 'pre-wrap'
                                }}
                            >

                                <strong>
                                    {msg.sender === 'user' ? 'You' : 'AI Assistant'}
                                </strong>

                                <div className="mt-1">
                                    {msg.text}
                                </div>

                            </div>

                        </div>

                    ))}

                    {loading && (
                        <div className="text-muted">
                            AI Assistant is thinking...
                        </div>
                    )}

                </div>

                <div className="card-footer">

                    <form onSubmit={handleSend} className="d-flex gap-2">

                        <input
                            type="text"
                            className="form-control"
                            placeholder="Ask a healthcare question..."
                            value={message}
                            onChange={(e) => setMessage(e.target.value)}
                            disabled={loading}
                        />

                        <button
                            type="submit"
                            className="btn btn-primary"
                            disabled={loading || !message.trim()}
                        >
                            Send
                        </button>

                    </form>

                </div>

            </div>

            <div className="alert alert-warning mt-3">
                <strong>Medical Disclaimer:</strong> This AI assistant
                provides general healthcare information only. It does not
                diagnose diseases or prescribe medicines. Consult a qualified
                doctor for medical advice. For emergencies, seek immediate
                medical attention.
            </div>

        </div>

    );

}

export default AIAssistant;