<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/estiloSocio.css">
    <title>Menú Socio</title>
</head>
<body>
    <div class="menu-container">
        <h2>SESIÓN DE SOCIO</h2>
        <form action="datosPersonales" method="post">
            <button type="submit">Ver datos personales</button>
        </form>
        <form action="estadoGarage" method="post">
            <button type="submit">Estado de garage</button>
        </form>
        <form action="logout" method="get">
            <button type="submit">Cerrar sesión</button>
        </form>
    </div>
</body>
</html>
