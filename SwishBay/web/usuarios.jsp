<%-- 
    Document   : listado
    Created on : Mar 21, 2022, 11:58:59 AM
    Author     : migue
--%>

<%@page import="java.util.List"%>
<%@page import="swishbay.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>
    <body>
        <h1>Listado de usuarios</h1>
        <table>
            <tr>
                <th>NOMBRE</th>
                <th>CORREO</th>                
                <th>APELLIDOS</th>
                <th>CIUDAD</th>
                <th>DOMICILIO</th>
                <th>NACIMIENTO</th>
                <th>SEXO</th>
            </tr>
        <%
            List<Usuario> usuarios = (List)request.getAttribute("usuarios");
            for (Usuario usuario : usuarios) {
        %>    
        <tr>
            <td><%= usuario.getNombre()%></td>
            <td><%= usuario.getCorreo()%></td>
            <td><%= usuario.getApellidos()%></td>
            <td><%= usuario.getCiudad()%></td>
            <td><%= usuario.getDomicilio()%></td>
            <td><%= usuario.getFechaNacimiento()%></td>
            <td><%= usuario.getSexo()%></td>
        </tr>
        
        <%
            }
        %>
        </table>
              
    </body>
</html>
