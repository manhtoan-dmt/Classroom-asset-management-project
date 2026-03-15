
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>

        <title>Create Booking</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body class="bg-light">

        <!-- NAVBAR -->
        <nav class="navbar navbar-dark bg-warning">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold">Room Booking Management</span>
            </div>
        </nav>


        <div class="container mt-4">

            <h3 class="mb-4">Create New Booking</h3>


            <div class="card shadow">

                <div class="card-header bg-warning text-white fw-bold">
                    Booking Information
                </div>

                <div class="card-body">

                    <form action="Book" method="post">

                        <input type="hidden" name="action" value="create">

                        <!-- ROOM -->

                        <div class="mb-3">

                            <label class="form-label">Room</label>

                            <select name="roomId" class="form-select" required>

                                <c:forEach var="r" items="${rooms}">
                                    <option value="${r.roomId}">
                                        ${r.roomCode}
                                    </option>
                                </c:forEach>

                            </select>

                        </div>



                        <!-- DATE -->

                        <div class="mb-3">

                            <label class="form-label">Date</label>

                            <input type="date"
                                   name="date"
                                   class="form-control"
                                   required>

                        </div>



                        <!-- TIME SLOT -->

                        <div class="mb-3">

                            <label class="form-label">Time Slot</label>

                            <select name="timeSlot" class="form-select" required>

                                <option value="07-09">07:00 - 09:00</option>
                                <option value="09-11">09:00 - 11:00</option>
                                <option value="13-15">13:00 - 15:00</option>
                                <option value="15-17">15:00 - 17:00</option>

                            </select>

                        </div>



                        <!-- PURPOSE -->

                        <div class="mb-3">

                            <label class="form-label">Purpose</label>

                            <textarea name="purpose"
                                      class="form-control"
                                      rows="3"
                                      placeholder="Purpose of booking"></textarea>

                        </div>



                        <div class="d-flex gap-2">

                            <button class="btn btn-warning text-white fw-bold">
                                Create Booking
                            </button>

                            <a href="Book" class="btn btn-secondary">
                                Cancel
                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </body>
</html>
```
