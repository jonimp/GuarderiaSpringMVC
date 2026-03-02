<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Registrar Usuario</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloRegistrarUsuario.css">
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
            <div class="form-container">
                <h1 class="titulo">REGISTRAR NUEVO USUARIO</h1>

                <!-- El formulario se envía a sí mismo; cambia la acción a tu servlet -->
                <form action="${pageContext.request.contextPath}/admin/registrar" method="post">
                    <!-- Selector de tipo de usuario con envío automático -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-user-tag"></i>
                            <span class="dato-label">Tipo de usuario:</span>
                        </div>
                        <div class="dato-content">
                            
                            <select name="tipoUsuario" onchange="window.location.href='${pageContext.request.contextPath}/admin/registrar?tipoUsuario=' + this.value;">
                                <option value="" disabled ${empty param.tipoUsuario ? 'selected' : ''}>Seleccione un tipo</option>
                                <option value="administrador" ${param.tipoUsuario == 'administrador' ? 'selected' : ''}>Administrador</option>
                                <option value="empleado" ${param.tipoUsuario == 'empleado' ? 'selected' : ''}>Empleado</option>
                                <option value="socio" ${param.tipoUsuario == 'socio' ? 'selected' : ''}>Socio</option>
                            </select>
                        </div>
                    </div>


                    <!-- TODOS LOS USUARIOS -->
                    <div>
                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-user"></i>
                                <span class="dato-label">Usuario:</span>
                            </div>
                            <div class="dato-content">
                                <input type="text" name="usuario" class="dato-input" value="${param.usuario}" required>
                            </div>
                        </div>
                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-key"></i>
                                <span class="dato-label">Contraseña:</span>
                            </div>
                            <div class="dato-content">
                                <input type="password" name="password" class="dato-input" value="${param.password}" required>
                            </div>
                        </div>
                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fas fa-id-card"></i>
                                <span class="dato-label">Nombre:</span>
                            </div>
                            <div class="dato-content">
                                <input type="text" name="nombre" class="dato-input" value="${param.nombre}" required>
                            </div>
                        </div>
                        <div class="dato-linea">
                            <div class="dato-header">
                                <i class="fa-solid fa-hashtag"></i>
                                <span class="dato-label">D.N.I:</span>
                            </div>
                            <div class="dato-content">
                                <input type="text" name="dni" class="dato-input" value="${param.dni}" required>
                            </div>
                        </div>
                    </div>

                    <c:choose>
                        
                        <c:when test="${param.tipoUsuario == 'empleado'}">
                            <div class="campos-especificos">
                                <div class="dato-linea">
                                    <div class="dato-header">
                                        <i class="fas fa-home"></i>
                                        <span class="dato-label">Dirección:</span>
                                    </div>
                                    <div class="dato-content">
                                        <input type="text" name="direccion" class="dato-input" value="${param.direccion}" required>
                                    </div>
                                </div>
                                <div class="dato-linea">
                                    <div class="dato-header">
                                        <i class="fas fa-phone"></i>
                                        <span class="dato-label">Teléfono:</span>
                                    </div>
                                    <div class="dato-content">
                                        <input type="text" name="telefono" class="dato-input" value="${param.telefono}" required>
                                    </div>
                                </div>
                                <div class="dato-linea">
                                    <div class="dato-header">
                                        <i class="fa-solid fa-hand"></i>
                                        <span class="dato-label">Especialidad:</span>
                                    </div>
                                    <div class="dato-content">
                                        <input type="text" name="especialidad" class="dato-input" value="${param.especialidad}" required>
                                    </div>
                                </div>
                            </div>
                        </c:when>

                        <%-- SOCIO --%>
                        <c:when test="${param.tipoUsuario == 'socio'}">
                            <div class="campos-especificos">
                                <div class="dato-linea">
                                    <div class="dato-header">
                                        <i class="fas fa-home"></i>
                                        <span class="dato-label">Dirección:</span>
                                    </div>
                                    <div class="dato-content">
                                        <input type="text" name="direccion" class="dato-input" value="${param.direccion}" required>
                                    </div>
                                </div>
                                <div class="dato-linea">
                                    <div class="dato-header">
                                        <i class="fas fa-phone"></i>
                                        <span class="dato-label">Teléfono:</span>
                                    </div>
                                    <div class="dato-content">
                                        <input type="text" name="telefono" class="dato-input" value="${param.telefono}" required>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                    <!-- Botones de acción -->
                    <div class="botones-edicion">
                        <a href="${pageContext.request.contextPath}/admin" class="boton-cancelar">Cancelar</a>
                        <button type="submit" class="boton-aceptar">Registrar</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>