<%-- 
    Document   : productos
    Created on : Mar 28, 2022, 11:03:29 AM
    Author     : Miguel Oña Guerrero
--%>

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
            <jsp:include page="cabecera.jsp" />

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid">
                <a class="navbar-brand" href="SellerServlet">Vender</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="#">Productos</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">Link</a>
                    </li>
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
                    </li>
                  </ul>
                  <form method="post" class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search">
                    <input class="btn btn-outline-success" type="submit" value="Buscar"></>
                  </form>
                </div>
              </div>
            </nav>

            <main class="row d-flex justify-content-center mt-4">
                
            <%
                List<Producto> productos = (List)request.getAttribute("productos");
                
                for(Producto producto : productos){
            %> 

                <div class="card mb-3 ms-2 me-2 col-4 position-relative" style="width: 18rem;">
                    <div class="row g-4">
                        <h5 class="card-header bg-secondary pt-2"><%= producto.getTitulo() %></h5>
                        <div class="col-sm-12 mt-2">
                            <img src="<%= producto.getFoto() %>" class="card-img-top" style="max-width: 200px;height: 170px" >
                        </div>
                        <div class="col-sm-12 mt-0">
                            <div class="row justify-content-center">
                                <h5 class="card-title text-dark mt-2"><%= producto.getPrecioSalida() %>€</h5>
                                <p class="card-text text-dark text-center" style="height: 72px"><%= producto.getDescripcion() %></p>
                                <div class="row justify-content-center pb-2 px-0">
                                    <a href="ProductoVerServlet?id=<%=producto.getId() %>" class="btn btn-primary col-5 mx-2">Ver producto</a>
                                    <a href="ProductoFavoritoServlet?id=<%=producto.getId() %>" class="col-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="orange" class="bi bi-star" viewBox="0 0 16 16">
                                            <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
                                        </svg>
                                    </a>
                                        
                                    <a href="ProductoFavoritoServlet?id=<%=producto.getId() %>" class="col-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="orange" class="bi bi-star-fill" viewBox="0 0 16 16">
                                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                                        </svg>
                                    </a>
                                </div>
                            </div>              
                        </div>
                    </div>
                </div>
            
            <%
                }
            %>

            </main>

            <footer class="mt-auto text-white-50 fixed-bottom">
              <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>
        
        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>

