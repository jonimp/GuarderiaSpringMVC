<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/estiloAdmin.css">
    <title>Menú Administrador</title>
</head>
<body>
    <div class="menu-container">
        <h2>SESIÓN DE ADMINISTRADOR</h2>
        <form action="/buscar" method="get">
            <button type="submit">Buscar usuario</button>
        </form>
        <form action="alta" method="post">
            <button type="submit">Registrar Usuario</button>
        </form>
        <form action="baja" method="post">
            <button type="submit">Eliminar Usuario</button>
        </form>
        <form action="garageDisponible" method="get">
            <button type="submit">Ver disponibilidad garages</button>
        </form>
        <form action="gestionVehiculo" method="get">
            <button type="submit">Gestionar vehiculos y garages</button>
        </form>
        <form action="asignarAGarage" method="post">
            <button type="submit">Asignar empleado a garage</button>
        </form>
        <form action="logout" method="get">
            <button type="submit">Cerrar sesión</button>
        </form>
    </div>
</body>
</html>
