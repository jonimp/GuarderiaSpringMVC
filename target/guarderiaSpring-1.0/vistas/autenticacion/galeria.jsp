<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/servicios.css">
    <title>Servicios</title>
</head>

<body>
    <main>
        <header class="banda-superior">
            <div class="contendor-cabecera">
                <nav class="menu-principal">
                    <div class="lista-menu">
                        <a href="${pageContext.request.contextPath}/" class="enlace-menu">Inicio</a>
                        <a href="${pageContext.request.contextPath}/servicios" class="enlace-menu">Servicios</a>
                        <a href="${pageContext.request.contextPath}/galeria" class="enlace-menu">Galería</a>
                        <a href="${pageContext.request.contextPath}/contacto" class="enlace-menu">Contacto</a>
                    </div>
                </nav>
            </div>
        </header>

        <!-- Contenedor con 9 fotos (3x3) -->
        <div class="contenedor-fotos">
            <!-- Fila 1 -->
            <div class="foto">
                <img src="imagenes/1.jpg" alt="Cochera">
                
            </div>
            <div class="foto">
                <img src="imagenes/2.jpg" alt="Proveeduría">
                
            </div>
            <div class="foto">
                <img src="imagenes/3.jpg" alt="Sanitarios">
                
            </div>

            <!-- Fila 2 -->
            <div class="foto">
                <img src="imagenes/4.jpg" alt="Servicio 4">
                
            </div>
            <div class="foto">
                <img src="imagenes/5.jpg" alt="Servicio 5">
                
            </div>
            <div class="foto">
                <img src="imagenes/6.jpg" alt="Servicio 6">
                
            </div>

            <!-- Fila 3 -->
            <div class="foto">
                <img src="imagenes/cochera.jpg" alt="Servicio 7">
                
            </div>
            <div class="foto">
                <img src="imagenes/proveeduria.jpg" alt="Servicio 8">
                
            </div>
            <div class="foto">
                <img src="imagenes/sanitarios.jpg" alt="Servicio 9">
                
            </div>
        </div>

    </main>
</body>

</html>