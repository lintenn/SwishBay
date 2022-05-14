<%-- 
    Document   : verproducto
    Created on : May 2, 2022, 9:42:03 PM
    Author     : Miguel Oña Guerrero
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="swishbay.dto.PujaDTO"%>
<%@page import="swishbay.dto.UsuarioDTO"%>
<%@page import="swishbay.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>SwishBay</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <%
        ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
        String error = (String) request.getAttribute("error");
        error = (error == null) ? "" : error;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    %>
    <body class="d-flex h-100 text-center text-white bg-dark">
        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <jsp:include page="cabecera.jsp" />
            <jsp:include page="menucomprador.jsp" />

            <main class="row d-flex justify-content-center mt-2">
                <div class="row col-6 border-bottom"> 
                    
                    <div class="mt-2 col-12 d-flex justify-content-between border-bottom">
                        <h1><%= producto.getTitulo() %></h1>
                        <p class="fs-4"><%= producto.getCategoria().getNombre() %></p>
                    </div>   
                                            
                    <div class="col-5 mt-4 d-flex justify-content-start">
                        <img src="<%= producto.getFoto() %>" class="card-img-top" style="max-width:90% ;height:90%" >
                    </div>
                    
                    <div class="col-7 mt-3">
                        <div class="d-flex justify-content-center">
                            <p class="fs-2"><%= producto.getDescripcion() %></p>
                        </div>
                        <div class="d-flex justify-content-center">
                            <p class="fs-4">Precio de salida por <%= producto.getPrecioSalida()%>€</p>
                        </div>
                        <div class="d-flex justify-content-center">
                            <p class="fs-4">Fin de subasta el <%= formato.format(producto.getFinPuja())%></p>
                        </div>
                        <div class="d-flex justify-content-center">
                            <p class="fs-4">Vendido por <%= producto.getVendedor().getNombre() + " " + producto.getVendedor().getApellidos()%></p>
                        </div>
                    </div>
                 
                </div>
                        
                <%
                    if(producto.getEnPuja() == 0 && producto.getComprador() == null){
                %>
                <div class="row col-9 d-flex justify-content-center mt-2">
                    <h3>Subasta aún no disponible</h3>
                </div>
                <%
                    }else if(producto.getComprador() != null){
                        PujaDTO puja = (PujaDTO) request.getAttribute("puja");
                %>
                <div class="row col-9 d-flex justify-content-center mt-2">
                    <h3>Subasta finalizada</h3>
                    <p class="fs-4">Compraste este producto por <%= puja.getPrecio() %>€ en la fecha <%= formato.format(puja.getFecha()) %></p>
                </div>
                <%
                    }else{
                        List<PujaDTO> pujas = (List) request.getAttribute("pujas");
                %>
                        
                <div class="row col-9 d-flex justify-content-center"> 
                    
                    <div class="mt-2 col-8 d-flex justify-content-between">
                        <%
                            if(pujas.isEmpty()){
                        %>
                        <h2>¡Realiza la primera puja!</h2>
                        <%
                            }else{
                        %>
                        <h2>Pujas: </h2>
                        <%
                            }
                        %>
                        <p class="mt-2"><%= error %></p>
                        <form action="CompradorPujarServlet" method="POST" class="col-4 d-flex justify-content-center">
                            <input type="hidden" name="productoid" value="<%= producto.getId() %>" />                            
                            <div class="col-8 d-flex justify-content-center">
                                <input type="text" class="form-control" name="cantidad" placeholder="Cantidad a pujar">
                            </div>
                            <div class="col-4">
                                <button type="submit" class="btn btn-lg btn-success fw-bold border-white mx-2">Pujar</button>
                            </div>

                        </form>
                    </div>
                    <div class="mt-3 col-8 d-flex justify-content-between">
                        <table class="table table-striped table-dark">
                            <thead>
                                <tr>
                                    <th scope="col"><p class="fs-4">Pujador</p></th>
                                    <th scope="col"><p class="fs-4">Cantidad</p></th>
                                    <th scope="col"><p class="fs-4">Fecha</p></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for(PujaDTO puja : pujas){
                                %>
                                <tr>     
                                    <td><%= puja.getComprador().getNombre() + " " + puja.getComprador().getApellidos() %></td>
                                    <td><%= puja.getPrecio() %>€</td>
                                    <td><%= formato.format(puja.getFecha()) %></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
                
                <%
                    }
                %>

            </main>
            <footer class="mt-auto fixed-bottom text-white-50">
              <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>