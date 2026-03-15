<div class="topnav">

    <a href="${pageContext.request.contextPath}/Home">Home</a>
    <a href="${pageContext.request.contextPath}/Room">Room Management</a>
    <a href="assets.jsp">Asset Management</a>
    <a href="${pageContext.request.contextPath}/Book">Manage Book</a>
    <a href="${pageContext.request.contextPath}/Issue">Issue Reports</a>
    
    <c:if test="${sessionScope.user.roleId == 1}">
        <a href="userManagement.jsp">User Management</a>
    </c:if>
    
    <a href="users.jsp">Profile</a>
    <a href="users.jsp">Logout</a>

</div>