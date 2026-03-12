<%@page import="java.util.List" %>
<%@page import="model.Asset" %>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Assets" method="get">
            <label>Room: </label>
            <select name="roomId">
                <c:forEach var="r" items="${rooms}">
                    <option value="${r.getRoomId()}">${r.getRoomCode()}</option>
                </c:forEach>
            </select>
            <input type="submit" name="Filter"/>
        </form>


        <table>

            <tr>
                <th>Category</th>
                <th>Asset</th>
                <th>Good</th>
                <th>Broken</th>
                <th>Repair</th>
                <th>Total</th>
            </tr>

            <c:forEach items="${assets}" var="a">

                <tr>

                    <td>${a.categoryId}</td>
                    <td>${a.assetName}</td>
                    <td>${a.good}</td>
                    <td>${a.broken}</td>
                    <td>${a.repair}</td>
                    <td>${a.total}</td>

                </tr>

            </c:forEach>

        </table>
    </body>
</html>
