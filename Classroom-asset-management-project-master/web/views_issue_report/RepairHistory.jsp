<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>

<%
    String today = LocalDate.now().toString();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Repair History</title>
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
                                <span class="navbar-brand fw-bold">Repair History</span>
                            </div>
                        </nav>

                        <div class="container mt-4">

                            <h3 class="mb-4">Repair History</h3>


                            <!-- FILTER DATE -->

                            <form action="Issue" method="get" class="mb-3">

                                <input type="hidden" name="action" value="history">

                                <div class="row">

                                    <div class="col-md-3">

                                        <label class="form-label">Filter by Date</label>

                                        <input type="date"
                                               name="date"
                                               class="form-control"
                                               value="<%= today %>">

                                    </div>

                                    <div class="col-md-2 d-flex align-items-end">

                                        <button class="btn btn-warning text-white w-100">
                                            Filter
                                        </button>

                                    </div>

                                </div>

                            </form>


                            <!-- HISTORY TABLE -->

                            <table class="table table-bordered table-hover bg-white shadow">

                                <thead class="table-warning">

                                    <tr>

                                        <th>Request ID</th>
                                        <th>Asset</th>
                                        <th>Reported By</th>
                                        <th>Technician</th>
                                        <th>Status</th>
                                        <th>Description</th>
                                        <th>Time</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    <c:forEach var="h" items="${historyList}">

                                        <tr>

                                            <td>${h.requestId}</td>

                                            <td>
                                                ${h.assetCode} - ${h.assetName}
                                            </td>

                                            <td>${h.reportedBy}</td>

                                            <td>${h.technician}</td>

                                            <td>${h.status}</td>

                                            <td>${h.description}</td>

                                            <td>${h.createdAt}</td>

                                        </tr>

                                    </c:forEach>

                                </tbody>

                            </table>


                            <a href="Issue?action=list"
                               class="btn btn-secondary">

                                Back

                            </a>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>






