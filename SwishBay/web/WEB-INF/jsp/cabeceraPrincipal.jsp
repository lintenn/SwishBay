<%-- 
    Document   : cabeceraPrincipal
    Created on : 12-abr-2022, 20:29:59
    Author     : Luis
--%>

<%@page import="swishbay.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario)session.getAttribute("usuario");
    String home = "UsuarioServlet";
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
            <img class="float-md-start mb-3" src="https://raw.githubusercontent.com/lintenn/SwishBay/main/img/SwishBay_logo_white.png" alt="" width="15%" height="15%"/>
        </a>
        <nav class="nav nav-masthead navbar-dark justify-content-center float-md-end">
            <span class="navbar-text me-2">
                Bienvenido/a, <%= user.getNombre() %> <%= user.getApellidos() %>
            </span>
              <div class="nav-item">
                <a class="nav-link link-light active" href="LogoutServlet">Cerrar sesión</a>
              </div>
        </nav>
    </div>
</header>
