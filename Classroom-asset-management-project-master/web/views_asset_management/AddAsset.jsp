```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>

        <title>Add Asset</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">

                <span class="navbar-brand fw-bold">
                    Asset Management
                </span>

            </div>
        </nav>


        <div class="container mt-5">

            <div class="row justify-content-center">

                <div class="col-lg-7">

                    <div class="card shadow">

                        <div class="card-header bg-warning text-white fw-bold">
                            Add New Asset
                        </div>

                        <div class="card-body">

                            <form action="addAsset" method="post">


                                <!-- ASSET CODE -->
                                <div class="mb-3">
                                    <label class="form-label">Asset Code</label>

                                    <input type="text"
                                           class="form-control"
                                           name="assetCode"
                                           placeholder="Example: CHA-0001"
                                           required>
                                </div>


                                <!-- ASSET NAME -->
                                <div class="mb-3">
                                    <label class="form-label">Asset Name</label>

                                    <input type="text"
                                           class="form-control"
                                           name="assetName"
                                           placeholder="Example: Chair"
                                           required>
                                </div>


                                <!-- CATEGORY -->
                                <div class="mb-3">

                                    <label class="form-label">Category</label>

                                    <select class="form-select" name="categoryId">

                                        <c:forEach var="c" items="${categories}">
                                            <option value="${c.categoryId}">
                                                ${c.categoryName}
                                            </option>
                                        </c:forEach>

                                    </select>

                                </div>


                                <!-- ROOM -->
                                <div class="mb-3">

                                    <label class="form-label">Room</label>

                                    <select class="form-select" name="roomId">

                                        <c:forEach var="r" items="${rooms}">
                                            <option value="${r.roomId}">
                                                ${r.roomCode}
                                            </option>
                                        </c:forEach>

                                    </select>

                                </div>


                                <!-- SERIAL NUMBER -->
                                <div class="mb-3">

                                    <label class="form-label">Serial Number</label>

                                    <input type="text"
                                           class="form-control"
                                           name="serialNumber"
                                           placeholder="Example: SN-1001">

                                </div>


                                <!-- PURCHASE DATE -->
                                <div class="mb-3">

                                    <label class="form-label">Purchase Date</label>

                                    <input type="date"
                                           class="form-control"
                                           name="purchaseDate">

                                </div>


                                <!-- STATUS -->
                                <div class="mb-3">

                                    <label class="form-label">Status</label>

                                    <select class="form-select" name="statusId">

                                        <c:forEach var="s" items="${statuses}">
                                            <option value="${s.statusId}">
                                                ${s.statusName}
                                            </option>
                                        </c:forEach>

                                    </select>

                                </div>


                                <!-- BUTTON -->
                                <div class="d-grid gap-2">

                                    <button class="btn btn-warning text-white fw-bold">
                                        Add Asset
                                    </button>

                                    <a href="assetList" class="btn btn-secondary">
                                        Back
                                    </a>

                                </div>


                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </body>

</html>
```
