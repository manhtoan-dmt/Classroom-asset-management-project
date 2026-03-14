<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Room Detail</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

    </head>
    <body>

        <jsp:include page="header.jsp"/>

        <div class="container-fluid">

            <div class="row">

                <!-- SIDEBAR -->
                <div class="col-md-2 p-0">
                    <jsp:include page="topnav.jsp"/>
                </div>

                <!-- CONTENT -->
                <div class="col-md-10 content">

                    <div class="container mt-4">

                        <h2>Room Detail</h2>

                        <!-- ROOM INFO -->

                        <div class="card mb-4">

                            <div class="card-header bg-warning">
                                Room Information
                            </div>

                            <div class="card-body">

                                <p><b>Room ID:</b> ${room.roomID}</p>
                                <p><b>Room Name:</b> ${room.name}</p>
                                <p><b>Type:</b> ${room.type}</p>
                                <p><b>Capacity:</b> ${room.capacity} people</p>
                                <p><b>Location:</b> ${room.building}</p>
                                <p><b>Status:</b> ${room.status}</p>
                                <p><b>Description:</b> ${room.description}</p>

                            </div>

                        </div>



                        <!-- ASSET LIST -->

                        <h4>Assets in this Room</h4>

                        <table class="table table-bordered">

                            <thead class="table-warning">

                                <tr>
                                    <th>Asset ID</th>
                                    <th>Asset Name</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>

                            </thead>

                            <tbody>

                                <c:forEach var="a" items="${assets}">

                                    <tr>

                                        <td>${a.assetID}</td>
                                        <td>${a.assetName}</td>
                                        <td>${a.status}</td>

                                        <td>

                                            <a href="Assets?action=view&id=${a.assetID}"
                                               class="btn btn-primary btn-sm">View</a>

                                            <c:if test="${a.status == 'Broken'}">

                                                <a href="Issue?action=repair&id=${a.assetID}"
                                                   class="btn btn-warning btn-sm">Repair</a>
                                                Repair
                                                </a>

                                            </c:if>

                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>



                        <!-- ROOM SCHEDULE -->

                        <h4>Room Schedule Today</h4>

                        <table class="table table-striped table-hover table-bordered">

                            <thead class="table-warning">

                                <tr>
                                    <th>Time</th>
                                    <th>User</th>
                                    <th>Purpose</th>
                                    <th>Status</th>
                                </tr>

                            </thead>

                            <tbody>

                                <c:forEach var="b" items="${bookings}">

                                    <tr>

                                        <td>${b.startTime} - ${b.endTime}</td>
                                        <td>${b.userName}</td>
                                        <td>${b.purpose}</td>
                                        <td>${b.status}</td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>


                        <a href="Room" class="btn btn-secondary mt-3">Back</a>

                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>




