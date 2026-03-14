<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Booking Detail</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">Booking Detail</span>
            </div>
        </nav>

        <div class="container mt-4">

            <div class="card shadow">

                <div class="card-header bg-warning text-white">
                    <h4 class="mb-0">Booking Detail</h4>
                </div>

                <div class="card-body">

                    <!-- BOOKING INFO -->
                    <h5 class="text-warning">Booking Information</h5>
                    <hr>

                    <p><strong>Booking ID:</strong> ${b.bookingId}</p>

                    <p>
                        <strong>Status:</strong>

                    <c:choose>
                        <c:when test="${b.status=='Pending'}">
                            <span class="badge bg-secondary">Pending</span>
                        </c:when>

                        <c:when test="${b.status=='Approved'}">
                            <span class="badge bg-success">Approved</span>
                        </c:when>

                        <c:when test="${b.status=='Cancelled'}">
                            <span class="badge bg-danger">Cancelled</span>
                        </c:when>
                    </c:choose>

                    </p>


                    <!-- USER INFO -->
                    <h5 class="text-warning mt-4">User Information</h5>
                    <hr>

                    <p><strong>Name:</strong> ${b.userName}</p>
                    <p><strong>Email:</strong> ${b.email}</p>


                    <!-- ROOM INFO -->
                    <h5 class="text-warning mt-4">Room Information</h5>
                    <hr>

                    <p><strong>Room:</strong> ${b.roomName}</p>


                    <!-- TIME -->
                    <h5 class="text-warning mt-4">Booking Time</h5>
                    <hr>

                    <p><strong>Created At:</strong> ${b.createdAt}</p>
                    <p><strong>Date:</strong> ${b.date}</p>


                    <!-- PURPOSE -->
                    <h5 class="text-warning mt-4">Purpose</h5>
                    <hr>

                    <p>${b.purpose}</p>


                    <!-- ACTION BUTTONS -->

                    <div class="mt-4">

                        <a href="Booking?action=approve&id=${b.bookingId}"
                           class="btn btn-success">

                            Approve

                        </a>

                        <a href="Booking?action=cancel&id=${b.bookingId}"
                           class="btn btn-danger">

                            Cancel

                        </a>

                        <a href="Booking?action=manage"
                           class="btn btn-secondary">

                            Back

                        </a>

                    </div>

                </div>

            </div>

        </div>

    </body>
</html>

