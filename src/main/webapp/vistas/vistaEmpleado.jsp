<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/estiloEmpleado.css">
    <title>Menú Empleado</title>
</head>
<body>
    <div class="menu-container">
        <h2>SESIÓN DE EMPLEADO</h2>
        <form action="datosPersonales" method="post">
            <button type="submit">Consultar datos personales</button>
        </form>
        <form action="vehiculosACargo" method="post">
            <button type="submit">Consultar vehiculos a cargo</button>
        </form>
        <form action="logout" method="get">
            <button type="submit">Cerrar sesión</button>
        </form>
    </div>
</body>
</html>
