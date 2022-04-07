<%-- 
    Document   : producto
    Created on : 6 abr. 2022, 18:30:04
    Author     : galop
--%>

<%@page import="swishbay.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="swishbay.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <%
       Producto producto = (Producto) request.getAttribute("producto");
       List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");

    %>
    
    <body class="d-flex h-100 text-center text-white bg-dark">
        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <header class="mb-auto">
              <div>
                <h3 class="float-md-start mb-0">SwishBay</h3>
                <nav class="nav nav-masthead justify-content-center float-md-end">
                  <a class="nav-link active" aria-current="page" href="/">Home</a>
                  <a class="nav-link" href="/">Features</a>
                  <a class="nav-link" href="/">Contact</a>
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
                      <a class="nav-link" href="#">Mis pujas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" href="ProductoNuevoEditarServlet">Añadir/modificar producto</a> 
                    </li>
                  
                  </ul>
                </div>
              </div>
            </nav>
            </br>
            </br>
            <h1 class="mb-4">Datos del producto</h1>
            </br>
            <form  method="POST" action="ProductoGuardarServlet">
                <div class="form-group row justify-content-md-center mb-4">
                  <div class="col-sm-4">
                      <input type="text" class="form-control" id="inputId" name="id" hidden="true" value="<%= producto==null? "": producto.getId() %>" >
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputNombre" class="col-sm-1 col-form-label">Nombre:</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" id="inputNombre" name="nombre" value="<%= producto==null? "": producto.getTitulo() %>" >
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputDescripcion" class="col-sm-1 col-form-label">Descripción:</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" id="input" name="descripcion" value="<%= producto==null? "": producto.getDescripcion() %>">
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputPrecio" class="col-sm-1 col-form-label">Precio:</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" id="input" name="precio" value="<%= producto==null? "": producto.getPrecioSalida() %>">
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                    <label for="inputFoto" class="col-sm-1 col-form-label">Foto (URL):</label>
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="input" name="foto" value="<%= producto==null? "": producto.getFoto() %>">
                    </div>
                </div>
                <div class="form-group row justify-content-md-center mb-3">
                    <label  for="inputCategoria" class="col-sm-1 col-form-label">Categoría:</label>
                    <div class="col-sm-4">
                        <select class="form-select" id="categoria" name="categoria">
                            <%
                              for (Categoria c:categorias){
                                String selected = "";
                                
                                if(producto != null && producto.getCategoria().equals(c))
                                    selected="selected";
                               
                            %>     
                                <option <%= selected %> value="<%=c.getNombre()%>"><%=c.getNombre()%> </option>
                           <%  
                              }
                           %>
                        </select>
                    </div>
                </div>
                </br>
                
                <div class="form-group row justify-content-md-center mt-4">
                  <div class="col-sm-10">
                    <button type="submit" class="btn btn-lg btn-secondary fw-bold border-white"><%= producto==null? "Añadir": "Modificar" %></button>
                  </div>
                </div>
            </form>                

            <footer class="mt-auto text-white-50 fixed-bottom">
              <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>
        
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
