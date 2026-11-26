import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Dashboard() {

    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    return (
        <div className="container-fluid">

            <nav className="navbar navbar-dark bg-primary px-4">
                <span className="navbar-brand">
                    Healthcare Management System
                </span>

                <button onClick={handleLogout} className="btn btn-light">
                    Logout
                </button>
            </nav>

            <div className="container mt-5">

                <h2 className="text-primary">
                    Welcome to Healthcare Dashboard
                </h2>

                <p className="text-muted">
                    Manage your healthcare activities here.
                </p>

                <div className="row mt-4">

                    <div className="col-md-4 mb-3">
                        <div className="card shadow p-4">
                            <h4>Patients</h4>
                            <p>Manage patient information.</p>
                            <Link to="/patients" className="btn btn-primary">
                                View Patients
                            </Link>
                        </div>
                    </div>

                    <div className="col-md-4 mb-3">
                      <div className="card shadow p-4">
                        <h4>Doctors</h4>
                        <p>Manage doctor information.</p>

        <Link to="/doctors" className="btn btn-success">
            View Doctors
        </Link>
    </div>

                    </div>

                    <div className="col-md-4 mb-3">
                       <div className="card shadow p-4">
                         <h4>Appointments</h4>
                       <p>Book and manage patient appointments.</p>

                     <Link to="/appointments" className="btn btn-success">
                         View Appointments
                </Link>
        </div>
   </div>

                    <div className="col-md-4 mb-3">
                    <div className="card shadow p-4">
                     <h4>Medical Reports</h4>

                     <p>Manage patient medical reports.</p>

                   <Link to="/medical-reports" className="btn btn-success">
                   View Medical Reports
          </Link>
    </div>
</div>

                    <div className="col-md-4 mb-3">
    <div className="card shadow p-4">
        <h4>Medical History</h4>

        <p>Manage patient medical history.</p>

        <Link to="/medical-history" className="btn btn-success">
            View Medical History
        </Link>
    </div>
</div>

                    <div className="col-md-4 mb-3">

    <div className="card shadow p-4">

        <h4>AI Healthcare Assistant</h4>

        <p>
            Ask healthcare questions and get AI-generated information.
        </p>

        <Link to="/ai-assistant" className="btn btn-primary">
            Open AI Assistant
        </Link>

    </div>

</div>

                </div>
            </div>
        </div>
    );
}

export default Dashboard;