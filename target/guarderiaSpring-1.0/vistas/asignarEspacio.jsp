<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zonaGarages.css">
        <title>Menú Administrador</title>
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

        <h1>Zonas de Garage</h1>

        <h2>Confirmar Asignación</h2>

        <p>
            Se guardará <strong>${vehiculo.nombre}</strong>
            en <strong>${vehiculo.tipoVehiculo}</strong>.
        </p>

        <form action="${pageContext.request.contextPath}/admin/confirmarAsignacion" method="post">
            <input type="hidden" name="matricula" value="${vehiculo.matricula}" />

            
            <a href="${pageContext.request.contextPath}/admin">
                <button type="button">Cancelar</button>
            </a>
                
            <button type="submit">Aceptar</button>
            
        </form>




    </body>
</html>