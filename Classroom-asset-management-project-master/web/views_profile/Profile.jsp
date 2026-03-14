```jsp
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>

        <title>User Profile</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">User Profile</span>
            </div>
        </nav>


        <div class="container mt-5" style="max-width:600px;">

            <div class="card shadow">

                <div class="card-body">

                    <form action="User" method="post">

                        <input type="hidden" name="action" value="updateProfile">


                        <h5 class="mb-3">PROFILE</h5>
                        <hr>

                        <!-- NAME -->

                        <div class="mb-3">

                            <label class="form-label">Name</label>

                            <input type="text"
                                   name="name"
                                   class="form-control"
                                   value="${user.fullName}">

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
                                   value="${user.role}"
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

    </body>
</html>
```
