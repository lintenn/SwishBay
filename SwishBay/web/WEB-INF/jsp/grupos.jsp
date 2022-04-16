<%-- 
    Document   : grupos
    Created on : 16 abr 2022, 2:32:52
    Author     : angel
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="swishbay.entity.Grupo"%>
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
                      <a class="nav-link" href="UsuarioCompradorServlet">Panel de usuarios compradores</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="GrupoServlet">Panel de grupos</a>
                    </li>
                    
                  </ul>
                  <form method="post" class="d-flex" action="GrupoServlet">
                    <input class="form-control me-2" type="search" placeholder="Buscar" name="filtro" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar"></>
                  </form>
                </div>
              </div>
            </nav>
        
            <main class="row d-flex justify-content-center mt-4">
            
                <div class="d-flex justify-content-between">
                    <h1>Listado de grupos: </h1>
                    <a href="GrupoNuevoEditarServlet" class="btn btn-lg btn-secondary fw-bold border-white">Crear nuevo grupo</a>
                </div>
                
            <table class="table table-dark table-striped">
                <tr>
                    <th>NOMBRE</th>
                    <th>Creador</th>
                    <th></th>
                </tr>
            <%
                List<Grupo> grupos = (List)request.getAttribute("grupos");
                if(grupos.size() != 0){
                    for (Grupo grupo : grupos) {
            %>    
            <tr>
                <td><%= grupo.getNombre()%></td>
                <td><%= grupo.getMarketing().getNombre()%></td>
                <td><a href="GrupoNuevoEditarServlet?id=<%= grupo.getId() %>" class="btn btn-primary">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                        </svg>
                    </a>
                </td>
            </tr>

            <%
                    }
                } else {
            %>
            </table>
            <h1>No hemos encontrado ningún grupo</h1>
            <%
                }
            %>
            
            </main>
            
            <footer class="text-white-50 fixed-bottom">
            <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>
        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
