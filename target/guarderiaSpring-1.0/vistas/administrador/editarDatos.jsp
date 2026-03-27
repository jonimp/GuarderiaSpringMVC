<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Editar Usuario</title>
    </head>
    <body>

        <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>

        <c:choose>

            <c:when test="${usuario.tipo == 'ADMINISTRADOR'}">
                <jsp:include page="/vistas/administrador/editarAdmin.jsp"/>
            </c:when>

            <c:when test="${usuario.tipo == 'EMPLEADO'}">
                <jsp:include page="/vistas/administrador/editarEmpleado.jsp"/>
            </c:when>

            <c:when test="${usuario.tipo == 'SOCIO'}">
                <jsp:include page="/vistas/administrador/editarSocio.jsp"/>
            </c:when>

        </c:choose>

    </body>
</html>
