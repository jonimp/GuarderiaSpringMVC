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
        
        <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>

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