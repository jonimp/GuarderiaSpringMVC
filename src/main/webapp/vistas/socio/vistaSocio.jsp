<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloUsuarios.css">
        <title>Menú Socio</title>
    </head>
    <body>

        <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>

        <main class="contenido-principal-soc">
            <div class="menu-container">
                <h2 class="titulo">SESIÓN DE SOCIO</h2>
                <form action="${pageContext.request.contextPath}/consultarDatos" method="get">
                    <button type="submit">Consultar datos personales</button>
                </form>
                <form action="${pageContext.request.contextPath}/socio/misVehiculos" method="get">
                    <button type="submit">Consultar estado de garage</button>
                </form>
                <form action="${pageContext.request.contextPath}/logout" method="get">
                    <button class="salir" type="submit">Cerrar sesion</button>
                </form>
            </div>
        </main>




        <script>
            if (window.history && window.history.pushState) {
                window.history.pushState(null, null, window.location.href);
                window.onpopstate = function () {
                    window.location.href = "${pageContext.request.contextPath}/";
                };
            }
        </script>          







    </body>
</html>