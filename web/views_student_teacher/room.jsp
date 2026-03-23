<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Room Management</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        
        <style>
            .main-wrapper {
                min-height: calc(100vh - 120px); 
            }
            .sidebar-area {
                background-color: #f8f9fa;
                border-right: 1px solid #dee2e6;
            }
            .content-area {
                padding: 20px;
            }
            .table td, .table th {
                vertical-align: middle;
            }
        </style>
    </head>
    <body>

        <jsp:include page="/views/header.jsp"/>

        <div class="container-fluid">
            <div class="row main-wrapper">

                <div class="col-md-2 p-0 sidebar-area">
                    <jsp:include page="/views_student_teacher/topnav.jsp"/>
                </div>

                <div class="col-md-10 content-area">

                    <div class="card shadow-sm">
                        <div class="card-body">
                            
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2 class="m-0">Room Management</h2>
                                <div>
                                    <a href="mybooking" class="btn btn-success">
                                        <i class="bi bi-calendar-check"></i> My bookings
                                    </a>
                                </div>
                            </div>

                            <form action="${pageContext.request.contextPath}/roomuser" method="get" class="mb-4">
                                <div class="row g-2"> <div class="col-md-4">
                                        <div class="input-group">
                                            <input type="text"
                                                   name="keyword"
                                                   class="form-control"
                                                   placeholder="Search by room name or code..."
                                                   value="${param.keyword}">
                                            <button class="btn btn-primary" type="submit">Search</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="table-responsive">
                                <table class="table table-bordered table-hover">
                                    <thead class="table-dark">
                                        <tr>
                                            <th class="text-center">Room ID</th>
                                            <th>Name</th>
                                            <th>Type</th>
                                            <th>Building</th>
                                            <th class="text-center">Capacity</th>
                                            <th class="text-center">Status</th>
                                            <th class="text-center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="r" items="${rooms}">
                                            <tr>
                                                <td class="text-center">${r.roomID}</td>
                                                <td class="fw-bold">${r.name}</td>
                                                <td><span class="badge bg-secondary">${r.type}</span></td>
                                                <td>${r.building}</td>
                                                <td class="text-center">${r.capacity}</td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${r.status == 'Available'}">
                                                            <span class="text-success fw-bold">${r.status}</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="text-danger fw-bold">${r.status}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="text-center">
                                                    <div class="btn-group" role="group">
                                                        <a href="roomuser?action=view&id=${r.roomID}"
                                                           class="btn btn-info btn-sm">View</a>
                                                        <a href="${pageContext.request.contextPath}/book?roomId=${r.roomID}"
                                                           class="btn btn-warning btn-sm">Book</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty rooms}">
                                            <tr>
                                                <td colspan="7" class="text-center text-muted">No rooms found.</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div> </div> </div> </div> </div>
        </div>

        <jsp:include page="/views/footer.jsp"/>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>