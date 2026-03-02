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

        <div class="garage-container">
            <!-- Zona Motorhome -->
            <div class="zona-garage">
                <h3>Zona Motorhome</h3>
                <div class="indicador-capacidad">
                    <div class="barra-progreso" style="width: ${estadoMotorhome.porcentaje}%"></div>
                    <span class="texto-capacidad">${estadoMotorhome.ocupados}/${estadoMotorhome.total}</span>
                </div>
                <div class="espacios-grid">
                    <c:forEach var="espacio" items="${motorhomes}">
                        <div class="espacio ${espacio.ocupado ? 'ocupado' : 'libre'}">
                            ${espacio.numeroEspacio}
                        </div>
                    </c:forEach>
                </div>
            </div>



            <!-- Zona Casa Rodante -->
            <div class="zona-garage">
                <h3>Zona Casa Rodante</h3>
                <div class="indicador-capacidad">
                    <div class="barra-progreso" style="width: ${estadoCasaRodante.porcentaje}%"></div>
                    <span class="texto-capacidad">${estadoCasaRodante.ocupados}/${estadoCasaRodante.total}</span>
                </div>
                <div class="espacios-grid">
                    <c:forEach var="espacio" items="${casaRodante}">
                        <div class="espacio ${espacio.ocupado ? 'ocupado' : 'libre'}">
                            ${espacio.numeroEspacio}
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Zona Trailer -->
            <div class="zona-garage">
                <h3>Zona Trailer</h3>
                <div class="indicador-capacidad">
                    <div class="barra-progreso" style="width: ${estadoTrailer.porcentaje}%"></div>
                    <span class="texto-capacidad">${estadoTrailer.ocupados}/${estadoTrailer.total}</span>
                </div>
                <div class="espacios-grid">
                    <c:forEach var="espacio" items="${trailer}">
                        <div class="espacio ${espacio.ocupado ? 'ocupado' : 'libre'}">
                            ${espacio.numeroEspacio}
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>


        <form method="get" action="${pageContext.request.contextPath}/admin/gestionVehiculo">

            <label>Seleccionar Socio:</label>
            <select name="dni" onchange="this.form.submit()">
                <option value="">-- Seleccionar --</option>

                <c:forEach var="socio" items="${socios}">
                    <option value="${socio.dni}"
                            ${dniSeleccionado == socio.dni ? 'selected' : ''}>
                        ${socio.nombre} - ${socio.dni}
                    </option>
                </c:forEach>

            </select>
        </form>


        <c:if test="${not empty vehiculos}">

            <form method="get" action="${pageContext.request.contextPath}/admin/asignarEspacio">

                <input type="hidden" name="dni" value="${dniSeleccionado}">

                <label>Seleccionar Vehículo:</label>
                <select name="matricula">
                    <c:forEach var="vehiculo" items="${vehiculos}">
                        <option value="${vehiculo.matricula}">
                            ${vehiculo.matricula} - ${vehiculo.tipoVehiculo}
                        </option>
                    </c:forEach>
                </select>

                <button type="submit">Asignar Espacio</button>

            </form>

        </c:if>




    </body>
</html>