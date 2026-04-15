<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloUsuarios.css">
        <title>Menú Administrador</title>
    </head>
    <body>

      <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>

        <main class="contenido-principal-admin">
            <div class="menu-container">
                <h2 class="titulo">SESIÓN DE ADMINISTRADOR</h2>
                <form action="${pageContext.request.contextPath}/admin/buscar" method="get">
                    <button type="submit">Buscar y gestionar usuario</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/registrar" method="get">
                    <button type="submit">Registrar Usuario</button>
                </form>
                <form action="${pageContext.request.contextPath}/vehiculo/registrarVehiculo" method="get">
                    <button type="submit">Registrar vehiculo</button>
                </form>
                <form action="${pageContext.request.contextPath}/vehiculo/gestionVehiculo" method="get">
                    <button type="submit">Gestionar vehiculos en garages</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/asignarEmpleado" method="get">
                    <button type="submit">Asignar empleado a garage</button>
                </form>
                <form action="${pageContext.request.contextPath}/logout" method="get">
                    <button class="salir" type="submit">Cerrar sesión</button>
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
