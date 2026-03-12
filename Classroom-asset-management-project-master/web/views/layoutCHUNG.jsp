<body>

<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-2 p-0">
            <jsp:include page="topnav.jsp"/>
        </div>

        <!-- CONTENT -->
        <div class="col-md-10 content">

            <h1>CODE HERE</h1>

            <h4>List of Accounts</h4>

        </div>

    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>

<-<!-- ================================ -->
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Accounts</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/static/css/style.css" />
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
        <div class="col-md-10 content">

            <h1>CODE HERE</h1>

            <h4>List of Accounts</h4>

        </div>

    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>

