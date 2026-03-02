<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloContacto.css">
        <title>Contacto - Guardería Spring Web MVC</title>
    </head>
    <body>
        
        <header class="banda-superior">
            <div class="contendor-cabecera">
                <nav class="menu-principal">
                    <div class="lista-menu">
                        <a href="${pageContext.request.contextPath}/" class="enlace-menu">Inicio</a>
                        <a href="#" class="enlace-menu">Servicios</a>
                        <a href="#" class="enlace-menu">Galería</a>
                        <a href="${pageContext.request.contextPath}/contacto" class="enlace-menu">Contacto</a>
                    </div>
                </nav>
            </div>    
        </header>
        
        <main class="contenido-principal">
            <div class="contact-box">
                <h1 class="titulo">Dejanos tu mensaje</h1>
                <h2>Contacto</h2>
                <form method="post" action="/enviar-contacto" class="contact-form" autocomplete="off">
                    <input type="text" name="nombre" placeholder="Nombre" required>
                    <input type="email" name="email" placeholder="Correo electrónico" required>
                    <textarea name="mensaje" placeholder="Mensaje" rows="5" required></textarea>
                    <button type="submit">Enviar mensaje</button>
                </form>
            </div>
        </main>
        
    </body>
</html>