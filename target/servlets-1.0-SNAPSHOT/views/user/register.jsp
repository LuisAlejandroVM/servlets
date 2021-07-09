<%--
  Created by IntelliJ IDEA.
  User: CDS
  Date: 06/07/2021
  Time: 08:31 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Registrar Usuario | Tiendita</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
    <h1>Registrar Usuario</h1>
    <form action="${context}/ServletUser" method="POST">
        <input type="hidden" value="create" name="action">
        <label>Nombre(s):</label>
        <input class="form-control" type="text" name="name" />
        <br>
        <label>Apellido(s):</label>
        <input class="form-control" type="text" name="lastname" />
        <br>
        <label>Edad:</label>
        <input class="form-control" type="number" name="age" />
        <br>
        <label>Correo:</label>
        <input class="form-control" type="email" name="email" />
        <br>
        <label>Contraseña:</label>
        <input class="form-control" type="password" name="password" />
        <br>
        <label>Rol:</label>
        <select class="form-select" name="role">
            <option value="1">Administrador</option>
            <option value="2">Director</option>
        </select>

        <button type="button" class="btn btn-secondary"><i class="fas fa-times"></i> Cancelar</button>
        <button type="submit" class="btn btn-success"><i class="fas fa-plus"></i> Agregar</button>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
