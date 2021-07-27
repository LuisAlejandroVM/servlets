<%--
  Created by IntelliJ IDEA.
  User: Alejandro
  Date: 05/07/2021
  Time: 08:26 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Listado de usuarios</title>
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/styles.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
    <a href="${context}/views/user/register.jsp" class="btn btn-outline-success"><i class="fas fa-plus"></i> Agregar usuario</a>
    <table class="table">
        <thead class="table-dark">
            <tr>
                <th>No.</th>
                <th>Nombre completo</th>
                <th>Correo</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${ listUsers }" var="user" varStatus="status">
            <tr>
                <td>${ status.count }</td>
                <td>${ user.idPerson.name } ${ user.idPerson.lastname }</td>
                <td>${ user.email }</td>
                <td>
                    <c:if test="${ user.status == 1 }">
                        <span class="badge rounded-pill bg-success">Activo</span>
                    </c:if>
                    <c:if test="${ user.status == 0 }">
                        <span class="badge rounded-pill bg-danger">Inactivo</span>
                    </c:if>
                </td>
                <td>
                    <c:if test="${ user.status == 1 }">
                        <form action="${context}/getUserById" method="POST" style="display: inline;">
                            <input type="hidden" name="action" value="getUserById">
                            <input type="hidden" name="id" value="${ user.id }">
                            <button type="submit" class="btn btn-outline-primary"><i class="fas fa-edit"></i> Modificar</button>
                        </form>
                        <button id="btn-delete-${ status.count }" data-code="${ user.id }" data-text="${ user.idPerson.name } ${ user.idPerson.lastname }" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#delete"><i class="fas fa-trash"></i> Eliminar</button>
                    </c:if>
                    <c:if test="${ user.status == 0 }">
                        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#details"><i class="fas fa-info-circle"></i> Detalles</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- MODAL --%>
    <div class="modal fade" id="delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${context}/deleteUser" method="POST">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" id="id">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Eliminar usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <label>¿Deshabilitar?</label>
                        <h5 id="text-delete"></h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Cerrar</button>
                        <button type="submit" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="details" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel2">Detalles del usuario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h5>Nombre(s):</h5>
                    <label>Luis</label>
                    <br>
                    <h5>Apellido(s):</h5>
                    <label>Vázquez</label>
                    <br>
                    <h5>Correo:</h5>
                    <label>luisvazquez@utez.edu.mx</label>
                    <br>
                    <h5>Contraseña:</h5>
                    <label>1234</label>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script src="${context}/assets/dist/js/main.js"></script>
</body>
</html>
