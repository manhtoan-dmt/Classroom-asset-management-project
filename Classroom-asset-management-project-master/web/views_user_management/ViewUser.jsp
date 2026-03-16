<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Management</title>

        <!-- Bootstrap -->
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

    </head>

    <body class="bg-light">

        <div class="container py-5">

            <!-- Title -->
            <div class="text-center mb-4">
                <h2 class="fw-bold">User Information</h2>
                <p class="text-muted">Account details of ${user.fullName}</p>
            </div>

            <!-- Card -->
            <div class="card shadow-lg border-0">

                <div class="card-header bg-primary text-white">
                    <h5 class="mb-0">User: ${user.fullName}</h5>
                </div>

                <div class="card-body">

                    <table class="table table-striped table-hover align-middle">

                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Created</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>

                                <td>${user.userId}</td>

                                <td>
                                    <span class="fw-semibold">${user.username}</span>
                                </td>

                                <td>${user.fullName}</td>

                                <td>${user.email}</td>

                                <td>${user.phone}</td>

                                <!-- ROLE -->
                                <td>
                                    <c:choose>
                                        <c:when test="${user.roleId == 1}">
                                            <span class="badge bg-danger">Admin</span>
                                        </c:when>

                                        <c:when test="${user.roleId == 2}">
                                            <span class="badge bg-warning text-dark">Manager</span>
                                        </c:when>

                                        <c:when test="${user.roleId == 3}">
                                            <span class="badge bg-info text-dark">Teacher</span>
                                        </c:when>

                                        <c:when test="${user.roleId == 4}">
                                            <span class="badge bg-secondary">Student</span>
                                        </c:when>
                                    </c:choose>
                                </td>

                                <!-- STATUS -->
                                <td>
                                    <c:choose>

                                        <c:when test="${user.statusId == 1}">
                                            <span class="badge bg-success">Active</span>
                                        </c:when>

                                        <c:when test="${user.statusId == 2}">
                                            <span class="badge bg-danger">Inactive</span>
                                        </c:when>

                                    </c:choose>
                                </td>

                                <td>${user.createdAt}</td>

                            </tr>
                        </tbody>

                    </table>

                </div>

                <!-- Footer -->
                <div class="card-footer text-end">

                    <a href="UserManagement" class="btn btn-secondary">
                        Back
                    </a>

                    <a href="UserManagement?action=edit&id=${user.userId}" class="btn btn-warning">
                        Edit
                    </a>

                    <a href="UserManagement?action=delete&id=${user.userId}"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('Delete this user?')">

                                    Delete

                                </a>

                </div>

            </div>

        </div>

    </body>
</html>