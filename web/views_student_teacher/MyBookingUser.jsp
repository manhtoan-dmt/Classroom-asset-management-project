<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>My Bookings</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        
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
            .table td, .table th {
                vertical-align: middle;
            }
            .badge {
                font-weight: 500;
                padding: 0.5em 0.8em;
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
                            
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2 class="m-0">My Bookings</h2>
                                <a href="roomuser" class="btn btn-primary">
                                    + Book New Room
                                </a>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-bordered table-hover">
                                    <thead class="table-dark">
                                        <tr>
                                            <th>Room Name</th>
                                            <th class="text-center">Date</th>
                                            <th class="text-center">Time Slot</th>
                                            <th class="text-center">Status</th>
                                            <th class="text-center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="b" items="${booking}">
                                            <tr>
                                                <td class="fw-bold text-primary">${b.roomName}</td>
                                                <td class="text-center">${b.date}</td>
                                                <td class="text-center">${b.timeSlot}</td>

                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${b.status == 'Pending'}">
                                                            <span class="badge bg-warning text-dark">Pending</span>
                                                        </c:when>
                                                        <c:when test="${b.status == 'Approved'}">
                                                            <span class="badge bg-success">Approved</span>
                                                        </c:when>
                                                        <c:when test="${b.status == 'Cancelled'}">
                                                            <span class="badge bg-danger">Cancelled</span>
                                                        </c:when>
                                                        <c:when test="${b.status == 'Rejected'}">
                                                            <span class="badge bg-danger">Rejected</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge bg-dark">Finished</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${b.status == 'Cancelled' || b.status == 'Rejected'}">
                                                            <span class="text-muted small italic">No action</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="mybooking?action=cancel&id=${b.bookingId}"
                                                               class="btn btn-outline-danger btn-sm"
                                                               onclick="return confirm('Are you sure you want to cancel this booking?')">
                                                                Cancel Booking
                                                            </a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <c:if test="${empty booking}">
                                            <tr>
                                                <td colspan="5" class="text-center py-4 text-muted">
                                                    You haven't made any bookings yet.
                                                </td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div> <div class="mt-4">
                                <a href="roomuser" class="btn btn-secondary btn-sm">
                                    ← Back to Room Management
                                </a>
                            </div>

                        </div> </div> </div> </div>
        </div>

        <jsp:include page="/views/footer.jsp"/>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>