<%-- 
    Document   : puja
    Created on : 8 abr. 2022, 18:08:38
    Author     : galop
--%>

<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.DateFormat"%>
<%@page import="swishbay.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Puja</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

    </head>
    <%
       Producto producto = (Producto) request.getAttribute("producto");
       String status = (String) request.getAttribute("status");
       Format f = new SimpleDateFormat("yyyy-MM-dd");
       String str = f.format(producto.getFinPuja());
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
                      <a class="nav-link" href="ProductoNuevoEditarServlet">Nuevo producto</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" href="ProductoNuevoEditarServlet">Añadir/modificar puja</a> 
                    </li>
                  
                  </ul>
                </div>
              </div>
            </nav>
            </br>
            </br>
            <h1 class="mb-2"><%=producto.getTitulo() %></h1>
            </br>
            <form  method="POST" action="EnPujaGuardarServlet">
                <div class="form-group row justify-content-md-center mb-4">
                  <div class="col-sm-4">
                      <input type="text" class="form-control" id="inputId" name="id" hidden="true" value="<%= producto==null? "": producto.getId() %>" >
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputNombre" class="col-sm-2 col-form-label">Precio de salida:</label>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="inputNombre" name="precio" value="<%= producto==null? "": producto.getPrecioSalida() %>" >
                  </div>
                  
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputDescripcion" class="col-sm-2 col-form-label">Fecha de fin: </label>
                  <div class="col-sm-3">
                    <input type="date" class="form-control" style="height: 40px" name="time" value=<%= producto.getEnPuja()==0? "2022-07-01": str %>>
                  </div>
                </div>
                <%
                    if(status != null){
                %>
                <div class="form-group row justify-content-center">
                    <div class=" alert alert-danger col-sm-3"><%=status%></div>
                </div>
                <% }
                %>
              
                </br>
                
                <div class="form-group row justify-content-md-center mt-2">
                  <div class="col-sm-10">
                    <button type="submit" class="btn btn-lg btn-success fw-bold border-white mx-2">Confirmar</button>
                    <a href="SellerServlet" class="btn btn-lg btn-secondary fw-bold border-white mx-2">Cancelar</a>
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
