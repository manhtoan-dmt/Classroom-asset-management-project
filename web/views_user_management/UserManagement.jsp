<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>User Management</title>
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

                        <!-- NAVBAR -->
                        <nav class="navbar navbar-dark bg-warning">
                            <div class="container-fluid">
                                <span class="navbar-brand fw-bold">User Management</span>
                            </div>
                        </nav>


                        <div class="container mt-4">

                            <h3 class="mb-4">User Management</h3>


                            <!-- SEARCH -->

                            <form action="UserManagement" method="get" class="mb-3">

                                <div class="row">

                                    <div class="col-md-4">

                                        <input type="text"
                                               name="keyword"
                                               value="${keyword}"
                                               class="form-control"
                                               placeholder="Search by name or email">

                                    </div>

                                    <div class="col-md-2">

                                        <button class="btn btn-warning text-white w-100">
                                            Search
                                        </button>

                                    </div>

                                    <div class="col-md-3">

                                        <a href="views_user_management/CreateNewUser.jsp"
                                           class="btn btn-success w-100">

                                            Create New Account

                                        </a>

                                    </div>

                                </div>

                            </form>



                            <!-- USER TABLE -->

                            <table class="table table-bordered table-hover bg-white shadow">

                                <thead class="table-warning">

                                    <tr>

                                        <th>User ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th>Action</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    <c:forEach var="u" items="${lUser}">

                                        <tr>

                                            <td>${u.userId}</td>
                                            <td>${u.fullName}</td>
                                            <td>${u.email}</td>
                                            <td>${u.roleId}</td>

                                            <td>

                                                <c:choose>

                                                    <c:when test="${u.statusId==1}">
                                                        <span class="badge bg-success">Active</span>
                                                    </c:when>

                                                    <c:when test="${u.statusId==2}">
                                                        <span class="badge bg-danger">Inactive</span>
                                                    </c:when>

                                                </c:choose>

                                            </td>

                                            <td>

                                                <a href="UserManagement?action=view&id=${u.userId}"
                                                   class="btn btn-primary btn-sm">

                                                    View

                                                </a>

                                                <a href="UserManagement?action=edit&id=${u.userId}"
                                                   class="btn btn-warning btn-sm text-white">

                                                    Edit

                                                </a>

                                                <a href="UserManagement?action=delete&id=${u.userId}"
                                                   class="btn btn-danger btn-sm"
                                                   onclick="return confirm('Delete this user?')">

                                                    Delete

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

        <jsp:include page="/views/footer.jsp"/>

    </body>
</html>


====




