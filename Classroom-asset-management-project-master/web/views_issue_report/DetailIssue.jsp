<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Issue Detail</title>
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


                                        <h5 class="text-warning">Issue Information</h5>
                                        <hr>

                                        <p><b>Issue ID:</b> ${issue.issueId}</p>

                                        <p><b>Room:</b> ${issue.roomName}</p>

                                        <p><b>Booking ID:</b> ${issue.bookingId}</p>

                                        <p><b>Reported By:</b> ${issue.reportedBy}</p>

                                        <p><b>Created At:</b> ${issue.createdAt}</p>


                                        <h5 class="text-warning mt-3">Title</h5>
                                        <hr>

                                        <p>${issue.title}</p>


                                        <h5 class="text-warning mt-3">Description</h5>
                                        <hr>

                                        <p>${issue.description}</p>


                                        <h5 class="text-warning mt-3">Status</h5>
                                        <hr>

                                        <select name="status" class="form-select w-25">

                                            <option ${issue.status=='Open'?'selected':''}>Open</option>
                                            <option ${issue.status=='Checking'?'selected':''}>Checking</option>
                                            <option ${issue.status=='In Progress'?'selected':''}>In Progress</option>
                                            <option ${issue.status=='Resolved'?'selected':''}>Resolved</option>
                                            <option ${issue.status=='Broken'?'selected':''}>Broken</option>

                                        </select>


                                        <div class="mt-3">

                                            <label class="form-label"><b>Technician Name</b></label>

                                            <input type="text"
                                                   name="technician"
                                                   class="form-control w-50"
                                                   placeholder="Enter technician name">

                                        </div>


                                        <div class="mt-4">

                                            <button class="btn btn-success">Save</button>

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

        <jsp:include page="footer.jsp"/>

    </body>
</html>





