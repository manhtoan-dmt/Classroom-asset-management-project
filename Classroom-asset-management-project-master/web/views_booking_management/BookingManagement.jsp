<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Manage Bookings</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

    </head>
    <body>

<jsp:include page="/views/header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-2 p-0">
            <jsp:include page="/views/topnav.jsp"/>
        </div>

        <!-- CONTENT -->
        <div class="col-md-10">

            <div class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">Room Booking Management</span>
            </div>
        </nav>


        <div class="container mt-4">

            <h3 class="mb-4">Manage Bookings</h3>


            <!-- FILTER DATE -->

            <form action="Book" method="get" class="mb-3">

                <input type="hidden" name="action" value="manage">

                <div class="row">

                    <div class="col-md-3">

                        <label class="form-label">Filter by Date</label>

                        <input type="date"
                               name="date"
                               class="form-control"
                               value="${today}">

                        
                    </div>

                    <div class="col-md-2 d-flex align-items-end">

                        <button class="btn btn-warning text-white w-100">
                            Search
                        </button>

                    </div>

                    <div class="col-md-3 d-flex align-items-end">

                        <a href="Booking?action=create"
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

                            <td>${b.getRoomName()}</td>
                            <td>${b.getUserName()}</td>
                            <td>${b.getDate()}</td>
                            <td>${b.getTimeSlot()}</td>

                            <td>

                                <c:choose>

                                    <c:when test="${b.getStatus()=='Pending'}">
                                        <span class="badge bg-secondary">Pending</span>
                                    </c:when>

                                    <c:when test="${b.getStatus()=='Approved'}">
                                        <span class="badge bg-success">Approved</span>
                                    </c:when>

                                    <c:when test="${b.getStatus()=='Cancelled'}">
                                        <span class="badge bg-danger">Cancelled</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-dark">Unknown</span>
                                    </c:otherwise>
                                </c:choose>

                            </td>

                            <td>

                                <a href="Booking?action=detail&id=${b.getBookingId()}"
                                   class="btn btn-primary btn-sm">

                                    Detail

                                </a>



                            </td>

                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </div>

    </div>

        </div>

    </div>

</div>

<jsp:include page="/views/footer.jsp"/>

</body>
</html>





    

