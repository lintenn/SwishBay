<%-- 
    Document   : usuario
    Created on : 10-abr-2022, 2:38:08
    Author     : Luis
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
    <%
        List<Categoria> categorias = (List)request.getAttribute("categorias");
        
        Usuario usuario = (Usuario)request.getAttribute("usuario");
        String status = (String) request.getAttribute("status");
    %>
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
                      <a class="nav-link active" aria-current="page" href="UsuarioServlet"> Usuarios</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="ProductoServlet">Productos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="CategoriaServlet">Categorías</a>
                    </li>
                    
                  </ul>
                </div>
              </div>
            </nav>
            
            <br/>
            <h1 class="mb-2">Datos del usuario</h1>
            <br/>
            
            <form  method="POST" action="UsuarioGuardarServlet">
                <% if (status != null) {%>
                    <div class="form-group row justify-content-center" style="height: 50px;">
                        <div class=" alert alert-danger col-sm-4"><%=status%></div>
                    </div>
                <% } %>
                <div class="form-group row justify-content-md-center mb-4">
                  <div class="col-sm-4">
                      <input type="hidden" class="form-control" id="inputId" name="id" value="<%= usuario==null? "": usuario.getId() %>" >
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputNombre" class="col-sm-1 col-form-label">Nombre:</label>
                  <div class="col-sm-4">
                    <input type="text" maxlength="45" class="form-control" id="inputNombre" name="nombre" required="" autofocus="" value="<%= usuario==null? "": usuario.getNombre() %>" >
                  </div>
                  *
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputApellidos" class="col-sm-1 col-form-label">Apellidos:</label>
                  <div class="col-sm-4">
                    <input type="text" maxlength="45" class="form-control" id="inputApellidos" name="apellidos" required="" value="<%= usuario==null? "": usuario.getApellidos() %>" >
                  </div>
                  *
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputEmail" class="col-sm-1 col-form-label">Email:</label>
                  <div class="col-sm-4">
                    <input type="email" maxlength="45" class="form-control" id="inputEmail" name="correo" required="" value="<%= usuario==null? "": usuario.getCorreo()%>" >
                  </div>
                  *
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputPassword" class="col-sm-1 col-form-label">Contraseña:</label>
                  <div class="col-sm-4">
                    <input type="password" minlength="6" maxlength="45" class="form-control" id="inputPassword" name="password" required="" value="<%= usuario==null? "": usuario.getCorreo()%>" >
                  </div>
                  *
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputDomicilio" class="col-sm-1 col-form-label">Domicilio:</label>
                  <div class="col-sm-4">
                    <input type="text" maxlength="100" class="form-control" id="inputDomicilio" name="domicilio" value="<%= usuario==null? "": usuario.getDomicilio() %>" >
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputCiudad" class="col-sm-1 col-form-label">Ciudad:</label>
                  <div class="col-sm-4">
                    <input type="text" maxlength="45" class="form-control" id="inputCiudad" name="ciudad" value="<%= usuario==null? "": usuario.getCiudad() %>" >
                  </div>
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputFechaNacimiento" class="col-sm-2 col-form-label">Fecha de nacimiento: </label>
                  <div class="col-sm-4">
                    <input type="date" class="form-control" id="inputFechaNacimiento" style="height: 40px" name="fechaNacimiento" value=<%= usuario==null? "": (new SimpleDateFormat("yyyy-MM-dd")).format(usuario.getFechaNacimiento()) %>>
                  </div>
                  &nbsp;
                </div>
                <div class="form-group row justify-content-md-center mb-4">
                  <label for="inputSaldo" class="col-sm-1 col-form-label">Saldo:</label>
                  <div class="col-sm-4">
                    <input type="text" class="form-control" id="inputSaldo" name="saldo" value="<%= usuario==null? "": usuario.getSaldo() %>" required>
                  </div>
                  *
                </div>
                <br/>
                <label for="inputGender" class="form-label">Sexo:</label>
                <div class="d-flex align-center justify-content-center">
                    <div class="form-check mx-1">
                        <input id="masc" name="sexo" value="masc" type="radio" class="form-check-input" <%= usuario==null? "checked":(usuario.getSexo().equals("masc")? "checked":"") %> required=""/>
                      <label class="form-check-label" for="masc">Masculino</label>
                    </div>
                    <div class="form-check mx-1">
                      <input id="fem" name="sexo" value="fem" type="radio" class="form-check-input" <%= usuario==null? "":(usuario.getSexo().equals("fem")? "checked":"") %> required=""/>
                      <label class="form-check-label" for="fem">Femenino</label>
                    </div>
                </div>
                <%
                    String strTipo = usuario==null? "":usuario.getTipoUsuario().getTipo();
                %>
                <br/>
                <label for="inputTipo" class="form-label">Tipo de usuario:</label>
                <div class="d-flex align-center justify-content-center">
                    <div class="form-check mx-1">
                        <input id="administrador" name="tipo" value="administrador" type="radio" class="form-check-input" <%= usuario==null? "checked":(usuario.getTipoUsuario().getTipo().equals("administrador")? "checked":"") %> required=""/>
                      <label class="form-check-label" for="administrador">Administrador</label>
                    </div>
                    <div class="form-check mx-1">
                      <input id="compradorvendedor" name="tipo" value="compradorvendedor" type="radio" class="form-check-input" <%= usuario==null? "":(usuario.getTipoUsuario().getTipo().equals("compradorvendedor")? "checked":"") %> required=""/>
                      <label class="form-check-label" for="compradorvendedor">Comprador/Vendedor</label>
                    </div>
                    <div class="form-check mx-1">
                      <input id="marketing" name="tipo" value="marketing" type="radio" class="form-check-input" <%= usuario==null? "":(usuario.getTipoUsuario().getTipo().equals("marketing")? "checked":"") %> required=""/>
                      <label class="form-check-label" for="marketing">Marketing</label>
                    </div>
                </div>
                
                <br/>
                <label for="inputCategory" class="form-label">Categorías preferidas:</label>
                <%
                    for (Categoria categoria : categorias) {
                        String checked = "";
                        if (usuario != null && !categoria.getUsuarioList().isEmpty() && categoria.getUsuarioList().contains(usuario)) {
                            checked = "checked";
                        }
                %>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="categoria<%=categoria.getId()%>" name="categoria" <%= checked %> value="<%= categoria.getId() %>"/>
                  <label class="custom-control-label" for="categoria<%=categoria.getId()%>"><%=categoria.getNombre()%></label>
                </div>
                
                <% } %>
                <br/>
                
                <div class="form-group row justify-content-md-center mt-2">
                  <div class="col-sm-10">
                    <button type="submit" class="btn btn-lg btn-success fw-bold border-white mx-2"><%= usuario==null? "Añadir": "Modificar" %></button>
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
