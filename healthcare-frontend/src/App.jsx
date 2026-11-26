import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';

import PatientList from './pages/PatientList';
import AddPatient from './pages/AddPatient';
import EditPatient from './pages/EditPatient';

import DoctorList from './pages/DoctorList';
import AddDoctor from './pages/AddDoctor';
import EditDoctor from './pages/EditDoctor';

import AppointmentList from './pages/AppointmentList';
import AddAppointment from './pages/AddAppointment';
import EditAppointment from './pages/EditAppointment';

import MedicalReportList from './pages/MedicalReportList';
import AddMedicalReport from './pages/AddMedicalReport';
import EditMedicalReport from './pages/EditMedicalReport';

import MedicalHistoryList from './pages/MedicalHistoryList';
import AddMedicalHistory from './pages/AddMedicalHistory';
import EditMedicalHistory from './pages/EditMedicalHistory';

import AIAssistant from './pages/AIAssistant';

import ProtectedRoute from './components/ProtectedRoute';

function App() {

    return (
        <BrowserRouter>

            <Routes>

                <Route path="/" element={<Navigate to="/login" replace />} />

                <Route path="/login" element={<Login />} />

                <Route path="/register" element={<Register />} />

                <Route
                    path="/dashboard"
                    element={
                        <ProtectedRoute>
                            <Dashboard />
                        </ProtectedRoute>
                    }
                />

                {/* Patient Routes */}

                <Route
                    path="/patients"
                    element={
                        <ProtectedRoute>
                            <PatientList />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/patients/add"
                    element={
                        <ProtectedRoute>
                            <AddPatient />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/patients/edit/:patientId"
                    element={
                        <ProtectedRoute>
                            <EditPatient />
                        </ProtectedRoute>
                    }
                />

                {/* Doctor Routes */}

                <Route
                    path="/doctors"
                    element={
                        <ProtectedRoute>
                            <DoctorList />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/doctors/add"
                    element={
                        <ProtectedRoute>
                            <AddDoctor />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/doctors/edit/:doctorId"
                    element={
                        <ProtectedRoute>
                            <EditDoctor />
                        </ProtectedRoute>
                    }
                />
                <Route
    path="/appointments"
    element={
        <ProtectedRoute>
            <AppointmentList />
        </ProtectedRoute>
    }
/>

<Route
    path="/appointments/add"
    element={
        <ProtectedRoute>
            <AddAppointment />
        </ProtectedRoute>
    }
/>

<Route
    path="/appointments/edit/:appointmentId"
    element={
        <ProtectedRoute>
            <EditAppointment />
        </ProtectedRoute>
    }
/>

{/* Medical Reports Routes */}

<Route
    path="/medical-reports"
    element={
        <ProtectedRoute>
            <MedicalReportList />
        </ProtectedRoute>
    }
/>

<Route
    path="/medical-reports/add"
    element={
        <ProtectedRoute>
            <AddMedicalReport />
        </ProtectedRoute>
    }
/>

<Route
    path="/medical-reports/edit/:reportId"
    element={
        <ProtectedRoute>
            <EditMedicalReport />
        </ProtectedRoute>
    }
/>

{/* Medical History Routes */}

<Route
    path="/medical-history"
    element={
        <ProtectedRoute>
            <MedicalHistoryList />
        </ProtectedRoute>
    }
/>

<Route
    path="/medical-history/add"
    element={
        <ProtectedRoute>
            <AddMedicalHistory />
        </ProtectedRoute>
    }
/>

<Route
    path="/medical-history/edit/:historyId"
    element={
        <ProtectedRoute>
            <EditMedicalHistory />
        </ProtectedRoute>
    }
/>

<Route
    path="/ai-assistant"
    element={
        <ProtectedRoute>
            <AIAssistant />
        </ProtectedRoute>
    }
/>

            </Routes>

        </BrowserRouter>
    );
}

export default App;