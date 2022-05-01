<%-- 
    Document   : menucomprador
    Created on : Apr 13, 2022, 12:23:53 AM
    Author     : Miguel Oña Guerrero
--%>

<%@page import="swishbay.dto.CategoriaDTO"%>
<%@page import="swishbay.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String servlet = (String)request.getAttribute("servlet");
%>
<!DOCTYPE html>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid">
                <a class="navbar-brand" href="SellerServlet">Vender</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorProductosServlet")) ? "active" : "" %>" href="CompradorProductosServlet">Productos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorEnPujaServlet")) ? "active" : "" %>" href="ProductoEnPujaServlet">Pujas Abiertas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorFavoritosServlet")) ? "active" : "" %>" href="CompradoFavoritosServlet">Favoritos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=(servlet.equals("CompradorCompradosServlet")) ? "active" : "" %>" href="CompradorCompradosServlet">Comprados</a>
                    </li>
                  </ul>
                  <%
                    List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
                        if(categorias != null){                    
                  %>
                  <jsp:include page="productofiltro.jsp"/>
                  <%
                      }
                  %>
                </div>
              </div>
            </nav>
