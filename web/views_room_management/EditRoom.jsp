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

        <jsp:include page="/views/header.jsp"/>

        <div class="container-fluid">

            <div class="row">

                <!-- SIDEBAR -->
                <div class="col-md-2 p-0">
                    <jsp:include page="/views/topnav.jsp"/>
                </div>

                <!-- CONTENT -->
                <div class="col-md-10 content">

                    <div class="container mt-4">

                        <h2>Edit Room</h2>
                        <form action="Room" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${room.roomId}">

                            <!-- ROOM NAME -->

                            <div class="mb-3">
                                <label class="form-label">Room Name</label>
                                <input type="text"
                                       name="roomCode"
                                       value="${room.roomCode}"
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
                                <select name="typeId" class="form-control">
                                    <c:forEach var="t" items="${types}">
                                        <option value="${t.typeId}"
                                                >
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
                                <select name="statusId" class="form-control">
                                    
                                    <c:forEach var="s" items="${statuses}">
                                        <option value="${s.statusId}"
                                                <c:if test="${s.statusId == room.statusId}">selected</c:if>>
                                            ${s.statusName}
                                        </option>
                                    </c:forEach>
                                    
                                </select>
                                    
                                
                            </div>


                            <!-- LOCATION -->

                            <div class="mb-3">
                                <label class="form-label">Location</label>
                                <select name="buildingId" class="form-control">
                                    <c:forEach var="l" items="${locations}">
                                        <option value="${l.buildingId }"
                                                <c:if test="${l.buildingId  == room.buildingId}">selected</c:if>>
                                            ${l.buildingName}
                                            </option>
                                    </c:forEach>
                                </select>
                                
                            </div>





                            <button class="btn btn-primary">Update</button>
                            <a href="Room" class="btn btn-secondary">Cancel</a>
                        </form>

                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="/views/footer.jsp"/>

    </body>
</html>
