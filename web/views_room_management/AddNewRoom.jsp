
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Add Room</title>
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
                <div class="col-md-10 content">

                    <div class="bg-light">

                        <nav class="navbar navbar-expand-lg navbar-dark bg-warning">
                            <div class="container-fluid">
                                <span class="navbar-brand fw-bold">Classroom Management</span>
                            </div>
                        </nav>

                        <div class="container mt-5">

                            <div class="row justify-content-center">

                                <div class="col-md-6">

                                    <div class="card shadow">

                                        <div class="card-header bg-warning text-white fw-bold">
                                            Add New Room
                                        </div>

                                        <div class="card-body">

                                            <form action="Room" method="post">
                                                <input type="hidden" name="action" value="add">

                                                <div class="mb-3">
                                                    <label class="form-label">Room Code</label>

                                                    <input type="text"
                                                           class="form-control"
                                                           name="roomCode"
                                                           placeholder="Example: A301"
                                                           required>

                                                    <c:if test="${not empty error}">
                                                        <div class="text-danger">
                                                            ${error}
                                                        </div>
                                                    </c:if>
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Capacity</label>
                                                    <input type="number" class="form-control" name="capacity" required>
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Building</label>

                                                    <select class="form-select" name="buildingId">

                                                        <c:forEach var="b" items="${locations}">
                                                            <option value="${b.buildingId}">
                                                                ${b.buildingName}
                                                            </option>
                                                        </c:forEach>

                                                    </select>

                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Room Type</label>

                                                    <select class="form-select" name="typeId">

                                                        <c:forEach var="t" items="${types}">
                                                            <option value="${t.typeId}">
                                                                ${t.typeName}
                                                            </option>
                                                        </c:forEach>

                                                    </select>

                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>

                                                    <select class="form-select" name="statusId">

                                                        <c:forEach var="s" items="${statuses}">
                                                            <option value="${s.statusId}">
                                                                ${s.statusName}
                                                            </option>
                                                        </c:forEach>

                                                    </select>

                                                </div>

                                                <div class="d-grid gap-2">

                                                    <button type="submit" class="btn btn-warning text-white fw-bold">
                                                        Add Room
                                                    </button>

                                                    <a href="Room" class="btn btn-secondary">
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




