<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Datos de usuario</title> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloMostrarDatos.css">
    </head>                                    
    <body>
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
            <div>
                <h1 class="titulo">DATOS DE USUARIO</h1>
                <div class="dato-linea">
                    <div class="dato-header">
                        <i class="fas fa-user"></i>
                        <span class="dato-label">Usuario:</span>
                    </div>
                    <div class="dato-content">
                        ${usuario.usuario}
                    </div>
                </div>

                <c:choose>

                    <%-- ADMIN --%>
                    <c:when test="${usuario.tipo == 'administrador'}">
                        
                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-id-card"></i>
                                <span class="dato-label">Nombre:</span>
                            </div>
                            <div class="dato-content">
                                ${usuario.nombre}
                            </div>
                        </div>
                            
                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-user-shield"></i>
                                <span class="dato-label">Tipo:</span>
                            </div>
                            <div class="dato-content">
                                Administrador
                            </div>
                        </div>
                    </c:when>


                    <%-- EMPLEADO --%>
                    <c:when test="${usuario.tipo == 'empleado'}">

                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-id-card"></i>
                                <span class="dato-label">Nombre:</span>
                            </div>
                            <div class="dato-content">
                                ${usuario.nombre}
                            </div>
                        </div>

                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-home"></i>
                                <span class="dato-label">Direccion:</span>
                            </div>
                            <div class="dato-content">
                                ${usuario.direccion}
                            </div>
                        </div>

                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-phone"></i>
                                <span class="dato-label">Telefono:</span>
                            </div>
                            <div class="dato-content">
                                ${usuario.telefono}
                            </div>
                        </div>

                    </c:when>


                    <%-- SOCIO --%>
                    <c:when test="${usuario.tipo == 'socio'}">

                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-id-card"></i>
                                <span class="dato-label">Nombre:</span>
                            </div>
                            <div class="dato-content">
                                ${usuario.nombre}
                            </div>
                        </div>

                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-home"></i>
                                <span class="dato-label">Direccion:</span>
                            </div>
                            <div class="dato-content">
                                ${usuario.direccion}
                            </div>
                        </div>

                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-phone"></i>
                                <span class="dato-label">Telefono:</span>
                            </div>
                            <div class="dato-content">
                                ${usuario.telefono}
                            </div>
                        </div>

                    </c:when>

                </c:choose>

                <div>
                    <a href="${pageContext.request.contextPath}/admin/buscar" class="boton-retroceso">Volver</a>
                </div>
            </div>
        </main>
    </body>

</html>