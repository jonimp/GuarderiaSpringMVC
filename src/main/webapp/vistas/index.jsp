<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloIndex.css">
        <link rel="shortcut icon" type="image/x-icon" href="/imagenes/favicon.ico">
        <title>Guardería Spring Web MVC</title>
    </head>
    <body>

        <script>
            window.onload = function () {
                document.querySelector("form").reset();
            };
        </script>


        <header class="banda-superior">
            <div class="contendor-cabecera">


                <nav class="menu-principal">
                    <div class="lista-menu">
                        <a href="${pageContext.request.contextPath}/" class="enlace-menu">Inicio</a>
                        <a href="#" class="enlace-menu">Servicios</a>
                        <a href="#" class="enlace-menu">Galería</a>
                        <a href="#" class="enlace-menu">Contacto</a>
                    </div>
                </nav>



            </div>    
        </header>


        <main class="contenido-principal">
            <div class="login-box">
                <h1 class="titulo">Guardería de vehículos de arrastre</h1>
                <h2>Iniciar Sesión</h2>
                <form method="post" action="/acceso" class="login-form" autocomplete="off">
                    <input type="text" name="usuario" placeholder="Usuario" required>
                    <input type="password" name="password" placeholder="Contraseña" required>
                    <button type="submit">Ingresar</button>
                </form> 
            </div>

            <div class="contenedor-fotos">
                <div class="foto">
                    <img src="imagenes/caravana1.jpg">
                </div>
                <div class="foto">
                    <img src="imagenes/caravana2.jpg">
                </div>
                <div class="foto">
                    <img src="imagenes/motorhome1.jpg">
                </div>
                <div class="foto">
                    <img src="imagenes/motorhome2.jpg">
                </div>
                <div class="foto">
                    <img src="imagenes/treiler1.jpg">
                </div>
                <div class="foto">
                    <img src="imagenes/treiler2.jpg">
                </div>
            </div>

        </main>

    </body>
</html>
