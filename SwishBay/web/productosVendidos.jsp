<%-- 
    Document   : seller
    Created on : 28 mar. 2022, 12:00:43
    Author     : galop
--%>

<%@page import="java.util.Collections"%>
<%@page import="swishbay.entity.Puja"%>
<%@page import="swishbay.entity.Usuario"%>
<%@page import="swishbay.entity.Producto"%>
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
            <header class="mb-auto">
              <div>
                <h3 class="float-md-start mb-0">SwishBay</h3>
                <nav class="nav nav-masthead justify-content-center float-md-end">
                  <a class="nav-link active" aria-current="page" href="/">Home</a>
                  <a class="nav-link" href="/">Features</a>
                  <a class="nav-link" href="/">Contact</a>
                  <a class="nav-link" href="LogoutServlet">Cerrar sesión</a>
                </nav>
              </div>
            </header>

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid">
                <a class="navbar-brand" href="ProductoServlet">Comprar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link" aria-current="page" href="SellerServlet"> Mis productos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="PujasServlet">Mis pujas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" href="ProductosVendidosServlet">Productos vendidos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="ProductoNuevoEditarServlet">Nuevo producto</a>
                    </li>
                    
                  </ul>
                  <form method="post" class="d-flex" action="SellerServlet">
                    <input class="form-control me-2" type="search" placeholder="Buscar" name="filtro" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar"></>
                  </form>
                </div>
              </div>
            </nav>

            <main class="row d-flex justify-content-center mt-4">

              <%
                List<Producto> productos = (List)request.getAttribute("productos");
                int i=0;
                Usuario user = (Usuario) session.getAttribute("usuario");
                
                Collections.reverse(productos);
                
                for(Producto producto : productos){
                    if(producto.getVendedor().equals(user) && producto.getComprador()!=null){
                       String nombre= producto.getComprador().getNombre() + " " + producto.getComprador().getApellidos();
                       i++;
                       Double p=producto.getPrecioSalida();
                        for (Puja puja: producto.getPujaList()){
                             if(puja.getPrecio()>=p)
                                 p=puja.getPrecio();
                        }
            %>      

              <div class="card mb-3 ms-2 me-2 col-4 position-relative" style="width: 18rem;">
                <div class="row g-4">
                    <h5 class="card-header bg-secondary pt-2"><%= producto.getTitulo() %></h5>
                  <div class="col-sm-12 mt-2">
                    <img src="<%= producto.getFoto() %>" class="card-img-top" style="max-width: 200px;height: 170px" >
                  </div>
                  <div class="col-sm-12 mt-0">
                    <div class="row justify-content-center">
                      <h5 class="card-title text-dark mt-2"><%= p %>€</h5>
                      <p class="card-text text-dark text-center" style="height: 72px"><%= producto.getDescripcion() %></p>
                      <p class="card-text text-dark pb-2 fw-bold">Vendido a: <%= nombre %></p>
                     
                    </div>
                    
                  </div>
                </div>
              </div>
            
            <%
                    }
                }
                if(i==0){
            %>
            <div class="py-5">    
                Lista de productos vacía.
            </div>
            </main>

            <footer class="text-white-50 fixed-bottom">
            <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>

            <%
                }else{
            %>
            </main>

            <footer class="mt-5 text-white-50">
            <p class="pt-5">© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            <%
                }
            %>
            </footer>
        </div>
        
        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>


