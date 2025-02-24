<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/estilos.css">
        <link rel="icon" type="image/x-icon" href="/imagenes/favicon.ico">
        <link rel="shortcut icon" type="image/x-icon" href="/imagenes/favicon.ico">
        <title>Guardería Spring Web MVC</title>
    </head>
    <body>
        
            <div class="login-box">
                <h1>Guarderia de coches</h1>
                <h2>Iniciar Sesión</h2>
                <form method="post" action="/acceso" class="login-form">
                        <input type="text" name="usuario" placeholder="Usuario" required>
                        <input type="password" name="contrasena" placeholder="Contraseña" required>
                        <button type="submit">Ingresar</button>
                </form> 
            </div>
    </body>
</html>