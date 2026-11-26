import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { addAppointment } from '../services/appointmentService';

function AddAppointment() {

    const navigate = useNavigate();

    const [appointment, setAppointment] = useState({
        patientId: '',
        doctorId: '',
        appointmentDateTime: '',
        reason: '',
        notes: ''
    });

    const handleChange = (e) => {
        setAppointment({
            ...appointment,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const appointmentData = {
                patientId: Number(appointment.patientId),
                doctorId: Number(appointment.doctorId),
                appointmentDateTime: appointment.appointmentDateTime,
                reason: appointment.reason,
                notes: appointment.notes,
                status: 'PENDING'
            };

            await addAppointment(appointmentData);

            alert('Appointment added successfully');

            navigate('/appointments');

        } catch (error) {
            console.error(error.response?.data || error);
            alert('Unable to add appointment');
        }
    };

    return (
        <div className="container mt-4">

            <h2 className="text-center mb-4">Book Appointment</h2>

            <div className="card shadow p-4">

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                        <label className="form-label">Patient ID</label>
                        <input
                            type="number"
                            name="patientId"
                            className="form-control"
                            value={appointment.patientId}
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
                            value={appointment.doctorId}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Appointment Date & Time</label>
                        <input
                            type="datetime-local"
                            name="appointmentDateTime"
                            className="form-control"
                            value={appointment.appointmentDateTime}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Reason</label>
                        <input
                            type="text"
                            name="reason"
                            className="form-control"
                            value={appointment.reason}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Notes</label>
                        <textarea
                            name="notes"
                            className="form-control"
                            value={appointment.notes}
                            onChange={handleChange}
                        />
                    </div>

                    <button type="submit" className="btn btn-success me-2">
                        Book Appointment
                    </button>

                    <Link to="/appointments" className="btn btn-secondary">
                        Cancel
                    </Link>

                </form>

            </div>

        </div>
    );
}

export default AddAppointment;