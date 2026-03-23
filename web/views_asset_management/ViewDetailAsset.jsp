<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Asset Detail</title>
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
                                <span class="navbar-brand fw-bold">Asset Management</span>
                            </div>
                        </nav>


                        <div class="container mt-4">

                            <h3 class="mb-4">Asset Detail</h3>


                            <!-- ASSET INFORMATION -->
                            <div class="card shadow mb-4">

                                <div class="card-header bg-warning text-white fw-bold">
                                    Asset Information
                                </div>

                                <div class="card-body">

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Asset Code :</div>
                                        <div class="col-md-9">${asset.assetCode}</div>
                                    </div>

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Asset Name :</div>
                                        <div class="col-md-9">${asset.assetName}</div>
                                    </div>

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Room :</div>
                                        <div class="col-md-9">${asset.roomName}</div>
                                    </div>

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Category :</div>
                                        <div class="col-md-9">${asset.categoryName}</div>
                                    </div>

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Status :</div>
                                        <div class="col-md-9">
                                            <span class="badge bg-success">${asset.statusName}</span>
                                        </div>
                                    </div>

                                </div>

                            </div>



                            <!-- PURCHASE INFORMATION -->

                            <div class="card shadow mb-4">

                                <div class="card-header bg-warning text-white fw-bold">
                                    Purchase Information
                                </div>

                                <div class="card-body">

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Purchase Date :</div>
                                        <div class="col-md-9">${asset.purchaseDate}</div>
                                    </div>

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Supplier :</div>
                                        <div class="col-md-9">${asset.supplier}</div>
                                    </div>

                                    <div class="row mb-2">
                                        <div class="col-md-3 fw-bold">Price :</div>
                                        <div class="col-md-9">${asset.price} USD</div>
                                    </div>


                                </div>

                            </div>



                            <!-- MAINTENANCE HISTORY -->

                            <div class="card shadow">

                                <div class="card-header bg-warning text-white fw-bold">
                                    Maintenance History
                                </div>

                                <div class="card-body">

                                    <table class="table table-bordered table-hover">

                                        <thead class="table-warning">

                                            <tr>
                                                <th>Date</th>
                                                <th>Problem</th>
                                                <th>Status</th>
                                                <th>Reported By</th>
                                                <th>Technician</th>
                                                <th>Completed</th>


                                            </tr>

                                        </thead>

                                        <tbody>

                                            <c:forEach var="m" items="${maintenanceList}">

                                                <tr>
                                                    <td>${m.date}</td>
                                                    <td>${m.problem}</td>
                                                    <td>${m.status}</td>
                                                    <td>${m.reportedBy}</td>
                                                    <td>${m.technician}</td>
                                                    <td>${m.completedAt}</td>
                                                </tr>

                                            </c:forEach>

                                        </tbody>

                                    </table>

                                </div>

                            </div>



                            <!-- BACK BUTTON -->

                            <div class="mt-4">
                                <a href="Asset" class="btn btn-secondary">Back</a>
                            </div>


                        </div>

                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="/views/footer.jsp"/>

    </body>
</html>





