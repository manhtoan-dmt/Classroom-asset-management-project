<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Dashboard</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

        <style>
            .main-wrapper {
                min-height: calc(100vh - 120px); 
            }
            .sidebar-area {
                background-color: #f8f9fa;
                border-right: 1px solid #dee2e6;
            }
            .content-area {
                padding: 25px;
            }
            .stat-card {
                height: 100%;
                transition: transform 0.2s;
            }
            .stat-card:hover {
                transform: translateY(-5px);
            }
        </style>
    </head>

    <body>

        <jsp:include page="/views/header.jsp"/>

        <div class="container-fluid">
            <div class="row main-wrapper">

                <div class="col-md-2 p-0 sidebar-area">
                    <jsp:include page="/views_student_teacher/topnav.jsp"/>
                </div>

                <div class="col-md-10 content-area">
                    
                    <div class="card shadow-sm">
                        <div class="card-body">
                            
                            <h2 class="mb-4">Home Dashboard</h2>

                            <h5 class="text-secondary mb-3 text-uppercase fw-bold">Room Statistics</h5>
                            <div class="row g-3 mb-5">

                                <div class="col-md-3">
                                    <div class="card stat-card border-0 bg-light shadow-sm text-center">
                                        <div class="card-body">
                                            <h6 class="text-muted">Total Rooms</h6>
                                            <h2 class="fw-bold">${totalRooms}</h2>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="card stat-card border-0 bg-success text-white shadow-sm text-center">
                                        <div class="card-body">
                                            <h6 class="opacity-75">Available</h6>
                                            <h2 class="fw-bold">${stats.available}</h2>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="card stat-card border-0 bg-warning text-dark shadow-sm text-center">
                                        <div class="card-body">
                                            <h6 class="opacity-75">Maintenance</h6>
                                            <h2 class="fw-bold">${stats.maintenance}</h2>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="card stat-card border-0 bg-danger text-white shadow-sm text-center">
                                        <div class="card-body">
                                            <h6 class="opacity-75">Occupied</h6>
                                            <h2 class="fw-bold">${stats.occupied}</h2>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h5 class="text-secondary text-uppercase fw-bold m-0">My Current Booking</h5>
                                <a href="roomuser" class="btn btn-outline-primary btn-sm">Book New Room</a>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-hover align-middle border">
                                    <thead class="table-dark">
                                        <tr>
                                            <th>Room</th>
                                            <th class="text-center">Date</th>
                                            <th class="text-center">Time Slot</th>
                                            <th class="text-center">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="b" items="${booking}">
                                            <tr>
                                                <td class="fw-bold">${b.roomName}</td>
                                                <td class="text-center">${b.date}</td>
                                                <td class="text-center text-primary">${b.timeSlot}</td>
                                                <td class="text-center">
                                                    <span class="badge rounded-pill 
                                                        ${b.status == 'Approved' ? 'bg-success' : (b.status == 'Pending' ? 'bg-warning text-dark' : 'bg-secondary')}">
                                                        ${b.status}
                                                    </span>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <c:if test="${empty booking}">
                                            <tr>
                                                <td colspan="4" class="text-center py-4 text-muted">
                                                    <i>No current bookings found.</i>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>

                        </div> </div> </div> </div>
        </div>

        <jsp:include page="/views/footer.jsp"/>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>