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

            <div class="contenedor-fotos">
                <div class="foto">
                    <img src="imagenes/cochera.jpg">
                    <p class="nombre-foto">Cochera</p>
                </div>
                <div class="foto">
                    <img src="imagenes/proveeduria.jpg">
                    <p class="nombre-foto">Proveeduría</p>
                </div>
                <div class="foto">
                    <img src="imagenes/sanitarios.jpg">
                    <p class="nombre-foto">Sanitarios</p>
                </div>
            </div>

        </main>


    </body>
</html>

