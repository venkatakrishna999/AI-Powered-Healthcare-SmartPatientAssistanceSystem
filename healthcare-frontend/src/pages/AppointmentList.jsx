import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getAllAppointments, deleteAppointment } from '../services/appointmentService';

function AppointmentList() {

    const [appointments, setAppointments] = useState([]);
    const navigate = useNavigate();

    const loadAppointments = async () => {
        try {
            const response = await getAllAppointments();
            setAppointments(response.data);
        } catch (error) {
            console.error(error);
            alert('Unable to load appointments');
        }
    };

    useEffect(() => {
        loadAppointments();
    }, []);

    const handleDelete = async (id) => {
        if (window.confirm('Are you sure you want to delete this appointment?')) {
            try {
                await deleteAppointment(id);
                alert('Appointment deleted successfully');
                loadAppointments();
            } catch (error) {
                console.error(error);
                alert('Unable to delete appointment');
            }
        }
    };

    return (
        <div className="container mt-4">

            <h2 className="text-center mb-4">Appointment Management</h2>

            <Link to="/appointments/add" className="btn btn-success mb-3">
                Add Appointment
            </Link>

            <div className="table-responsive">
                <table className="table table-bordered table-striped">

                    <thead className="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>Patient ID</th>
                            <th>Doctor ID</th>
                            <th>Date & Time</th>
                            <th>Reason</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>
                        {appointments.map((appointment) => (
                            <tr key={appointment.appointmentId}>
                                <td>{appointment.appointmentId}</td>
                                <td>{appointment.patientId}</td>
                                <td>{appointment.doctorId}</td>
                                <td>{appointment.appointmentDateTime}</td>
                                <td>{appointment.reason}</td>
                                <td>{appointment.status}</td>

                                <td>
                                    <button
                                        className="btn btn-primary btn-sm me-2"
                                        onClick={() => navigate(`/appointments/edit/${appointment.appointmentId}`)}
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDelete(appointment.appointmentId)}
                                    >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>

                </table>
            </div>

            <Link to="/dashboard" className="btn btn-secondary">
                Back to Dashboard
            </Link>

        </div>
    );
}

export default AppointmentList;