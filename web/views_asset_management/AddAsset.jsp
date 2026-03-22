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

            <form action="Add" method="post">

       <label>Asset</label>

        <select name="assetId" class="form-select">

            <c:forEach var="a" items="${storageAssets}">
                <option value="${a.assetId}">
                    ${a.assetCode} - ${a.assetName}
                </option>
            </c:forEach>

        </select>


        <label class="mt-3">Room</label>

        <select name="roomId" class="form-select">

            <c:forEach var="r" items="${rooms}">
                <option value="${r.roomId}">
                    ${r.roomCode}
                </option>
            </c:forEach>

        </select>

        <button class="btn btn-warning mt-3">
            Add Asset
        </button>

    </form>

        </div>

    </div>

</div>

<jsp:include page="/views/footer.jsp"/>

</body>
</html>



    