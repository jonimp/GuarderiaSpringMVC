<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloGestionUsuario.css">
        <title>gestion usuarios</title>
    </head>
    <body>
        <header class="banda-superior">
            <div class="contendor-cabecera">
                <nav>
                    <div class="lista-menu">
                        <a href="${pageContext.request.contextPath}/" class="enlace-menu">Inicio</a>
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

                <form action="${pageContext.request.contextPath}/admin/buscar?modo=${modo}" method="get">
                    <input type="hidden" name="modo" value="${modo}">
                    <div class="filtros-por-tipo">
                        <select name="tipoUsuario" id="tipoUsuario" onchange="this.form.submit();">
                            <option value="">Todos los tipos</option>
                            <option value="administrador"
                                    ${param.tipoUsuario == 'administrador' ? 'selected' : ''}>
                                Administrador
                            </option>
                            <option value="empleado"
                                    ${param.tipoUsuario == 'empleado' ? 'selected' : ''}>
                                Empleado
                            </option>
                            <option value="socio"
                                    ${param.tipoUsuario == 'socio' ? 'selected' : ''}>
                                Socio
                            </option>
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
                                <td>${u.usuario}</td>
                                <td>${u.nombre}</td>
                                <td>${u.tipo}</td>
                                <td>
                                    <a class="boton" href="${pageContext.request.contextPath}/admin/ver/${u.usuario}">Ver</a>
                                    <a class="boton" href="${pageContext.request.contextPath}/admin/editar/${u.usuario}">Editar</a> 
                                    <form action="${pageContext.request.contextPath}/admin/eliminar/${u.usuario}" 
                                          method="post" 
                                          style="display:inline;"
                                          onsubmit="return confirm('¿Seguro que deseas eliminar este usuario?');">

                                        <button type="submit" class="boton">Eliminar</button>

                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>

            </div>
        </main>
    </body>


</html>