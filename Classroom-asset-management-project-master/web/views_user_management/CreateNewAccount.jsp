```jsp id="c7h9sv"
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>

        <title>Create New Account</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">Create New Account</span>
            </div>
        </nav>

        <div class="container mt-4">

            <div class="card shadow">

                <div class="card-header bg-warning text-white">
                    <h4>Create New Account</h4>
                </div>

                <div class="card-body">

                    <form action="User" method="post">

                        <input type="hidden" name="action" value="create">

                        <!-- USER ID -->

                        <div class="mb-3">

                            <label class="form-label">User ID</label>

                            <input type="text"
                                   name="userId"
                                   class="form-control"
                                   placeholder="Enter user ID (U001...)"
                                   required>

                        </div>


                        <!-- NAME -->

                        <div class="mb-3">

                            <label class="form-label">Full Name</label>

                            <input type="text"
                                   name="fullName"
                                   class="form-control"
                                   placeholder="Enter full name"
                                   required>

                        </div>


                        <!-- EMAIL -->

                        <div class="mb-3">

                            <label class="form-label">Email</label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   placeholder="Enter email"
                                   required>

                        </div>


                        <!-- PASSWORD -->

                        <div class="mb-3">

                            <label class="form-label">Password</label>

                            <input type="password"
                                   name="password"
                                   class="form-control"
                                   placeholder="Enter password"
                                   required>

                        </div>


                        <!-- ROLE -->

                        <div class="mb-3">

                            <label class="form-label">Role</label>

                            <select name="role" class="form-select">

                                <option value="Student">Student</option>
                                <option value="Teacher">Teacher</option>
                                <option value="Manager">Room Manager</option>
                                <option value="Admin">Admin</option>

                            </select>

                        </div>


                        <!-- STATUS -->

                        <div class="mb-3">

                            <label class="form-label">Status</label>

                            <select name="status" class="form-select">

                                <option value="Active">Active</option>
                                <option value="Inactive">Inactive</option>

                            </select>

                        </div>


                        <!-- BUTTON -->

                        <div class="mt-4">

                            <button class="btn btn-success">
                                Create
                            </button>

                            <a href="User?action=list"
                               class="btn btn-secondary">

                                Back

                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </body>
</html>
```
