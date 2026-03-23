<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create Booking | Classroom Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f4f7f6;
        }
        .booking-container {
            max-width: 600px;
            margin: 50px auto;
        }
        .card {
            border: none;
            border-radius: 15px;
        }
        .card-header {
            border-radius: 15px 15px 0 0 !important;
            padding: 20px;
            text-align: center;
        }
        .form-label {
            font-weight: 600;
            color: #444;
        }
        .form-control, .form-select {
            border-radius: 8px;
            padding: 10px 15px;
            border: 1px solid #ddd;
        }
        .form-control:focus, .form-select:focus {
            box-shadow: 0 0 0 0.25 margin rgba(255, 193, 7, 0.25);
            border-color: #ffc107;
        }
        .btn-create {
            background-color: #ffc107;
            border: none;
            color: #000;
            font-weight: 700;
            padding: 12px;
            transition: all 0.3s;
        }
        .btn-create:hover {
            background-color: #e0a800;
            transform: translateY(-2px);
        }
        .error-message {
            margin-top: 15px;
            border-radius: 8px;
        }
        .readonly-input {
            background-color: #f9f9f9 !important;
            font-weight: bold;
            color: #0072BC;
        }
    </style>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-dark shadow-sm" style="background-color: #0072BC;">
        <div class="container">
            <a class="navbar-brand fw-bold" href="roomuser">Classroom System</a>
        </div>
    </nav>

    <div class="container">
        <div class="booking-container">
            
            <div class="text-center mb-4">
                <h3 class="fw-bold text-dark">Create New Booking</h3>
                <p class="text-muted">Please fill in the details to reserve your room.</p>
            </div>

            <div class="card shadow-lg">
                <div class="card-header bg-warning">
                    <h5 class="mb-0 text-dark fw-bold">Booking Information</h5>
                </div>

                <div class="card-body p-4">
                    <form action="book" method="post">
                        <input type="hidden" name="action" value="create">

                        <div class="mb-3">
                            <label class="form-label">Selected Room</label>
                            <input type="hidden" name="roomId" value="${room.roomId}">
                            <input type="text" 
                                   class="form-control readonly-input" 
                                   value="Room: ${room.roomCode}" 
                                   readonly>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Booking Date</label>
                                <input type="date" 
                                       name="date" 
                                       class="form-control" 
                                       required>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label class="form-label">Time Slot</label>
                                <select name="timeSlot" class="form-select" required>
                                    <option value="" selected disabled>Select slot...</option>
                                    <option value="07-09">07:00 - 09:00</option>
                                    <option value="09-11">09:00 - 11:00</option>
                                    <option value="13-15">13:00 - 15:00</option>
                                    <option value="15-17">15:00 - 17:00</option>
                                </select>
                            </div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Purpose of Use</label>
                            <textarea name="purpose" 
                                      class="form-control" 
                                      rows="3" 
                                      placeholder="Explain why you need this room..."></textarea>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-create shadow-sm">
                                Confirm & Create Booking
                            </button>
                            <a href="roomuser" class="btn btn-outline-secondary">
                                Cancel
                            </a>
                        </div>

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger error-message d-flex align-items-center mt-3" role="alert">
                                <div>
                                    <i class="bi bi-exclamation-triangle-fill me-2"></i> ${error}
                                </div>
                            </div>
                        </c:if>

                    </form>
                </div>
            </div>
            
            <p class="text-center mt-4 text-muted small">
                &copy; 2026 Room Management System. All rights reserved.
            </p>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>