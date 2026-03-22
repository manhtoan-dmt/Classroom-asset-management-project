<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Edit User</title>
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

                                <div class="card-header bg-warning">
                                    <h4>Edit User</h4>
                                </div>

                                <div class="card-body">

                                    <form action="UserManagement" method="post">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="id" value="${user.userId}">

                                        <div class="mb-3">
                                            <label class="form-label">Username</label>
                                            <input type="text" class="form-control"
                                                   name="username" value="${user.username}">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Full Name</label>
                                            <input type="text" class="form-control"
                                                   name="fullName" value="${user.fullName}">
                                        </div>
                                        <div class="mb-3">
                                            <label>Password</label>
                                            <input type="text" class="form-control"
                                                   name="password" value="${user.password}">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Email</label>
                                            <input type="text" class="form-control"
                                                   name="email" value="${user.email}">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Phone</label>
                                            <input type="text" class="form-control"
                                                   name="phone" value="${user.phone}">
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Role</label>

                                            <select class="form-select" name="role">

                                                <option value="1" ${user.roleId==1?"selected":""}>Admin</option>
                                                <option value="2" ${user.roleId==2?"selected":""}>Manager</option>
                                                <option value="3" ${user.roleId==3?"selected":""}>Teacher</option>
                                                <option value="4" ${user.roleId==4?"selected":""}>Student</option>

                                            </select>

                                        </div>

                                        <div class="mb-3">

                                            <label class="form-label">Status</label>

                                            <select class="form-select" name="status">

                                                <option value="1" ${user.statusId==1?"selected":""}>Active</option>
                                                <option value="2" ${user.statusId==2?"selected":""}>Inactive</option>

                                            </select>

                                        </div>

                                        <button class="btn btn-success">
                                            Update
                                        </button>

                                        <a href="UserManagement" class="btn btn-secondary">
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



