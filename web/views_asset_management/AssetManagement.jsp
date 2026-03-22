<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Asset Management</title>
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

            <div class="container mt-4">

        <h2>Asset Management</h2>


        <!-- BUTTON AREA -->

        <div class="mb-3">

            <a href="Assets?action=create" class="btn btn-success">
                CREATE NEW PRODUCT
            </a>


         
            <a href="Assets?action=assetType"
               class="btn btn-warning">
                Asset Type Management
            </a>


        </div>



        <!-- ASSET TABLE -->

        <table class="table table-bordered table-hover">

            <thead class="table-primary">

                <tr>
                    <th>ID</th>
                    <th>Asset Type</th>
                    <th>Available</th>
                    <th>Broken</th>
                    <th>Disposed</th>
                    <th>In Use</th>
                    <th>Lost</th>
                    <th>Maintenance</th>
                    <th>Total</th>
                    <th>Action</th>

                </tr>

            </thead>

            <tbody>

                <c:forEach var="a" items="${assetStats}">

                    <tr>
                        <td>${a.typeID}</td>
                        <td>${a.assetType}</td>
                        <td>${a.available}</td>
                        <td>${a.broken}</td>
                        <td>${a.disposed}</td>
                        <td>${a.inUse}</td>
                        <td>${a.lost}</td>
                        <td>${a.maintenance}</td>
                        <td>${a.total}</td>

                        <td>

                            <a href="Assets?action=add&type=${a.typeID}"
                               class="btn btn-success btn-sm">
                                Add
                            </a>

                            <a href="Assets?action=view&categoryId=${a.typeID}"
                               class="btn btn-primary btn-sm">
                                View
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

<jsp:include page="/views/footer.jsp"/>

</body>
</html>


    
