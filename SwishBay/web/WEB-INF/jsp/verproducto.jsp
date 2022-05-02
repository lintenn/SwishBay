<%-- 
    Document   : verproducto
    Created on : May 2, 2022, 9:42:03 PM
    Author     : Miguel Oña Guerrero
--%>

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
    <body class="d-flex h-100 text-center text-white bg-dark">
        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <jsp:include page="cabecera.jsp" />
            <jsp:include page="menucomprador.jsp" />

            <main class="row d-flex justify-content-center mt-4">
                
            <%
                //List<ProductoDTO> productos = (List)request.getAttribute("productos");
                //UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");
            %>
                <div class="py-5">    
                    <h1>Producto</h1>
                </div>
            </main>
            <footer class="mt-auto fixed-bottom text-white-50">
              <p>© 2022 SwishBay, aplicación web desarrollada por el <a href="/" class="text-white">Grupo 10</a>.</p>
            </footer>
        </div>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>