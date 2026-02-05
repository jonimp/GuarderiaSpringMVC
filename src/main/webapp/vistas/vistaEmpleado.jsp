<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/estiloUsuarios.css">
    <title>Menú Empleado</title>
</head>
<body>

    <header class="banda-superior">
        <div class="contendor-cabecera">
            

            <nav>
                <div class="lista-menu">
                    <a href="#" class="enlace-menu">Inicio</a>
                    <a href="#" class="enlace-menu">Servicios</a>
                    <a href="#" class="enlace-menu">Galería</a>
                    <a href="#" class="enlace-menu">Contacto</a>
                </div>
            </nav>

        </div>    
    </header>
        
    <main class="contenido-principal-empl">
        <div class="menu-container">
            <h2 class="titulo">SESIÓN DE EMPLEADO</h2>
            <form action="/buscar" method="get">
                <button type="submit">Consultar datos personales</button>
            </form>
            <form action="alta" method="post">
                <button type="submit">Consultar vehiculos a cargo</button>
            </form>
            <form action="logout" method="get">
                <button type="submit">Cerrar sesion</button>
            </form>
        </div>
    </main>
</body>
</html>