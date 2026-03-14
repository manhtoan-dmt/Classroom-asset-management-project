
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>

<%
    String today = LocalDate.now().toString();
%>

<!DOCTYPE html>
<html>
    <head>

        <title>Issue Reports</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">Issue Reports | Repair History</span>
            </div>
        </nav>


        <div class="container mt-4">

            <h3 class="mb-4">Issue Reports</h3>


            <!-- FILTER DATE + STATUS -->

            <form action="Issue" method="get">

                <div class="row mb-3">

                    <div class="col-md-3">

                        <label class="form-label">Filter Date</label>

                        <input type="date"
                               name="date"
                               class="form-control"
                               value="<%= today %>">

                    </div>

                    <div class="col-md-3">

                        <label class="form-label">Status</label>

                        <select name="status" class="form-select">

                            <option value="">All</option>
                            <option>Error</option>
                            <option>Checking</option>
                            <option>In Progress</option>
                            <option>Fixed</option>
                            <option>Broken</option>

                        </select>

                    </div>

                    <div class="col-md-2 d-flex align-items-end">

                        <button class="btn btn-warning text-white w-100">
                            Filter
                        </button>

                    </div>

                </div>

            </form>



            <table class="table table-bordered table-hover bg-white shadow">

                <thead class="table-warning">

                    <tr>

                        <th>ID</th>
                        <th>Room</th>
                        <th>Title</th>
                        <th>Reported By</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    <c:forEach var="i" items="${issues}">

                        <tr>

                            <td>${i.issueId}</td>
                            <td>${i.roomName}</td>
                            <td>${i.title}</td>
                            <td>${i.reportedBy}</td>

                            <td>

                                <span class="badge bg-secondary">${i.status}</span>

                            </td>

                            <td>${i.date}</td>

                            <td>

                                <a href="Issue?action=detail&id=${i.issueId}"
                                   class="btn btn-primary btn-sm">

                                    View

                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </div>

    </body>
</html>
```
