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
            <button type="submit">Consultar datos personales</button>
        </form>
        <form action="estadoGarage" method="post">
            <button type="submit">Ver estado de garage</button>
        </form>
        <form action="salir" method="post">
            <button type="submit">Salir del menú</button>
        </form>
    </div>
</body>
</html>
