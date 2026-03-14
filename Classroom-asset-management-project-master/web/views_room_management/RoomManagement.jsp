
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Room Management</title>
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
                <div class="col-md-10 content">

                    <div class="container mt-4">

                        <h2>Room Management</h2>

                        <!-- SEARCH -->
                        <form action="Room" method="get" class="mb-3">

                            <div class="row">

                                <div class="col-md-3">
                                    <input type="text"
                                           name="keyword"
                                           class="form-control"
                                           placeholder="Search room...">
                                </div>

                                <div class="col-md-2">
                                    <button class="btn btn-primary">Search</button>
                                </div>

                                <div class="col-md-2">
                                    <a href="Room?action=add" class="btn btn-success">Add Room</a>
                                </div>

                            </div>

                        </form>

                        <!-- ROOM TABLE -->

                        <table class="table table-bordered table-hover">

                            <thead class="table-dark">

                                <tr>
                                    <th>Room ID</th>
                                    <th>Name</th>
                                    <th>Type</th>
                                    <th>Building</th>
                                    <th>Capacity</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>

                            </thead>

                            <tbody>

                                <c:forEach var="r" items="${rooms}">

                                    <tr>

                                        <td>${r.roomID}</td>
                                        <td>${r.name}</td>
                                        <td>${r.type}</td>
                                        <td>${r.building}</td>
                                        <td>${r.capacity}</td>
                                        <td>${r.status}</td>

                                        <td>

                                            <a href="Room?action=view&id=${r.roomID}"
                                               class="btn btn-info btn-sm">View</a>

                                            <a href="Room?action=edit&id=${r.roomID}"
                                               class="btn btn-warning btn-sm">Edit</a>
                                            <!--check lại cái delete này ở sevlet vì người dùng có thể dalete qua đường -->
                                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                                <a href="Room?action=delete&id=${r.roomID}"
                                                   class="btn btn-danger btn-sm"
                                                   onclick="return confirm('Delete this room?')">
                                                    Delete
                                                </a>
                                            </c:if>


                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                    </div>


                </div>

            </div>

        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>




