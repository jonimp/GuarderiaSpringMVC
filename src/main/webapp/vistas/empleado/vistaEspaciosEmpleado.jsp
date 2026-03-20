<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/visual.css">
        <title>Espacios Asignados</title>
    </head>
    <body>
        
        <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>
        
        <div class="contenido-principal">
            <h2>Espacios asignados</h2>

            <c:if test="${empty espacios}">
                <p class="mensaje-vacio">No tenés espacios asignados.</p>
            </c:if>

            <c:forEach var="espacio" items="${espacios}">
                <div class="tarjeta">
                    <strong>Espacio:</strong> ${espacio.id}<br>
                    <strong>Zona:</strong> ${espacio.tipoZona}
                </div>
            </c:forEach>
        </div>
    </body>
</html>