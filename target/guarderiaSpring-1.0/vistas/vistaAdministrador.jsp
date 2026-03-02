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

        <header class="banda-superior">
            <div class="contendor-cabecera">
                <nav>
                    <div class="lista-menu">
                        <a href="${pageContext.request.contextPath}/" class="enlace-menu">Inicio</a>
                        <a href="#" class="enlace-menu">Servicios</a>
                        <a href="#" class="enlace-menu">Galería</a>
                        <a href="${pageContext.request.contextPath}/contacto" class="enlace-menu">Contacto</a>
                    </div>
                </nav>
            </div>    
        </header>

        <main class="contenido-principal-admin">
            <div class="menu-container">
                <h2 class="titulo">SESIÓN DE ADMINISTRADOR</h2>
                <form action="${pageContext.request.contextPath}/admin/buscar" method="get">
                    <button type="submit">Buscar y gestionar usuario</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/registrar" method="get">
                    <button type="submit">Registrar Usuario</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/registrarVehiculo" method="get">
                    <button type="submit">Registrar vehiculo</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/gestionVehiculo" method="get">
                    <button type="submit">Gestionar vehiculos en garages</button>
                </form>
                <form action="asignarAGarage" method="post">
                    <button type="submit">Asignar empleado a garage</button>
                </form>
                <form action="logout" method="get">
                    <button class="salir" type="submit">Cerrar sesión</button>
                </form>
            </div>
        </main>
    </body>
</html>
