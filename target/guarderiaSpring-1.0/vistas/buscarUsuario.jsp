<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Búsqueda de Usuarios</title>
    <link rel="stylesheet" type="text/css" href="/css/estiloBusqueda.css">
</head>
<body>
    <div class="search-container">
        <h2>Búsqueda de Usuarios</h2>
        
        <form action="buscarUsuarios" method="get">
            <div class="search-filters">
                <select name="tipo">
                    <option value="">Seleccione tipo de usuario</option>
                    <option value="administrador">Administrador</option>
                    <option value="empleado">Empleado</option>
                    <option value="socio">Socio</option>
                </select>

                <input type="text" 
                       name="nombre" 
                       placeholder="Buscar por nombre...">

                <button type="submit">Buscar</button>
            </div>
        </form>

        <table class="results-table">
            <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${usuarios}">
                    <tr>
                        <td>${usuario.usuario}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.tipoUsuario}</td>
                        <td>
                            <a href="verUsuario?id=${usuario.id}" class="btn-ver">Ver</a>
                            <a href="editarUsuario?id=${usuario.id}" class="btn-editar">Editar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>