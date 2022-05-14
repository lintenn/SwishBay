<%-- 
    Document   : menucomprador
    Created on : May 14, 2022, 9:19:58 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String servlet = (String)request.getAttribute("servlet");
    if(servlet == null){
        servlet = "";
    }
%>
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorProductosServlet")) ? "active" : "" %>" href="CompradorProductosServlet">Productos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorEnPujaServlet")) ? "active" : "" %>" href="CompradorEnPujaServlet">Pujas Abiertas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorFavoritosServlet")) ? "active" : "" %>" href="CompradorFavoritosServlet">Favoritos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorCompradosServlet")) ? "active" : "" %>" href="CompradorCompradosServlet">Comprados</a>
                    </li>
                  </ul>
