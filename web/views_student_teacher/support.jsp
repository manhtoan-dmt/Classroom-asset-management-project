<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Room Issue</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <style>
            .main-wrapper { min-height: calc(100vh - 120px); }
            .sidebar-area { background-color: #f8f9fa; border-right: 1px solid #dee2e6; }
            .content-area { padding: 25px; }
            .card-header.bg-dark { letter-spacing: 1px; }
            .table-v-middle td { vertical-align: middle; }
            .alert { border-radius: 8px; margin-bottom: 20px; }
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

                    <c:if test="${not empty successMsg}">
                        <div class="alert alert-success alert-dismissible fade show shadow-sm" role="alert">
                            <i class="bi bi-check-circle-fill me-2"></i>
                            <strong>Thành công!</strong> ${successMsg}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <c:if test="${not empty errorMsg}">
                        <div class="alert alert-danger alert-dismissible fade show shadow-sm" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            <strong>Lỗi!</strong> ${errorMsg}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <div class="card shadow-sm mb-5">
                        <div class="card-header bg-dark text-white p-3">
                            <h5 class="mb-0"><i class="bi bi-exclamation-octagon me-2"></i> REPORT ROOM ISSUE</h5>
                        </div>
                        <div class="card-body p-4">
                            
                            <form action="support" method="get" class="row mb-3">
                                <div class="col-md-4">
                                    <label class="form-label fw-bold">Select Room:</label>
                                    <select name="roomId" class="form-select" onchange="this.form.submit()" required>
                                        <option value="">-- Choose Room --</option>
                                        <c:forEach var="r" items="${roomList}">
                                            <option value="${r.roomId}" ${r.roomId == selectedRoomId ? 'selected' : ''}>
                                                ${r.roomCode}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-8 d-flex align-items-end">
                                    <small class="text-muted mb-2"></small>
                                </div>
                            </form>

                            <hr>

                    
                            <form action="${pageContext.request.contextPath}/support" method="post">
                                <%-- Giữ roomId đã chọn từ bước trước --%>
                                <input type="hidden" name="roomId" value="${selectedRoomId}">

                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="form-label fw-bold">Asset Type:</label>
                                        <select name="assetId" class="form-select" required ${empty assetList ? 'disabled' : ''}>
                                            <option value="">-- Choose Asset --</option>
                                            <c:forEach var="a" items="${assetList}">
                                                <option value="${a.assetId}">
                                                    ${a.assetName} [${a.assetCode}]
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-6">
                                        <label class="form-label fw-bold">Description of Issue:</label>
                                        <textarea name="description" class="form-control" rows="2" 
                                                  placeholder="E.g. Projector not working..." required></textarea>
                                    </div>
                                </div>

                                <div class="d-flex justify-content-end gap-2">
                                    
                                    <button type="submit" class="btn btn-dark px-5 fw-bold" ${empty assetList ? 'disabled' : ''}>
                                        Submit Report
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>

                   
                    <div class="card shadow-sm">
                        <div class="card-header bg-secondary text-white">
                            <h6 class="mb-0"><i class="bi bi-clock-history me-2"></i> My Reporting History</h6>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-hover mb-0 table-v-middle">
                                <thead class="table-light">
                                    <tr>
                                        <th>Room</th>
                                        <th>Asset</th>
                                        <th>Description</th>
                                        <th class="text-center">Date Created</th>
                                        <th class="text-center">Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="req" items="${requestHistory}">
                                        <tr>
                                            <td class="fw-bold">${req.roomName}</td>
                                            <td>${req.assetName}</td>
                                            <td style="max-width: 300px;" class="text-truncate">${req.description}</td>
                                            <td class="text-center small">${req.createdAt}</td>
                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${req.status == 'COMPLETED'}">
                                                        <span class="badge bg-success">Completed</span>
                                                    </c:when>
                                                    <c:when test="${req.status == 'IN_PROGRESS'}">
                                                        <span class="badge bg-info text-dark">In Progress</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-warning text-dark">${req.status}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty requestHistory}">
                                        <tr>
                                            <td colspan="5" class="text-center py-4 text-muted">No reports found.</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <jsp:include page="/views/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>