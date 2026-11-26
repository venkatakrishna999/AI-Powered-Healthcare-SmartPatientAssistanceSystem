import React, { useEffect, useState } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';

import {
    getAppointmentById,
    updateAppointment
} from '../services/appointmentService';

function EditAppointment() {

    const { appointmentId } = useParams();
    const navigate = useNavigate();

    const [appointment, setAppointment] = useState({
        patientId: '',
        doctorId: '',
        appointmentDateTime: '',
        reason: '',
        status: 'PENDING',
        notes: ''
    });

    useEffect(() => {
        loadAppointment();
    }, []);

    const loadAppointment = async () => {
        try {
            const response = await getAppointmentById(appointmentId);
            const data = response.data;

            setAppointment({
                patientId: data.patientId || '',
                doctorId: data.doctorId || '',
                appointmentDateTime: (data.appointmentDateTime || '').slice(0, 16) || '',
                reason: data.reason || '',
                status: data.status || 'PENDING',
                notes: data.notes || ''
            });

        } catch (error) {
            console.error(error);
            alert('Unable to load appointment');
        }
    };

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
                status: appointment.status,
                notes: appointment.notes
            };

            await updateAppointment(appointmentId, appointmentData);

            alert('Appointment updated successfully');

            navigate('/appointments');

        } catch (error) {
            console.error(error.response?.data || error);
            alert('Unable to update appointment');
        }
    };

    return (
        <div className="container mt-4">

            <h2 className="text-center mb-4">Edit Appointment</h2>

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
                        <label className="form-label">Status</label>
                        <select
                            name="status"
                            className="form-select"
                            value={appointment.status}
                            onChange={handleChange}
                        >
                            <option value="PENDING">PENDING</option>
                            <option value="CONFIRMED">CONFIRMED</option>
                            <option value="COMPLETED">COMPLETED</option>
                            <option value="CANCELLED">CANCELLED</option>
                        </select>
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

                    <button type="submit" className="btn btn-primary me-2">
                        Update Appointment
                    </button>

                    <Link to="/appointments" className="btn btn-secondary">
                        Cancel
                    </Link>

                </form>

            </div>

        </div>
    );
}

export default EditAppointment;