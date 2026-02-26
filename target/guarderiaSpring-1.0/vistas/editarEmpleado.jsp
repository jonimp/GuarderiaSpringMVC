<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Editar datos de usuario</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloEditarDatos.css">
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
                <h1 class="titulo">EDITAR DATOS DE EMPLEADO</h1>
                <form action="${pageContext.request.contextPath}/admin/actualizarDatos" method="post">
            
                    <!-- Usuario -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-user"></i>
                            <span class="dato-label">Usuario:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="usuario" value="${usuario.usuario}" class="dato-input">
                        </div>
                    </div>
                    <!-- Contraseña -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-key"></i>
                            <span class="dato-label">Contraseña:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="password" value="${usuario.password}" class="dato-input">
                        </div>
                    </div>
                    <!-- Nombre -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-id-card"></i>
                            <span class="dato-label">Nombre:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="nombre" value="${usuario.nombre}" class="dato-input">
                        </div>
                    </div>
                    
                    <input type="hidden" name="dni" value="${usuario.dni}">    
                        
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-home"></i>
                            <span class="dato-label">Direccion:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="direccion" value="${usuario.direccion}" class="dato-input">
                        </div>
                    </div>
                    <!-- Teléfono -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-phone"></i>
                            <span class="dato-label">Telefono:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="telefono" value="${usuario.telefono}" class="dato-input">
                        </div>
                    </div>
                    <!-- Especialidad -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fa-solid fa-hand"></i>
                            <span class="dato-label">Especialidad:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="especialidad" value="${usuario.especialidad}" class="dato-input">
                        </div>
                    </div>
                    <!-- Botones de acción -->
                    <div class="botones-edicion">
                        <a href="${pageContext.request.contextPath}/panel" class="boton-cancelar">Cancelar</a>
                        <button type="submit" class="boton-aceptar">Aceptar</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>