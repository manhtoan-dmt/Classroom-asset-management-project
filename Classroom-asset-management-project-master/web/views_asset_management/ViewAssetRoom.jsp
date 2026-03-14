
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Asset Room Detail</title>
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
                <div class="col-md-10">

                    <div class="bg-light">


                        <!-- NAVBAR -->

                        <nav class="navbar navbar-dark bg-warning">
                            <div class="container-fluid">
                                <span class="navbar-brand fw-bold">Asset Management</span>
                            </div>
                        </nav>



                        <div class="container mt-4">


                            <h4 class="mb-4">Asset List</h4>


                            <!-- FILTER -->

                            <form action="viewAssetType" method="get">

                                <div class="row g-3 mb-4">

                                    <div class="col-md-4">

                                        <label class="form-label">Room</label>

                                        <select name="roomId" class="form-select">

                                            <option value="">ALL</option>

                                            <c:forEach var="r" items="${rooms}">
                                                <option value="${r.roomId}">
                                                    ${r.roomCode}
                                                </option>
                                            </c:forEach>

                                        </select>

                                    </div>



                                    <div class="col-md-4">

                                        <label class="form-label">Asset</label>

                                        <select name="categoryId" class="form-select">

                                            <c:forEach var="c" items="${assetTypes}">
                                                <option value="${c.categoryId}">
                                                    ${c.categoryName}
                                                </option>
                                            </c:forEach>

                                        </select>

                                    </div>



                                    <div class="col-md-4 d-flex align-items-end">

                                        <button class="btn btn-warning text-white w-100">

                                            Search

                                        </button>

                                    </div>


                                </div>

                            </form>



                            <!-- TABLE -->

                            <table class="table table-bordered table-hover bg-white">

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

                                            <td>${a.assetCode}</td>

                                            <td>${a.assetName}</td>

                                            <td>${a.statusName}</td>

                                            <td>

                                                <a class="btn btn-sm btn-info text-white">View</a>

                                                <a class="btn btn-sm btn-primary">Edit</a>

                                                <c:if test="${sessionScope.role == 'ADMIN'}">

                                                    <a class="btn btn-sm btn-danger">Delete</a>

                                                </c:if>

                                            </td>

                                        </tr>

                                    </c:forEach>

                                </tbody>

                            </table>



                            <!-- ADD BUTTON -->

                            <div class="mb-4">

                                <a href="addAsset" class="btn btn-warning text-white fw-bold">

                                    Add Asset

                                </a>

                            </div>



                            <!-- STATUS STATISTICS -->

                            <div class="card shadow mt-4"> 
                                <div class="card-header bg-warning text-white fw-bold"> Asset Status </div> 
                                <div class="card-body"> <div class="row text-center">
                                        <div class="col-md-2">
                                            <h6>Available</h6> 
                                            <p class="fs-4 text-success">${stats.available}</p> 
                                        </div> 
                                        <div class="col-md-2"> 
                                            <h6>Broken</h6> 
                                            <p class="fs-4 text-danger">${stats.broken}</p> 
                                        </div> 
                                        <div class="col-md-2"> 
                                            <h6>Disposed</h6> 
                                            <p class="fs-4 text-secondary">${stats.disposed}</p>
                                        </div> 
                                        <div class="col-md-2"> 
                                            <h6>In Use</h6> 
                                            <p class="fs-4 text-primary">${stats.inuse}</p> 
                                        </div> 
                                        <div class="col-md-2"> 
                                            <h6>Lost</h6> 
                                            <p class="fs-4 text-dark">${stats.lost}</p> 
                                        </div> 
                                        <div class="col-md-2">
                                            <h6>Maintenance</h6> 
                                            <p class="fs-4 text-warning">${stats.maintenance}</p> 
                                        </div> 
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





