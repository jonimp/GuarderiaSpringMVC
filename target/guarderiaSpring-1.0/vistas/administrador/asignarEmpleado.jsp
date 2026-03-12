<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloAsignar.css">
        <title>Gestión de Usuarios</title>
    </head>
    <body>
        <header class="banda-superior">
            <div class="contendor-cabecera">
                <nav>
                    <div class="lista-menu">
                        <a href="${pageContext.request.contextPath}/" class="enlace-menu">Inicio</a>
                        <a href="${pageContext.request.contextPath}/servicios" class="enlace-menu">Servicios</a>
                        <a href="${pageContext.request.contextPath}/galeria" class="enlace-menu">Galería</a>
                        <a href="${pageContext.request.contextPath}/contacto" class="enlace-menu">Contacto</a>
                    </div>
                </nav>
            </div>    
        </header>

        <div class="formularios-container" 
             style="display: flex; flex-direction: column; align-items: center; gap:20px;">

            <form method="post" 
                  action="${pageContext.request.contextPath}/admin/asignarEmpleado">

                <label>Seleccionar Empleado:</label>
                <div class="dato-content">
                    <select class="selector" name="dniEmpleado" required>
                        <option value="">-- Seleccionar --</option>
                        <c:forEach var="empleado" items="${empleados}">
                            <option value="${empleado.dni}">
                                ${empleado.nombre} - ${empleado.dni}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <label>Seleccionar Espacio:</label>
                <div class="dato-content">
                    <select class="selector" name="idEspacio" required>
                        <option value="">-- Seleccionar --</option>
                        <c:forEach var="espacio" items="${espacios}">
                            <option value="${espacio.id}">
                                Espacio ${espacio.id} - ${espacio.tipoZona}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit">Asignar</button>

            </form>


            <h3>Desasignar empleado de espacio</h3>

            <form method="post" 
                  action="${pageContext.request.contextPath}/admin/desasignarEmpleado">

                <label>Seleccionar Espacio:</label>
                <select name="idEspacio" required>
                    <option value="">-- Seleccionar --</option>

                    <c:forEach var="espacio" items="${espacios}">
                        <c:if test="${not empty espacio.empleadoAsignado}">
                            <option value="${espacio.id}">
                                Espacio ${espacio.id} 
                                - ${espacio.tipoZona} 
                                - Empleado: ${espacio.empleadoAsignado}
                            </option>
                        </c:if>
                    </c:forEach>

                </select>

                <br><br>

                <button type="submit">Desasignar</button>

            </form>   






        </div>

        <a class="boton" href="${pageContext.request.contextPath}/admin">Volver</a>

    </body>
</html>