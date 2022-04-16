<%-- 
    Document   : menucomprador
    Created on : Apr 13, 2022, 12:23:53 AM
    Author     : Miguel Oña Guerrero
--%>

<%@page import="swishbay.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                      <a class="nav-link active" aria-current="page" href="ProductoServlet">Productos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="ProductoEnPujaServlet">Pujas Abiertas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="ProductoFavoritoServlet">Favoritos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="ProductoCompradoServlet">Comprados</a>
                    </li>
                    <!--
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                      </ul>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link disabled">Disabled</a>
                    </li> -->
                  </ul>
                  <%
                      String action = (String)request.getAttribute("action");
                  %>
                  <form action="<%=action %>" method="POST" class="d-flex">
                    <div class="col-sm-4">
                        <select class="form-select px-2" id="filtroCategoria" name="filtroCategoria">         
                            <%
                              List<Categoria> categorias = (List) request.getAttribute("categorias");
                              String filtroCategoria = (String) request.getAttribute("selected");
                             %>
                             <option <%= (filtroCategoria==null || filtroCategoria.equals("Categoria")) ? "selected":"" %> value="Categoria">Categoría </option>
                             <%
                              for (Categoria c:categorias){
                                
                            %>     
                                <option <%= (filtroCategoria!=null && filtroCategoria.equals(c.getNombre())) ? "selected":"" %> value="<%=c.getNombre()%>"><%=c.getNombre()%> </option>
                           <%  
                              }
                           %>
                        </select>
                    </div>
                    <input class="form-control me-2 mx-2" type="search" placeholder="Buscar" name="filtro" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar">
                  </form>
                </div>
              </div>
            </nav>
