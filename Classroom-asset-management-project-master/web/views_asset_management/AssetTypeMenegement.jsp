==
nối từ aset management

<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Asset Type Management</title>
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
                <div class="col-md-10">

                    <div class="bg-light">


                        <!-- NAVBAR -->
                        <nav class="navbar navbar-dark bg-warning">
                            <div class="container-fluid">
                                <span class="navbar-brand fw-bold">Asset Management</span>
                            </div>
                        </nav>


                        <div class="container mt-4">

                            <h3 class="mb-4">Asset Type Management</h3>


                            <!-- ADD ASSET TYPE -->

                            <div class="card mb-4 shadow">

                                <div class="card-header bg-warning text-white fw-bold">
                                    Create New Product
                                </div>

                                <div class="card-body">

                                    <form action="createAssetType" method="post">

                                        <div class="row">

                                            <div class="col-md-8">
                                                <input type="text"
                                                       class="form-control"
                                                       name="categoryName"
                                                       placeholder="Enter asset type name"
                                                       required>
                                            </div>

                                            <div class="col-md-4">

                                                <button class="btn btn-warning text-white w-100">
                                                    Add Asset Type
                                                </button>

                                            </div>

                                        </div>

                                    </form>

                                </div>

                            </div>


                            <!-- TABLE -->

                            <table class="table table-bordered table-hover bg-white shadow">

                                <thead class="table-warning">

                                    <tr>
                                        <th>ID</th>
                                        <th>Asset Type</th>
                                        <th>Action</th>
                                    </tr>

                                </thead>

                                <tbody>

                                    <c:forEach var="c" items="${categories}">

                                        <tr>

                                            <td>${c.categoryId}</td>

                                            <td>${c.categoryName}</td>

                                            <td>

                                                <a href="deleteAssetType?id=${c.categoryId}"
                                                   class="btn btn-danger btn-sm"
                                                   onclick="return confirm('Delete this asset type?')">

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

        <jsp:include page="footer.jsp"/>

    </body>
</html>



