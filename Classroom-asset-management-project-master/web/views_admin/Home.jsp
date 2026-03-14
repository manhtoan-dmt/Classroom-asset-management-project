<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Home</title>
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

                        <h2>Home Dashboard</h2>

                        <!-- Filter Date -->
                        <form action="Home" method="get" class="mb-4">
                            <div class="row">

                                <div class="col-md-3">
                                    <label>Date</label>
                                    <input type="date" name="bookingDate"
                                           value="<%= java.time.LocalDate.now()%>" required>
                                </div>

                                <div class="col-md-2 align-self-end">
                                    <button class="btn btn-primary">Search</button>
                                </div>

                            </div>
                        </form>


                        <!-- ROOM STATISTICS -->
                        <h4>ROOM STATISTICS</h4>
                        <div class="row mb-4">

                            <div class="col-md-3">
                                <div class="card text-center bg-light">
                                    <div class="card-body">
                                        <h6>Total Rooms: </h6>
                                        <h3>${totalRooms}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center bg-success text-white">
                                    <div class="card-body">
                                        <h6>Available</h6>
                                        <h3>${availableRooms}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center bg-warning">
                                    <div class="card-body">
                                        <h6>Maintenance</h6>
                                        <h3>${maintenanceRooms}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center bg-danger text-white">
                                    <div class="card-body">
                                        <h6>Occupied</h6>
                                        <h3>${occupiedRooms}</h3>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <!-- ASSET STATISTICS -->
                        <h4>ASSET STATISTICS</h4>
                        <div class="row mb-4">

                            <div class="col-md-2">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Total</h6>
                                        <h4>${totalAssets}</h4>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Available</h6>
                                        <h4>${availableAssets}</h4>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Broken</h6>
                                        <h4>${brokenAssets}</h4>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Disposed</h6>
                                        <h4>${disposedAssets}</h4>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>In Use</h6>
                                        <h4>${inUseAssets}</h4>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Lost</h6>
                                        <h4>${lostAssets}</h4>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <!-- ISSUE REPORT -->
                        <h4>ISSUE REPORTS</h4>
                        <div class="row mb-4">

                            <div class="col-md-3">
                                <div class="card text-center bg-warning">
                                    <div class="card-body">
                                        <h6>Open</h6>
                                        <h3>${openIssues}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center bg-info">
                                    <div class="card-body">
                                        <h6>In Progress</h6>
                                        <h3>${progressIssues}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center bg-success text-white">
                                    <div class="card-body">
                                        <h6>Completed</h6>
                                        <h3>${completedIssues}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center bg-secondary text-white">
                                    <div class="card-body">
                                        <h6>Cancelled</h6>
                                        <h3>${cancelledIssues}</h3>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <!-- USER STATISTICS -->
                        <h4>USER STATISTICS</h4>
                        <div class="row mb-4">

                            <div class="col-md-3">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Total Users</h6>
                                        <h3>${totalUsers}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Students</h6>
                                        <h3>${students}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Room Managers</h6>
                                        <h3>${roomManagers}</h3>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="card text-center">
                                    <div class="card-body">
                                        <h6>Admins</h6>
                                        <h3>${admins}</h3>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>





