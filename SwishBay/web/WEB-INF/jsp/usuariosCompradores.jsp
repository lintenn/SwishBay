<%-- 
    Document   : usuariosCompradores
    Created on : 16 abr 2022, 1:58:16
    Author     : angel
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="swishbay.dto.UsuarioDTO"%>
<%@page import="java.text.DateFormat"%>
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
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="UsuarioCompradorServlet">Panel de usuarios compradores</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="GrupoServlet">Panel de grupos</a>
                    </li>
                    
                  </ul>
                    <%
                        String tipoFiltro = (String)request.getAttribute("tipoFiltro");
                        String saldoDesde = (String)request.getAttribute("saldoDesde");
                        String saldoHasta = (String)request.getAttribute("saldoHasta");
                    %>
                  <form method="post" class="d-flex" action="UsuarioCompradorServlet">
                      <div class="mt-2 me-2">
                        Sueldo  
                      </div>
                      <div class="mt-2 mx-1">
                        Desde:  
                      </div>
                      <input class="form-control mx-1" type="number" min="0"  style=" width:100px;" id="saldoDesde" name="saldoDesde" value=<%= saldoDesde==null ? "" : saldoDesde  %> ></>
                      <div class="mt-2" style="margin-right: 15px;">€</div>
                      <div class="mt-2">
                        Hasta:  
                      </div>
                      <input class="form-control mx-1" type="number" min="0" style=" width:100px;" id="saldoHasta" name="saldoHasta" value=<%= saldoHasta==null ? "" : saldoHasta  %> ></>
                      <div class="mt-2" style="margin-right: 15px;">€</div>
                      
                      <select class="form-select px-2 me-2" id="filtroUsuariosCompradores" name="filtroUsuariosCompradores">
                          <option value="Nombre" <%= (tipoFiltro != null && tipoFiltro.equals("Nombre")) ? "selected" : "" %>>Nombre</option>
                          <option value="Correo" <%= (tipoFiltro != null && tipoFiltro.equals("Correo")) ? "selected" : "" %>>Correo</option>
                          <option value="Apellidos" <%= (tipoFiltro != null && tipoFiltro.equals("Apellidos")) ? "selected" : "" %>>Apellidos</option>
                          <option value="Ciudad" <%= (tipoFiltro != null && tipoFiltro.equals("Ciudad")) ? "selected" : "" %>>Ciudad</option>
                          <option value="Domicilio" <%= (tipoFiltro != null && tipoFiltro.equals("Domicilio")) ? "selected" : "" %>>Domicilio</option>
                          <option value="Sexo" <%= (tipoFiltro != null && tipoFiltro.equals("Sexo")) ? "selected" : "" %>>Sexo</option>
                      </select>
                    <input class="form-control me-2" type="search" placeholder="Buscar" name="filtro" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar"></>
                  </form>
                </div>
              </div>
            </nav>
        
            <main class="row d-flex justify-content-center mt-4">
            
                <div class="d-flex justify-content-between">
                    <h1>Listado de usuarios compradores: </h1>
                </div>
                
            <table class="table table-dark table-striped">
                <tr>
                    <th>NOMBRE</th>
                    <th>CORREO</th>                
                    <th>APELLIDOS</th>
                    <th>CIUDAD</th>
                    <th>DOMICILIO</th>
                    <th>NACIMIENTO</th>
                    <th>SEXO</th>
                    <th>SALDO</th>
                </tr>
            <%
                List<UsuarioDTO> usuarios = (List)request.getAttribute("usuarios");
                if(usuarios.size() != 0){
                    for (UsuarioDTO usuario : usuarios) {
                        String strFechaNacimiento = DateFormat.getDateInstance(DateFormat.SHORT).format(usuario.getFechaNacimiento());
            %>    
            <tr>
                <td><%= usuario.getNombre()%></td>
                <td><%= usuario.getCorreo()%></td>
                <td><%= usuario.getApellidos()%></td>
                <td><%= usuario.getCiudad()%></td>
                <td><%= usuario.getDomicilio()%></td>
                <td><%= strFechaNacimiento %></td>
                <td><%= usuario.getSexo()%></td>
                <td><%= usuario.getSaldo()%></td>
            </tr>

            <%
                    }

            %>
            </table>
            <%
                }
                else {
            %>
            </table>
            <h1>No hemos encontrado ningún usuario</h1>
            <%
                }
            %>
            
            </main>
            
            <footer class="mt-5 text-white-50">
            <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>
        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
