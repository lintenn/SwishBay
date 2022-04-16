<%-- 
    Document   : crearGrupo
    Created on : 16 abr 2022, 3:29:13
    Author     : angel
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="swishbay.entity.Usuario"%>
<%@page import="swishbay.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Usuario</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    </head>
    <body class="d-flex h-100 text-center text-white bg-dark">
        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <jsp:include page="cabeceraPrincipal.jsp" />
            
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link" href="UsuarioCompradorServlet">Panel de usuarios compradores</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="GrupoServlet">Panel de grupos</a>
                    </li>
                    
                  </ul>
                </div>
              </div>
            </nav>
            
            <br/>
            <h1 class="mb-2">Datos del grupo</h1>
            <br/>
            
            <form  method="POST" action="GrupoGuardarServlet">
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputNombre" class="col-sm-1 col-form-label">Nombre:</label>
                  <div class="col-sm-4">
                    <input type="text" maxlength="45" class="form-control" id="inputNombre" name="nombre" required="" autofocus="" value="" >
                  </div>
                  *
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                
                <div class="form-group row justify-content-md-center mt-2">
                  <div class="col-sm-10">
                    <button type="submit" class="btn btn-lg btn-success fw-bold border-white mx-2">Añadir</button>
                    <a href="UsuarioServlet" class="btn btn-lg btn-secondary fw-bold border-white mx-2">Cancelar</a>
                  </div>
                </div>
            </form>
            <br/>
            
            
            
            <footer class="text-white-50 fixed-bottom">
            <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>
        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
