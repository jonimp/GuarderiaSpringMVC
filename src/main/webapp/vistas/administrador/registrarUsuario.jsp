<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Registrar Usuario</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registrar.css">
    </head>
     <body>
      
        <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>

        <main class="contenido-principal">
            <form action="${pageContext.request.contextPath}/admin/registrar" method="post">
                <div>    
                    <h1 class="titulo">REGISTRAR NUEVO USUARIO</h1>
                    <div class="dato-sel">    
                        <div class="dato-header">
                            <i class="fas fa-user-tag"></i>
                            <span class="dato-label">Tipo de usuario:</span>
                        </div>
                        <div class="dato-content">
                            <select name="tipo" class="selector" 
                                    onchange="window.location.href = '${pageContext.request.contextPath}/admin/registrar?tipo=' + this.value;" 
                                    >
                                <option value="" ${empty usuario.tipo ? 'selected' : ''}>Seleccione un tipo</option>
                                <option value="ADMINISTRADOR" ${usuario.tipo == 'ADMINISTRADOR' ? 'selected' : ''}>Administrador</option>
                                <option value="EMPLEADO" ${usuario.tipo == 'EMPLEADO' ? 'selected' : ''}>Empleado</option>
                                <option value="SOCIO" ${usuario.tipo == 'SOCIO' ? 'selected' : ''}>Socio</option>
                            </select>
                        </div>
                    </div>

                    <!-- Campos comunes a todos los usuarios -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-user"></i>
                            <span class="dato-label">Usuario:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="usuario" class="dato-input" value="${usuario.usuario}" required>
                        </div>
                    </div>
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-key"></i>
                            <span class="dato-label">Contraseña:</span>
                        </div>
                        <div class="dato-content">
                            <input type="password" name="password" class="dato-input" value="${usuario.password}" required>
                        </div>
                    </div>
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-id-card"></i>
                            <span class="dato-label">Nombre:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="nombre" class="dato-input" value="${usuario.nombre}" required>
                        </div>
                    </div>
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fa-solid fa-hashtag"></i>
                            <span class="dato-label">D.N.I:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="dni" class="dato-input" value="${usuario.dni}" required>
                        </div>
                    </div>

                    <!-- Campos específicos según tipo de usuario -->
                    <c:choose>
                        <c:when test="${usuario.tipo == 'EMPLEADO'}">
                            <div class="dato-linea">
                                <div class="dato-header">
                                    <i class="fas fa-home"></i>
                                    <span class="dato-label">Dirección:</span>
                                </div>
                                <div class="dato-content">
                                    <input type="text" name="direccion" class="dato-input" value="${usuario.direccion}" required>
                                </div>
                            </div>
                            <div class="dato-linea">
                                <div class="dato-header">
                                    <i class="fas fa-phone"></i>
                                    <span class="dato-label">Teléfono:</span>
                                </div>
                                <div class="dato-content">
                                    <input type="text" name="telefono" class="dato-input" value="${usuario.telefono}" required>
                                </div>
                            </div>
                            <div class="dato-linea">
                                <div class="dato-header">
                                    <i class="fa-solid fa-hand"></i>
                                    <span class="dato-label">Especialidad:</span>
                                </div>
                                <div class="dato-content">
                                    <input type="text" name="especialidad" class="dato-input" value="${usuario.especialidad}" required>
                                </div>
                            </div>
                        </c:when>

                        <c:when test="${usuario.tipo == 'SOCIO'}">
                            <div class="dato-linea">
                                <div class="dato-header">
                                    <i class="fas fa-home"></i>
                                    <span class="dato-label">Dirección:</span>
                                </div>
                                <div class="dato-content">
                                    <input type="text" name="direccion" class="dato-input" value="${usuario.direccion}" required>
                                </div>
                            </div>
                            <div class="dato-linea">
                                <div class="dato-header">
                                    <i class="fas fa-phone"></i>
                                    <span class="dato-label">Teléfono:</span>
                                </div>
                                <div class="dato-content">
                                    <input type="text" name="telefono" class="dato-input" value="${usuario.telefono}" required>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                    <!-- Botones de acción -->
                    <div class="botones-edicion">
                        <a href="${pageContext.request.contextPath}/admin/inicio" class="boton-cancelar">Cancelar</a>
                        <button type="submit" class="boton-aceptar">Registrar</button>
                    </div>
                </div>        
            </form>        
        </main>
    </body>
</html>