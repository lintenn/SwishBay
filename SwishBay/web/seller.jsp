<%-- 
    Document   : seller
    Created on : 28 mar. 2022, 12:00:43
    Author     : galop
--%>

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
                </nav>
              </div>
            </header>

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid">
                <a class="navbar-brand" href="#">Comprar</a>
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
                  <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                  </form>
                </div>
              </div>
            </nav>

            <main class="row d-flex justify-content-center mt-4">

              <div class="card mb-3 ms-2 me-2 col-4 position-relative" >
                <div class="row g-0">
                  <h5 class="card-header bg-secondary">Ratón</h5>
                  <div class="col-md-4">
                    <img src="https://m.media-amazon.com/images/I/61UxfXTUyvL._AC_SY450_.jpg" class="rounded-start" style="max-width: 100%;" alt="...">
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title text-dark">50€</h5>
                      <p class="card-text text-dark text-left">Esta es la descripción del producto. Es un ratón Logitech</p>
                      <p class="card-text"><small class="text-muted mb-5 position-absolute bottom-0 start-60 translate-middle-x">Fin de puja en 3 horas</small></p>
                      <a href="#" class="btn btn-primary mb-1 position-absolute bottom-0 start-60 translate-middle-x">Pujar</a>
                    </div>
                  </div>
                </div>
              </div>

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


