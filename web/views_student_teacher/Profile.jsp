<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>User Profile</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

    </head>
    <body>

        <jsp:include page="/views/header.jsp"/>

        <div class="container-fluid">

            <div class="row">

                <!-- SIDEBAR -->
                <div class="col-md-2 p-0">
                    <jsp:include page="/views_student_teacher/topnav.jsp"/>
                </div>

                <!-- CONTENT -->
                <div class="col-md-10">

                    <div class="bg-light">

                        <!-- NAVBAR -->
                        <nav class="navbar navbar-dark bg-warning">
                            <div class="container-fluid">
                                <span class="navbar-brand fw-bold">User Profile</span>
                            </div>
                        </nav>


                        <div class="container mt-5" style="max-width:600px;">

                            <div class="card shadow">

                                <div class="card-body">

                                    <form action="profile" method="post">

                                        <input type="hidden" name="action" value="updateAll">

                                        <!-- MESSAGE -->
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger">${error}</div>
                                        </c:if>

                                        <c:if test="${not empty success}">
                                            <div class="alert alert-success">${success}</div>
                                        </c:if>


                                        <h5 class="mb-3">PROFILE</h5>
                                        <hr>

                                        <!-- NAME -->

                                        <div class="mb-3">

                                            <label class="form-label">Name</label>

                                            <input type="text"
                                                   name="fullName"
                                                   class="form-control"
                                                   value="${user.fullName}">

                                        </div>

                                        <!--UserName-->
                                        <div class="mb-3">
                                            <label class="form-label">Username</label>
                                            <input type="text" name="username" class="form-control" value="${user.username}">
                                        </div>

                                        <!--Phone-->

                                        <div class="mb-3">
                                            <label class="form-label">Phone</label>
                                            <input type="text" name="phone" class="form-control" value="${user.phone}">
                                        </div>


                                        <!-- EMAIL -->

                                        <div class="mb-3">

                                            <label class="form-label">Email</label>

                                            <input type="email"
                                                   name="email"
                                                   class="form-control"
                                                   value="${user.email}">

                                        </div>




                                        <!-- ROLE -->

                                        <div class="mb-3">

                                            <label class="form-label">Role</label>

                                            <input type="text"
                                                   class="form-control"
                                                   value="${user.roleName}"
                                                   readonly>

                                        </div>


                                        <h5 class="mt-4 mb-3">CHANGE PASSWORD</h5>
                                        <hr>


                                        <!-- CURRENT PASSWORD -->

                                        <div class="mb-3">

                                            <label class="form-label">Current Password</label>

                                            <input type="password"
                                                   name="currentPassword"
                                                   class="form-control"
                                                   placeholder="Enter current password">

                                        </div>


                                        <!-- NEW PASSWORD -->

                                        <div class="mb-3">

                                            <label class="form-label">New Password</label>

                                            <input type="password"
                                                   name="newPassword"
                                                   class="form-control"
                                                   placeholder="Enter new password">

                                        </div>


                                        <div class="mt-4">

                                            <button class="btn btn-warning text-white w-100">
                                                Update Profile
                                            </button>

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





