
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Booking Detail</title>
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

                            <c:when test="${b.status=='Rejected'}">
                                <span class="badge bg-dark">Rejected</span>
                            </c:when>

                            <c:when test="${b.status=='Cancelled'}">
                                <span class="badge bg-danger">Cancelled</span>
                            </c:when>

                            <c:when test="${b.status=='Finished'}">
                                <span class="badge bg-primary">Finished</span>
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


                    <!-- PURPOSE -->
                    <h5 class="text-warning mt-4">Purpose</h5>
                    <hr>

                    <p>${b.purpose}</p>


                    <!-- ACTION BUTTONS -->

                    <div class="mt-4 d-flex gap-2 flex-wrap">

                            <a href="Book?action=updateStatus&id=${b.bookingId}&status=1"
                               class="btn btn-secondary">
                                Pending
                            </a>

                            <a href="Book?action=updateStatus&id=${b.bookingId}&status=2"
                               class="btn btn-success">
                                Approve
                            </a>

                            <a href="Book?action=updateStatus&id=${b.bookingId}&status=3"
                               class="btn btn-dark">
                                Reject
                            </a>

                            <a href="Book?action=updateStatus&id=${b.bookingId}&status=4"
                               class="btn btn-danger">
                                Cancel
                            </a>

                            <a href="Book?action=updateStatus&id=${b.bookingId}&status=5"
                               class="btn btn-primary">
                                Finish
                            </a>

                            <a href="Book?action=manage"
                               class="btn btn-outline-secondary">
                                Back
                            </a>

                        </div>

                </div>

            </div>

        </div>

    </div>
        </div>

    </div>

</div>

<jsp:include page="/views/footer.jsp"/>

</body>
</html>

    


