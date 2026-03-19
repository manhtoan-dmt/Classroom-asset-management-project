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
            .main-wrapper {
                min-height: calc(100vh - 120px);
            }
            .sidebar-area {
                background-color: #f8f9fa;
                border-right: 1px solid #dee2e6;
            }
            .content-area {
                padding: 25px;
            }
            .card-header.bg-dark {
                letter-spacing: 1px;
            }
            .table-v-middle td {
                vertical-align: middle;
            }
            .alert {
                border-radius: 8px;
                margin-bottom: 20px;
            }
        </style>

        <script>
    // Hàm 1: Lọc danh sách thiết bị theo phòng
    function onRoomChange() {
        const selectedRoomId = document.getElementById("roomSelect").value;
        const assetSelect = document.getElementById("assetSelect");
        const options = assetSelect.querySelectorAll(".asset-option");

        // Reset lại ô Asset và ô Code hiển thị
        assetSelect.value = "";
        document.getElementById("assetCodeDisplay").innerText = "---";

        options.forEach(opt => {
            const roomOfAsset = opt.getAttribute("data-room");
            // Hiển thị option nếu thuộc phòng đã chọn, ngược lại thì ẩn
            if (roomOfAsset === selectedRoomId || selectedRoomId === "") {
                opt.style.display = "block";
                opt.disabled = false;
            } else {
                opt.style.display = "none";
                opt.disabled = true; 
            }
        });
    }

    // Hàm 2: Cập nhật Asset Code khi chọn thiết bị (CÁI BẠN ĐANG THIẾU)
    function onAssetChange() {
        const assetSelect = document.getElementById("assetSelect");
        const selectedOption = assetSelect.options[assetSelect.selectedIndex];
        
        // Lấy mã code từ attribute data-code đã set ở c:forEach
        const assetCode = selectedOption.getAttribute("data-code");
        
        const displayDiv = document.getElementById("assetCodeDisplay");
        if (assetCode) {
            displayDiv.innerText = assetCode;
        } else {
            displayDiv.innerText = "---";
        }
    }
</script>
    </head>
    <body>

        <jsp:include page="/views/header.jsp"/>

        <div class="container-fluid">
            <div class="row main-wrapper">

                <div class="col-md-2 p-0 sidebar-area">
                    <jsp:include page="/views_student_teacher/topnav.jsp"/>
                </div>

                <div class="col-md-10 content-area">

                    <%-- HIỂN THỊ THÔNG BÁO TẠI ĐÂY --%>
                    <c:if test="${not empty successMsg}">
                        <div class="alert alert-success alert-dismissible fade show shadow-sm" role="alert">
                            <i class="bi bi-check-circle-fill me-2"></i>
                            <strong>Thành công!</strong> ${successMsg}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>

                    <c:if test="${not empty errorMsg}">
                        <div class="alert alert-danger alert-dismissible fade show shadow-sm" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            <strong>Lỗi!</strong> ${errorMsg}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <%-- KẾT THÚC THÔNG BÁO --%>

                    <div class="card shadow-sm mb-5">
                        <div class="card-header bg-dark text-white p-3">
                            <h5 class="mb-0"><i class="bi bi-exclamation-octagon me-2"></i> REPORT ROOM ISSUE</h5>
                        </div>
                        <div class="card-body p-4">
                            <form action="${pageContext.request.contextPath}/support" method="post">
                                <div class="row mb-3">
                                    <div class="col-md-4">
                                        <label class="form-label fw-bold">Select Room:</label>
                                        <select name="roomId" id="roomSelect" class="form-select" onchange="onRoomChange()" required>
                                            <option value="">-- Choose Room --</option>
                                            <c:forEach var="r" items="${roomList}">
                                                <option value="${r.roomId}">${r.roomCode}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label class="form-label fw-bold">Asset Type:</label>
                                        <select name="assetId" id="assetSelect" class="form-select" onchange="onAssetChange()" required>
                                            <option value="" data-code="">-- Choose Asset --</option>
                                            <c:forEach var="a" items="${assetList}">
                                                <option value="${a.assetId}" data-room="${a.roomId}" data-code="${a.assetCode}" class="asset-option">
                                                    ${a.assetName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label class="form-label fw-bold">Asset Code:</label>
                                        <div id="assetCodeDisplay" class="form-control bg-light fw-bold text-primary">---</div>
                                    </div>
                                </div>

                                <div class="mb-4">
                                    <label class="form-label fw-bold">Description of Issue:</label>
                                    <textarea name="description" class="form-control" rows="3" placeholder="Describe the problem (e.g. Projector blue screen, broken chair...)" required></textarea>
                                </div>

                                <div class="d-flex justify-content-end gap-2">
                                    <a href="roomuser" class="btn btn-outline-secondary px-4">Back</a>
                                    <button type="submit" class="btn btn-dark px-5 fw-bold">Submit Report</button>
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
                                            <td colspan="6" class="text-center py-4 text-muted">No reports found.</td>
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