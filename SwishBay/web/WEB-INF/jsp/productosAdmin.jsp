<%-- 
    Document   : productosAdmin
    Created on : 11-abr-2022, 14:10:09
    Author     : Luis
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="swishbay.dto.CategoriaDTO"%>
<%@page import="swishbay.dto.ProductoDTO"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SwishBay</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body class="d-flex h-100 text-center text-white bg-dark">
        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <jsp:include page="cabeceraPrincipal.jsp" />

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid">
                <a class="navbar-brand" href="UsuarioServlet">Panel de Administrador</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link" href="UsuarioServlet"> Usuarios</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="ProductoAdminServlet">Productos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="CategoriaServlet">Categorías</a>
                    </li>
                    
                  </ul>
                  <form method="post" class="d-flex" action="ProductoAdminServlet">
                    <%
                        String filtroDesde = (String) request.getAttribute("desdeSelected");
                        String filtroHasta = (String) request.getAttribute("hastaSelected");
                    %>
                    
                      <div class="mt-2">
                        Desde:  
                      </div>
                      
                      <input class="form-control mx-1" type="number" min="0"  style=" width:80px;" id="desde" name="desde" value=<%= filtroDesde==null ? "0":Integer.parseInt(filtroDesde)  %> ></>
                      <div class="mt-2" style="margin-right: 15px;">€</div>
                      <div class="mt-2">
                        Hasta:  
                      </div>
                      
                      <input class="form-control mx-1" type="number" min="0" style=" width:80px;" id="hasta" name="hasta" value=<%= filtroHasta==null ? "9999":Integer.parseInt(filtroHasta)  %> ></>
                      <div class="mt-2" style="margin-right: 15px;">€</div>
                    
                    <div class="col-sm-3">
                        <select class="form-select px-2" id="filtroCategoria" name="filtroCategoria">
                            
                            <%
                              List<ProductoDTO> productos = (List)request.getAttribute("productos");
                              List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
                              String filtroCategoria = (String) request.getAttribute("selected");
                             %>
                             <option <%= (filtroCategoria==null || filtroCategoria.equals("Categoria")) ? "selected":"" %> value="Categoria">Categoría </option>
                             <%
                              for (CategoriaDTO c:categorias){
                                
                            %>     
                                <option <%= (filtroCategoria!=null && filtroCategoria.equals(c.getNombre())) ? "selected":"" %> value="<%=c.getNombre()%>"><%=c.getNombre()%> </option>
                           <%  
                              }
                           %>
                        </select>
                    </div>
                    <input class="form-control me-2 mx-2" type="search" placeholder="Buscar por título" name="filtro" aria-label="Search" size="30">
                    <input class="btn btn-outline-success" type="submit" value="Buscar"></>
                  </form>
                </div>
              </div>
            </nav>

            <main class="row d-flex justify-content-center mt-4">
                
                <h1>Listado de productos: </h1>

            <%
                if (productos == null || productos.isEmpty()) {
            %>      
                <div class="py-5 mb-5">    
                    <h2>Sin productos que mostrar.</h2>
                </div>
                
            <% 
                } else {
                    for (ProductoDTO producto : productos) {
            %>
                
              <div class="card mb-3 ms-2 me-2 col-4 position-relative" style="width: 18rem;">
                <div class="row g-4">
                    <h5 class="card-header bg-secondary pt-2"><%= producto.getTitulo() %></h5>
                  <div class="col-sm-12 mt-2">
                    <img src="<%= producto.getFoto() %>" class="card-img-top" style="max-width: 165px;height: 140px" >
                  </div>
                  <div class="col-sm-12 mt-0">
                    <div class="row justify-content-center">
                      <h5 class="card-title text-dark mt-2">Precio de salida: <%= producto.getPrecioSalida() %>€</h5>
                      <p class="card-text text-dark text-center" style="height: 72px">Descripción: <%= producto.getDescripcion() %></p>
                      <p class="card-text text-dark text-center mb-0" >Vendido por: <%= producto.getVendedor().getNombre() + " " + producto.getVendedor().getApellidos() %></p>
                      <%
                            if(producto.getComprador()!=null){
                      %>  
                      <p class="card-text text-dark text-center mb-0">Vendido a: <%= producto.getComprador().getNombre() + " " + producto.getComprador().getApellidos() %></p>
                      <%
                            }
                      %> 
                      <div class="row justify-content-center pb-2 px-0">
                        <% if(producto.getEnPuja()!=0){
                        %>
                        <p class="card-text text-dark text-center mb-0" >Fin subasta: <%= new SimpleDateFormat("dd/MM/yyyy").format(producto.getFinPuja()) %> </p>
                        <% }
                        %>
                        <a href="ProductoAdminEditarServlet?id=<%=producto.getId() %>" class="btn btn-primary col-4 mx-2">Modificar</a>
                        <a href="ProductoAdminBorrarServlet?id=<%=producto.getId() %>" class="btn btn-danger col-2">
                            <svg xmlns="http://www.w3.org/2000/svg"  fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                            <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"></path>
                            </svg>
                        </a>
       
                      </div>
                    </div>
                    
                  </div>
                </div>
              </div>
            
            <%    
                    }
                }
            %>
            </main>

            <footer class="mt-5 text-white-50">
            <p class="pt-5">© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>
        
        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
