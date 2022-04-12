<%-- 
    Document   : cabeceraPrincipal
    Created on : 12-abr-2022, 20:29:59
    Author     : Linten
--%>

<%@page import="swishbay.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario)session.getAttribute("usuario");
    String home = "ProductoServlet";
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    } else {
        if (user.getTipoUsuario().getTipo().equals("administrador")) {
            home = "UsuarioServlet";
        } else if (user.getTipoUsuario().getTipo().equals("compradorvendedor")) {
            home = "ProductoServlet";
        } else if (user.getTipoUsuario().getTipo().equals("marketing")) {
            home = "prueba.jsp";
        }
    }
%>
<header class="mb-auto">
    <div>
        <a href="<%= home %>">
            <img class="float-md-start mb-0" src="https://raw.githubusercontent.com/lintenn/SwishBay/main/img/SwishBay_logo_white.png" alt="" width="120" height="50">
        </a>
        <nav class="nav nav-masthead justify-content-center float-md-end">
            <a class="nav-link active" aria-current="page" href="<%= home %>">Home</a>
            <a class="nav-link" href="/">Features</a>
            <a class="nav-link" href="/">Contact</a>
            <a class="nav-link" href="LogoutServlet">Cerrar sesión</a>
        </nav>
    </div>
</header>
