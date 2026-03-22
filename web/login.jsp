
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
        <title>Login</title>

        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

    </head>



    <body>

        <div class="login-box">

            <h3 class="login-title">Login</h3>

            <form action="login" method="post">

                <div class="mb-3">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="${username}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" value="${password}" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-login w-100">
                    Login
                </button>

            </form>
            <br>
            <c:if test="${error != null}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>
        </div>

    </body>
</html>