nối từ viewAssetRoom

<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Edit Asset</title>
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

                            <h3 class="mb-4">Edit Asset</h3>

                            <div class="card shadow">

                                <div class="card-header bg-warning text-white fw-bold">
                                    Update Asset Information
                                </div>

                                <div class="card-body">

                                    <form action="EditAsset" method="post">
                                        <input type="hidden" name="action" value="update"/>
                                        <!-- Hidden Asset ID -->
                                        <input type="hidden" name="assetId" value="${asset.assetId}">

                                        <!-- Asset Code -->
                                        <div class="mb-3">
                                            <label class="form-label">Asset Code</label>
                                            <input type="text" class="form-control" name="assetCode"
                                                   value="${asset.assetCode}"  readonly="">
                                        </div>

                                        <!-- Asset Name -->
                                        <div class="mb-3">
                                            <label class="form-label">Asset Name</label>
                                            <input type="text" class="form-control" name="assetName"
                                                   value="${asset.assetName}" required>
                                        </div>

                                        <!-- Category -->
                                        <div class="mb-3">
                                            <label class="form-label">Category</label>

                                            <input type="text" value="${asset.categoryName}" readonly class="form-control"
                                                   readonly="">

                                        </div>

                                        <!-- Room -->
                                        <div class="mb-3">
                                            <label class="form-label">Room</label>

                                            <select class="form-select" name="roomId">

                                                <option value=""></option>

                                                <c:forEach var="r" items="${rooms}">
                                                    <option value="${r.roomId}"
                                                            <c:if test="${r.roomId == asset.roomId}">selected</c:if>>
                                                        ${r.roomCode}
                                                    </option>
                                                </c:forEach>

                                            </select>

                                        </div>

                                        <!-- Serial Number -->
                                        <div class="mb-3">
                                            <label class="form-label">Serial Number</label>
                                            <input type="text" value="${asset.serialNumber}" readonly class="form-control" readonly="">
                                        </div>

                                        <!-- Purchase Date -->
                                        <div class="mb-3">
                                            <label class="form-label">Purchase Date</label>
                                            <input type="text" value="${asset.purchaseDate}" readonly class="form-control" readonly="">
                                        </div>

                                        <!-- Status -->
                                        <div class="mb-3">
                                            <label class="form-label">Status</label>

                                            <select class="form-select" name="statusId">

                                                <c:forEach var="s" items="${statuses}">
                                                    <option value="${s.statusId}"
                                                            <c:if test="${s.statusId == asset.statusId}">selected</c:if>>
                                                        ${s.statusName}
                                                    </option>
                                                </c:forEach>

                                            </select>

                                        </div>

                                        <div class="d-flex gap-2">

                                            <button class="btn btn-warning text-white fw-bold">
                                                Update Asset
                                            </button>

                                            <a href="Assets?action=view" class="btn btn-secondary">
                                                Cancel
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

        <jsp:include page="/views/footer.jsp"/>

    </body>
</html>


