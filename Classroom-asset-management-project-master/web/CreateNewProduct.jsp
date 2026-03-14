<!DOCTYPE html>
<html>
    <head>
        <title>Create Asset</title>
    </head>
    <body>

        <h2>Create New Product</h2>

        <form action="Assets" method="post">
            <input type="hidden" name="action" value="create">

            Asset Name:
            <input type="text" name="name">

            <br><br>

            Asset Type:
            <select name="type">
                <c:forEach var="t" items="${types}">
                    <option value="${t.typeID}">${t.typeName}</option>
                </c:forEach>
            </select>

            <br><br>

            <button>Create</button>

        </form>

    </body>
</html>