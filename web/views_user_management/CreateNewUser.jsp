<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Create User</title>
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

                        <div class="container mt-5">

                            <div class="card shadow">

                                <div class="card-header bg-success text-white">
                                    <h4>Create New User</h4>
                                </div>

                                <div class="card-body">


                                    ${error}


                                    <form action="${pageContext.request.contextPath}/UserManagement" method="post">
                                        <input type="hidden" name="action" value="create">
                                        <div class="mb-3">
                                            <label>Username</label>
                                            <input type="text" name="username"
                                                   class="form-control" required>
                                        </div>

                                        <div class="mb-3">
                                            <label>Password</label>
                                            <input type="text" name="password"
                                                   class="form-control" required>
                                        </div>

                                        <div class="mb-3">
                                            <label>Full Name</label>
                                            <input type="text" name="fullName"
                                                   class="form-control">
                                        </div>

                                        <div class="mb-3">
                                            <label>Email</label>
                                            <input type="text" name="email"
                                                   class="form-control">
                                        </div>

                                        <div class="mb-3">
                                            <label>Phone</label>
                                            <input type="text" name="phone"
                                                   class="form-control">
                                        </div>

                                        <div class="mb-3">
                                            <label>Role</label>

                                            <select name="roleId" class="form-select">

                                                <option value="1">Admin</option>
                                                <option value="2">Manager</option>
                                                <option value="3">Teacher</option>
                                                <option value="4">Student</option>

                                            </select>

                                        </div>

                                        <div class="mb-3">
                                            <label>Status</label>

                                            <select name="statusId" class="form-select">

                                                <option value="1">Active</option>
                                                <option value="2">Inactive</option>

                                            </select>

                                        </div>

                                        <button class="btn btn-success">
                                            Create User
                                        </button>

                                        <a href="${pageContext.request.contextPath}/UserManagement"
                                           class="btn btn-secondary">
                                            Cancel
                                        </a>

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



