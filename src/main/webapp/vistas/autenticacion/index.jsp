<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

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


        <jsp:include page="encabezado.jsp"/>


        <main class="contenido-principal">
            <div class="login-box">
                <h1 class="titulo">Guardería de vehículos de arrastre</h1>
                <h2>Iniciar Sesión</h2>
                <form method="post" action="${pageContext.request.contextPath}/acceso" class="login-form" autocomplete="off">
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
