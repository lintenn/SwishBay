<%-- 
    Document   : prueba.jsp
    Created on : 28-mar-2022, 0:41:20
    Author     : Luis
--%>

<%@page import="swishbay.entity.TipoUsuario"%>
<%@page import="swishbay.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    //TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SwishBay</title>
    </head>
    <body>
        <h1>¡Bienvenido, <%= user.getNombre() + " " + user.getApellidos() %>!</h1>
        <p>Eres <%= user.getRol().getNombre() %></p>
    </body>
</html>
