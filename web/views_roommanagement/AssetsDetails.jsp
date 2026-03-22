<%-- 
    Document   : AssetsDetails
    Created on : Mar 13, 2026, 10:17:27 PM
    Author     : ADMIN
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Assets" method="get">

            Room:
            <select name="roomId">

                <option value="">All</option>

                <c:forEach var="r" items="${rooms}">
                    <option value="${r.roomId}"
                            <c:if test="${r.roomId == roomId}">selected</c:if>>
                        ${r.roomCode}
                    </option>
                </c:forEach>

            </select>

            Asset:
            <select name="assetName">

                <option value="">All</option>

                <c:forEach var="a" items="${assetNames}">
                    <option value="${a}"
                            <c:if test="${a == assetName}">selected</c:if>>
                        ${a}
                    </option>
                </c:forEach>

            </select>

            <input type="submit" value="Filter">

        </form>
        <table border="1">

            <tr>
                <th>Asset Code</th>
                <th>Asset Name</th>
                <th>Category</th>
                <th>Quantity</th>
                <th>Serial Number</th>
                <th>Purchase Date</th>
                <th>Status</th>
            </tr>

            <c:forEach var="a" items="${assets}">

                <tr>
                    <td>${a.assetCode}</td>
                    <td>${a.assetName}</td>
                    <td>${a.categoryId}</td>
                    <td>${a.quantity}</td>
                    <td>${a.serialNumber}</td>
                    <td>${a.purchaseDate}</td>
                    <td>${a.statusName}</td>
                </tr>

            </c:forEach>

        </table>
    </body>
</html>
