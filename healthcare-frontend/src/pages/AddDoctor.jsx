import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addDoctor } from '../services/doctorService';

function AddDoctor() {

    const navigate = useNavigate();

    const [doctor, setDoctor] = useState({
        userId: '',
        specialization: '',
        qualification: '',
        experienceYears: '',
        consultationFee: '',
        hospitalName: '',
        bio: '',
        available: true
    });

    const [error, setError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;

        setDoctor({
            ...doctor,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        const doctorData = {
            user: {
                userId: Number(doctor.userId)
            },
            specialization: doctor.specialization,
            qualification: doctor.qualification,
            experienceYears: Number(doctor.experienceYears),
            consultationFee: Number(doctor.consultationFee),
            hospitalName: doctor.hospitalName,
            bio: doctor.bio,
            available: doctor.available === true
                || doctor.available === 'true'
        };

        try {
            await addDoctor(doctorData);

            alert('Doctor added successfully!');
            navigate('/doctors');

        } catch (err) {
            console.error(err);

            setError(
                err.response?.data?.message ||
                'Unable to add doctor. Please check the details.'
            );
        }
    };

    return (
        <div className="container mt-5">

            <div className="card shadow p-4">

                <h2 className="text-center text-primary mb-4">
                    Add New Doctor
                </h2>

                {error && (
                    <div className="alert alert-danger">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit}>

                    <div className="row">

                        <div className="col-md-6 mb-3">
                            <label className="form-label">User ID</label>
                            <input
                                type="number"
                                name="userId"
                                className="form-control"
                                value={doctor.userId}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Specialization</label>
                            <input
                                type="text"
                                name="specialization"
                                className="form-control"
                                placeholder="e.g. Cardiologist"
                                value={doctor.specialization}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Qualification</label>
                            <input
                                type="text"
                                name="qualification"
                                className="form-control"
                                placeholder="e.g. MBBS, MD"
                                value={doctor.qualification}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Experience (Years)</label>
                            <input
                                type="number"
                                name="experienceYears"
                                className="form-control"
                                min="0"
                                value={doctor.experienceYears}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Consultation Fee</label>
                            <input
                                type="number"
                                name="consultationFee"
                                className="form-control"
                                min="0"
                                step="0.01"
                                value={doctor.consultationFee}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Hospital Name</label>
                            <input
                                type="text"
                                name="hospitalName"
                                className="form-control"
                                value={doctor.hospitalName}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-12 mb-3">
                            <label className="form-label">Doctor Bio</label>
                            <textarea
                                name="bio"
                                className="form-control"
                                rows="3"
                                value={doctor.bio}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="col-md-6 mb-3">
                            <label className="form-label">Availability</label>
                            <select
                                name="available"
                                className="form-select"
                                value={String(doctor.available)}
                                onChange={handleChange}
                            >
                                <option value="true">Available</option>
                                <option value="false">Unavailable</option>
                            </select>
                        </div>

                    </div>

                    <button type="submit" className="btn btn-success me-2">
                        Add Doctor
                    </button>

                    <button
                        type="button"
                        className="btn btn-secondary"
                        onClick={() => navigate('/doctors')}
                    >
                        Cancel
                    </button>

                </form>

            </div>

        </div>
    );
}

export default AddDoctor;