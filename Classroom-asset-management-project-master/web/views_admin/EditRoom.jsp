<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Edit Room</title>
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
                <div class="col-md-10 content">

                    <div class="container mt-4">

                        <h2>Edit Room</h2>

                        <form action="Room" method="post">

                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${room.roomID}">

                            <!-- ROOM NAME -->

                            <div class="mb-3">
                                <label class="form-label">Room Name</label>

                                <input type="text"
                                       name="name"
                                       value="${room.name}"
                                       class="form-control"
                                       required>

                                <c:if test="${not empty errorName}">
                                    <div class="text-danger">
                                        ${errorName}
                                    </div>
                                </c:if>

                            </div>


                            <!-- ROOM TYPE -->

                            <div class="mb-3">
                                <label class="form-label">Room Type</label>

                                <select name="type" class="form-control">

                                    <c:forEach var="t" items="${types}">

                                        <option value="${t.typeID}"
                                                <c:if test="${t.typeID == room.typeID}">selected</c:if>>
                                            ${t.typeName}
                                        </option>

                                    </c:forEach>

                                </select>

                            </div>


                            <!-- CAPACITY -->

                            <div class="mb-3">
                                <label class="form-label">Capacity</label>

                                <input type="number"
                                       name="capacity"
                                       value="${room.capacity}"
                                       class="form-control"
                                       min="1"
                                       required>

                            </div>


                            <!-- STATUS -->

                            <div class="mb-3">
                                <label class="form-label">Status</label>

                                <select name="status" class="form-control">

                                    <c:forEach var="s" items="${statuses}">

                                        <option value="${s}"
                                                <c:if test="${s == room.status}">selected</c:if>>
                                            ${s}
                                        </option>

                                    </c:forEach>

                                </select>

                            </div>


                            <!-- LOCATION -->

                            <div class="mb-3">
                                <label class="form-label">Location</label>

                                <select name="location" class="form-control">

                                    <c:forEach var="l" items="${locations}">

                                        <option value="${l.locationID}"
                                                <c:if test="${l.locationID == room.locationID}">selected</c:if>>
                                            ${l.locationName}
                                        </option>

                                    </c:forEach>

                                </select>

                            </div>


                            <!-- DESCRIPTION -->

                            <div class="mb-3">
                                <label class="form-label">Description</label>

                                <textarea name="description"
                                          class="form-control"
                                          rows="3">${room.description}</textarea>

                            </div>


                            <button class="btn btn-primary">Update</button>

                            <a href="Room" class="btn btn-secondary">Cancel</a>

                        </form>

                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>
==





<!--controller-->
<!--String name = request.getParameter("name");

if(roomDAO.isRoomNameExist(name, id)){
    request.setAttribute("errorName", "Room name already exists");
    
    request.getRequestDispatcher("views_admin/edit_room.jsp")
           .forward(request, response);
    return;
}-->

<!--DAO-->
<!--public boolean isRoomNameExist(String name, String id){

String sql = "SELECT * FROM Rooms WHERE name=? AND roomID<>?";

PreparedStatement ps = connection.prepareStatement(sql);
ps.setString(1, name);
ps.setString(2, id);

ResultSet rs = ps.executeQuery();

return rs.next();
}-->