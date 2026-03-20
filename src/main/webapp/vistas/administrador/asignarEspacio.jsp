<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zonaGarages.css">
        <title>Menú Administrador</title>
    </head>
    <body>

        <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>

        <div class="formularios-container" style="display: flex; flex-direction: column; align-items: center;">               

            <h1>Zonas de Garage</h1>

            <h2>Confirmar Asignación</h2>

            <p>
                Se guardará <strong>${vehiculo.nombre}</strong>
                en <strong>${vehiculo.tipoVehiculo}</strong>.
            </p>

            <form action="${pageContext.request.contextPath}/admin/confirmarAsignacion" method="post">
                <input type="hidden" name="matricula" value="${vehiculo.matricula}" />


                <a href="${pageContext.request.contextPath}/admin">
                    <button type="button">Cancelar</button>
                </a>

                <button type="submit">Aceptar</button>

            </form>

        </div>


    </body>
</html>