<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/estilo.css">
        <link rel="icon" type="image/x-icon" href="/img/casita.png">
        <link rel="shortcut icon" type="image/x-icon" href="/img/casita.png">
        <title>Guardería Spring Web MVC</title>
    </head>
    <body>
        <h1>Primer ingreso a la aplicacion</h1>
        <h2>Crear usuario/administrador</h2>
        <form method="post" action="/consulta">
            <p>
                <select name="color">
                    <c:forEach var="color" items="${colores}" >
                        <option value="${color}">${color}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Enviar">
            </p>
        </form>
    </body>
</html>