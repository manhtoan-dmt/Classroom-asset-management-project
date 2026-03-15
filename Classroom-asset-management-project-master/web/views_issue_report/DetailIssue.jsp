<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Issue Detail</title>

        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/static/css/style.css" />

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

                    <nav class="navbar navbar-dark bg-warning">
                        <div class="container-fluid">
                            <span class="navbar-brand fw-bold">Issue Detail</span>
                        </div>
                    </nav>

                    <div class="container mt-4">

                        <div class="card shadow">

                            <div class="card-header bg-warning text-white">
                                <h4>Issue Detail</h4>
                            </div>

                            <div class="card-body">

                                <form action="Issue" method="post">

                                    <input type="hidden" name="action" value="updateStatus">
                                    <input type="hidden" name="issueId" value="${issue.issueId}">

                                    <!-- ISSUE INFO -->

                                    <h5 class="text-warning">Issue Information</h5>
                                    <hr>

                                    <p><b>Issue ID:</b> ${issue.issueId}</p>

                                    <p><b>Room:</b> ${issue.roomCode}</p>

                                    <p><b>Asset:</b> ${issue.assetName}</p>

                                    <p><b>Reported By:</b> ${issue.reportedBy}</p>

                                    <p><b>Date:</b> ${issue.date}</p>


                                    <!-- DESCRIPTION -->

                                    <h5 class="text-warning mt-3">Description</h5>
                                    <hr>

                                    <p>${issue.description}</p>

                                    <!-- STATUS -->

                                    <h5 class="text-warning mt-3">Issue Status</h5>
                                    <hr>

                                    <select name="issueStatus" class="form-select w-25">

                                        <option value="OPEN"
                                                ${issue.issueStatus=='OPEN'?'selected':''}>
                                            OPEN
                                        </option>

                                        <option value="IN_PROGRESS"
                                                ${issue.issueStatus=='IN_PROGRESS'?'selected':''}>
                                            IN PROGRESS
                                        </option>

                                        <option value="COMPLETED"
                                                ${issue.issueStatus=='COMPLETED'?'selected':''}>
                                            COMPLETED
                                        </option>

                                        <option value="CANCELLED"
                                                ${issue.issueStatus=='CANCELLED'?'selected':''}>
                                            CANCELLED
                                        </option>

                                    </select>


                                    <!-- BUTTON -->

                                    <div class="mt-4">

                                        <button type="submit" class="btn btn-success">
                                            Save
                                        </button>

                                        <a href="Issue?action=list"
                                           class="btn btn-secondary">
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

    <jsp:include page="/views/footer.jsp"/>

</body>
</html>