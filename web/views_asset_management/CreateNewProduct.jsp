<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Create Asset</title>
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
                <div class="col-md-10 ">

                    <div class="bg-light">

                        <!-- NAVBAR -->
                        <nav class="navbar navbar-dark bg-warning">
                            <div class="container-fluid">
                                <span class="navbar-brand fw-bold">Asset Management</span>
                            </div>
                        </nav>


                        <div class="container mt-5">

                            <div class="row justify-content-center">

                                <div class="col-lg-7">

                                    <div class="card shadow">

                                        <div class="card-header bg-warning text-white fw-bold">
                                            Create New Asset
                                        </div>

                                        <div class="card-body">

                                            <c:if test="${error != null}">
                                                <div class="alert alert-danger">
                                                    ${error}
                                                </div>
                                            </c:if>

                                            <form action="Create" method="post">

                                                <!-- ASSET CODE -->
                                                <div class="mb-3">
                                                    <label class="form-label">Asset Code</label>
                                                    <input type="text" class="form-control" name="assetCode" required>
                                                </div>


                                                <!-- ASSET NAME -->
                                                <div class="mb-3">
                                                    <label class="form-label">Asset Name</label>
                                                    <input type="text" class="form-control" name="assetName" required>
                                                </div>


                                                <!-- CATEGORY -->
                                                <div class="mb-3">
                                                    <label class="form-label">Category</label>

                                                    <select class="form-select" name="categoryId">

                                                        <c:forEach var="c" items="${categories}">
                                                            <option value="${c.categoryId}">
                                                                ${c.categoryName}
                                                            </option>
                                                        </c:forEach>

                                                    </select>

                                                </div>


                                                <!-- ROOM -->
                                                <div class="mb-3">
                                                    <!--                                                    <label class="form-label">Room</label>
                                                    
                                                                                                        <select class="form-select" name="roomId">
                                                    
                                                    <c:forEach var="r" items="${rooms}">
                                                        <option value="${r.roomId}">
                                                        ${r.roomCode}
                                                    </option>
                                                    </c:forEach>

                                                </select>-->

                                                </div>


                                                <!-- SERIAL NUMBER -->
                                                <div class="mb-3">
                                                    <label class="form-label">Serial Number</label>
                                                    <input type="text" class="form-control" name="serialNumber">
                                                </div>


                                                <!-- PURCHASE DATE -->
                                                <div class="mb-3">
                                                    <label class="form-label">Purchase Date</label>
                                                    <input type="date" class="form-control" name="purchaseDate">
                                                </div>
                                                
                                                <div class="mb-3">
                                                    <label class="form-label">Supplier</label>
                                                    <input type="text" class="form-control" name="Supplier">
                                                </div>
                                                
                                                <div class="mb-3">
                                                    <label class="form-label">Price</label>
                                                    <input type="text" class="form-control" name="Price">
                                                </div>

                                                <!-- STATUS -->
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>

                                                    <input type="text" class="form-control" value="Available" readonly>

                                                </div>


                                                <div class="d-grid gap-2">

                                                    <button class="btn btn-warning text-white fw-bold">
                                                        Create Asset
                                                    </button>

                                                    <a href="Assets" class="btn btn-secondary">
                                                        Back
                                                    </a>

                                                </div>

                                            </form>

                                        </div>

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






