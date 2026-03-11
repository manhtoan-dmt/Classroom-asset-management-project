<%-- 
    Document   : Room
    Created on : Mar 11, 2026, 10:01:11 PM
    Author     : ADMIN
--%>
<%@page import="java.util.List" %>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Room Management</h2>
        <table>

            <tr>
                <th>Room ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Building</th>
                <th>Capacity</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="r" items="${rooms}">

                <tr>
                    <td>${r.roomId}</td>

                    <td>${r.roomCode}</td>

                    <td>${r.typeName}</td>

                    <td>${r.buildingName}</td>

                    <td>${r.capacity}</td>

                    <td>${r.statusName}</td>

                    <td>
                        <a href="viewRoom?id=${r.roomId}">View</a>
                        <a href="editRoom?id=${r.roomId}">Edit</a>
                        <a href="deleteRoom?id=${r.roomId}"
                           onclick="return confirm('Delete this room?')">Delete</a>
                    </td>

                </tr>

            </c:forEach>

        </table>

    </body>
</html>
