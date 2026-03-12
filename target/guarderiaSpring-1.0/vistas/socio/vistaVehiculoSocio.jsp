<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visual.css">
        <title>Mis Vehículos</title>
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

        <div class="contenido-principal">
            <h2>Mis vehículos</h2>

            <c:if test="${empty vehiculos}">
                <p class="mensaje-vacio">No tenés vehículos registrados.</p>
            </c:if>

            <c:forEach var="vehiculo" items="${vehiculos}">
                <div class="tarjeta">
                    <strong>Matrícula:</strong> ${vehiculo.matricula}<br>
                    <strong>Tipo:</strong> ${vehiculo.tipoVehiculo}
                </div>
            </c:forEach>
        </div>
    </body>
</html>