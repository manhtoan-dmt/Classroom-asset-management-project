
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    String today = LocalDate.now().toString();
%>

<!DOCTYPE html>
<html>

    <head>

        <title>Manage Bookings</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">Room Booking Management</span>
            </div>
        </nav>


        <div class="container mt-4">

            <h3 class="mb-4">Manage Bookings</h3>


            <!-- FILTER DATE -->

            <form action="Booking" method="get" class="mb-3">

                <input type="hidden" name="action" value="manage">

                <div class="row">

                    <div class="col-md-3">

                        <label class="form-label">Filter by Date</label>

                        <input type="date"
                               name="date"
                               class="form-control"
                               value="<%= today %>">

                    </div>

                    <div class="col-md-2 d-flex align-items-end">

                        <button class="btn btn-warning text-white w-100">
                            Search
                        </button>

                    </div>

                    <div class="col-md-3 d-flex align-items-end">

                        <a href="createBooking.jsp"
                           class="btn btn-success w-100">

                            Create New Booking

                        </a>

                    </div>

                </div>

            </form>



            <!-- BOOKING TABLE -->

            <table class="table table-bordered table-hover bg-white shadow">

                <thead class="table-warning">

                    <tr>

                        <th>Room</th>
                        <th>User</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Detail</th>

                    </tr>

                </thead>

                <tbody>

                    <c:forEach var="b" items="${bookings}">

                        <tr>

                            <td>${b.roomName}</td>
                            <td>${b.userName}</td>
                            <td>${b.date}</td>
                            <td>${b.timeSlot}</td>

                            <td>

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

                            </td>

                            <td>

                                <a href="Booking?action=detail&id=${b.bookingId}"
                                   class="btn btn-primary btn-sm">

                                    Detail

                                </a>

                                <a href="Booking?action=history&id=${b.bookingId}"
                                   class="btn btn-secondary btn-sm">

                                    History

                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </div>

    </body>

</html>
