<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Registrar vehiculo</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registrar.css">
    </head>

    <body>

        <jsp:include page="/vistas/autenticacion/encabezado.jsp"/>

        <main class="contenido-principal">
            <form action="${pageContext.request.contextPath}/admin/registarVehiculo" method="post">
                <div>
                    <h1 class="titulo">Registrar vehiculo</h1>
                    <div class="dato-sel">
                        <div class="dato-header">
                            <i class="fa-solid fa-circle-user"></i>    
                            <span class="dato-label">Socio usuario:</span>    
                        </div>        
                        <div class="dato-content">    
                            <select name="dniSocio" class="selector">
                                <c:forEach var="s" items="${socios}">
                                    <option value="${s.dni}">
                                        ${s.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Matricula -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fa-solid fa-hashtag"></i>
                            <span class="dato-label">Matricula:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="matricula" value="" class="dato-input">
                        </div>
                    </div>

                    <!-- Nombre -->
                    <div class="dato-linea">
                        <div class="dato-header">
                            <i class="fas fa-id-card"></i>
                            <span class="dato-label">Nombre:</span>
                        </div>
                        <div class="dato-content">
                            <input type="text" name="nombre" value="" class="dato-input">
                        </div>
                    </div>

                    <!-- Tipo de vehiculo -->
                    <div class="dato-sel">
                        <div class="dato-header">
                            <i class="fa-solid fa-truck"></i>
                            <span class="dato-label">Tipo de vehículo:</span>
                        </div>
                        <div class="dato-content">
                            <select name="tipoVehiculo" class="selector">
                                <!--class="selector" onchange="this.form.submit();" -->
                                <option value="">Seleccionar tipo de vehiculo</option>
                                <option value="MOTORHOME">Motorhome</option>
                                <option value="CASA_RODANTE">Casa rodante</option>
                                <option value="TRAILER">Trailer</option>
                            </select>
                        </div>
                    </div>

                    <!-- Botones de acción -->
                    <div class="botones-edicion">
                        <a href="${pageContext.request.contextPath}/admin" class="boton-cancelar">Cancelar</a>
                        <button type="submit" class="boton-aceptar">Aceptar</button>
                    </div>
                </div>
            </form>        
        </main>
    </body>