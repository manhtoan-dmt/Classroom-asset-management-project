```jsp id="zqk3vt"
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>

        <title>User Management</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">User Management</span>
            </div>
        </nav>


        <div class="container mt-4">

            <h3 class="mb-4">User Management</h3>


            <!-- SEARCH -->

            <form action="User" method="get" class="mb-3">

                <div class="row">

                    <div class="col-md-4">

                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="Search by name or email">

                    </div>

                    <div class="col-md-2">

                        <button class="btn btn-warning text-white w-100">
                            Search
                        </button>

                    </div>

                    <div class="col-md-3">

                        <a href="createUser.jsp"
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

                    <c:forEach var="u" items="${userList}">

                        <tr>

                            <td>${u.userId}</td>
                            <td>${u.fullName}</td>
                            <td>${u.email}</td>
                            <td>${u.role}</td>

                            <td>

                                <c:choose>

                                    <c:when test="${u.status=='Active'}">
                                        <span class="badge bg-success">Active</span>
                                    </c:when>

                                    <c:when test="${u.status=='Inactive'}">
                                        <span class="badge bg-danger">Inactive</span>
                                    </c:when>

                                </c:choose>

                            </td>

                            <td>

                                <a href="User?action=view&id=${u.userId}"
                                   class="btn btn-primary btn-sm">

                                    View

                                </a>

                                <a href="User?action=edit&id=${u.userId}"
                                   class="btn btn-warning btn-sm text-white">

                                    Edit

                                </a>

                                <a href="User?action=delete&id=${u.userId}"
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

    </body>
</html>
```
