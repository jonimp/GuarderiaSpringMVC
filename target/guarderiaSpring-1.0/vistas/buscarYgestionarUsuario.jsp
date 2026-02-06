<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estiloGestionUsuario.css">
        <title>gestion usuarios</title>
    </head>
    <body>
        <header class="banda-superior">
            <div class="contendor-cabecera">
                <nav>
                    <div class="lista-menu">
                        <a href="#" class="enlace-menu">Inicio</a>
                        <a href="#" class="enlace-menu">Servicios</a>
                        <a href="#" class="enlace-menu">Galería</a>
                        <a href="#" class="enlace-menu">Contacto</a>
                    </div>
                </nav>

            </div>    
        </header>

        <main class="contenido-principal">
            <div class="menu-container">
                <h2>Búsqueda de Usuarios</h2>

                <form action="${pageContext.request.contextPath}/admin/buscar" method="get">
                    <div class="filtros-por-tipo">
                        <select name="tipoUsuario" id="tipoUsuario">
                            <option value="">Todos los tipos</option>
                            <option value="administrador">Administrador</option>
                            <option value="empleado">Empleado</option>
                            <option value="socio">Socio</option>
                        </select> 

                        <input type="text"
                               name="nombre" 
                               placeholder=" Buscar por nombre ó usuario ..."
                               id="nombreBusqueda">

                        <button type="submit" class="boton-busqueda">Buscar</button>
                    </div>
                </form>
                    
                <c:if test="${empty usuarios}">
                    <p>No se encontraron usuarios.</p>
                </c:if>
    
                <!-- Tabla de resultados -->
                <table class="tabla-resultados">
                    <thead>
                        <tr>
                            <th>Usuario</th>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${usuarios}">
                            <tr>
                                <td>${u.username}</td>
                                <td>${u.nombre}</td>
                                <td>${u.tipo}</td>
                                <td>
                                    <button class="boton-acciones" href="ver?user=${u.username}">Ver</button>
                                    <button class="boton-acciones" href="editar?user=${u.username}">Editar</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>

            </div>
        </main>
    </body>


</html>