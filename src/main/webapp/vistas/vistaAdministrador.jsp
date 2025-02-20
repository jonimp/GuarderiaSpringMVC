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
        <h2>Opciones de Administrador</h2>
        <form action="altaSocio" method="post">
            <button type="submit">Dar alta socio</button>
        </form>
        <form action="altaEmpleado" method="post">
            <button type="submit">Dar alta empleado</button>
        </form>
        <form action="altaAdministrador" method="post">
            <button type="submit">Dar alta administrador</button>
        </form>
        <form action="listarUsuarios" method="get">
            <button type="submit">Listar usuarios</button>
        </form>
        <form action="bajaUsuario" method="post">
            <button type="submit">Dar baja usuario</button>
        </form>
        <form action="gestionarVehiculosGarages" method="get">
            <button type="submit">Gestionar vehiculos y garages</button>
        </form>
        <form action="consultarDisponibilidadGarage" method="get">
            <button type="submit">Consultar disponibilidad garage</button>
        </form>
        <form action="asignarEmpleadoGarage" method="post">
            <button type="submit">Asignar empleado a garage</button>
        </form>
        <form action="salir" method="post">
            <button type="submit">Salir del menú</button>
        </form>
    </div>
</body>
</html>

