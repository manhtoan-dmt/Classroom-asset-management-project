
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Issue Reports</title>
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

        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">Issue Reports</span>
            </div>
        </nav>

        <div class="container mt-4">

            <h3 class="mb-4">Issue Reports</h3>

            <!-- FILTER -->

            <div class="card shadow-sm p-3 mb-4">

                <form action="Issue" method="get">
                    <input type="hidden" name="action" value="filter">

                    <div class="row">

                        <div class="col-md-3">
                            <label class="form-label">Date</label>

                            <input type="date"
                                   name="date"
                                   class="form-control"
                                   value="${param.date}">
                        </div>

                        <div class="col-md-3">

                            <label class="form-label">Asset Status</label>

                            <select name="status" class="form-select">

                                <option value="">All</option>

                                <option value="Available"
                                        ${param.status=='Available'?'selected':''}>
                                    Available
                                </option>

                                <option value="In Use"
                                        ${param.status=='In Use'?'selected':''}>
                                    In Use
                                </option>

                                <option value="Maintenance"
                                        ${param.status=='Maintenance'?'selected':''}>
                                    Maintenance
                                </option>

                                <option value="Broken"
                                        ${param.status=='Broken'?'selected':''}>
                                    Broken
                                </option>

                                <option value="Lost"
                                        ${param.status=='Lost'?'selected':''}>
                                    Lost
                                </option>

                                <option value="Disposed"
                                        ${param.status=='Disposed'?'selected':''}>
                                    Disposed
                                </option>

                            </select>

                        </div>

                        <div class="col-md-2 d-flex align-items-end">

                            <button class="btn btn-warning text-white w-100">
                                Filter
                            </button>

                        </div>

                        <div class="col-md-2 d-flex align-items-end">

                            <a href="Issue" class="btn btn-secondary w-100">
                                Reset
                            </a>

                        </div>

                    </div>

                </form>

            </div>

            <!-- TABLE -->

            <div class="card shadow-sm">

                <div class="table-responsive">

                    <table class="table table-bordered table-hover">

                        <thead class="table-warning text-center">

                            <tr>
                                <th>ID</th>
                                <th>Room</th>
                                <th>Asset Name</th>
                                <th>Asset Id</th>
                                <th>Reported By</th>
                                <th>Status</th>
                                <th>Date</th>
                                <th>Action</th>
                            </tr>

                        </thead>

                        <tbody>

                            <c:if test="${empty issues}">
                                <tr>
                                    <td colspan="7" class="text-center text-muted">
                                        No issues found
                                    </td>
                                </tr>
                            </c:if>

                            <c:forEach var="i" items="${issues}">

                                <tr>

                                    <td class="text-center">${i.issueId}</td>

                                    <td>${i.roomCode}</td>

                                    <td>${i.assetName}</td>
                                    <td>${i.assetId}</td>

                                    <td>${i.reportedBy}</td>

                                    <td class="text-center">

                                        <c:choose>

                                            <c:when test="${i.status=='Available'}">
                                                <span class="badge bg-success">Available</span>
                                            </c:when>

                                            <c:when test="${i.status=='In Use'}">
                                                <span class="badge bg-primary">In Use</span>
                                            </c:when>

                                            <c:when test="${i.status=='Maintenance'}">
                                                <span class="badge bg-warning text-dark">Maintenance</span>
                                            </c:when>

                                            <c:when test="${i.status=='Broken'}">
                                                <span class="badge bg-danger">Broken</span>
                                            </c:when>

                                            <c:when test="${i.status=='Lost'}">
                                                <span class="badge bg-dark">Lost</span>
                                            </c:when>

                                            <c:when test="${i.status=='Disposed'}">
                                                <span class="badge bg-secondary">Disposed</span>
                                            </c:when>

                                            <c:otherwise>
                                                <span class="badge bg-info">${i.status}</span>
                                            </c:otherwise>

                                        </c:choose>

                                    </td>

                                    <td>${i.date}</td>

                                    <td class="text-center">

                                        <a href="Issue?action=detail&id=${i.issueId}"
                                           class="btn btn-sm btn-primary">
                                            View
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

    </div>

</div>

<jsp:include page="/views/footer.jsp"/>

</body>
</html>

==

    
