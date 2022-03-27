<%-- 
    Document   : register
    Created on : 26-mar-2022, 21:20:48
    Author     : Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>Register</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }
      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
        
        html,
        body {
          height: 100%;
        }
        body {
          display: -ms-flexbox;
          display: flex;
          -ms-flex-align: center;
          align-items: center;
          padding-top: 40px;
          padding-bottom: 40px;
          background-color: #f5f5f5;
        }
        .form-signin {
          width: 100%;
          max-width: 530px;
          padding: 15px;
          margin: auto;
        }
        .form-signin .checkbox {
          font-weight: 400;
        }
        .form-signin .form-control {
          position: relative;
          box-sizing: border-box;
          height: auto;
          padding: 10px;
          font-size: 16px;
        }
        .form-signin .form-control:focus {
          z-index: 2;
        }
        .form-signin input[type="email"] {
          margin-bottom: -1px;
          border-bottom-right-radius: 0;
          border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
          margin-bottom: 10px;
          border-top-left-radius: 0;
          border-top-right-radius: 0;
        }
      }
      
      .botones{
          display: flex;
          padding: 0 10px;
      }
    </style>
    
    <!-- Custom styles for this template -->    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  </head>
  <body class="text-center">
      <main class="form-signin">
        <form class="border border-light p-5" action="LoginServlet">
            <img class="mb-4" src="https://raw.githubusercontent.com/lintenn/SwishBay/main/img/SwishBay_logo_black.png" alt="" width="120" height="50">
            <h1 class="h3 mb-3 fw-normal">Register</h1>
            
            <div class="row g-3">
                <div class="col-sm-6 form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="First name" value required/>
                    <label for="inputFirstName" class="sr-only">First name</label>
                </div>
                <div class="col-sm-6 form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Last name"/>
                    <label for="inputLastName" class="sr-only">Last name</label>
                </div>
            </div>
            <div class="form-floating">
                <input type="email" class="form-control" id="floatingInput" placeholder="Email address"/>
                <label for="inputEmail" class="sr-only">Email address</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password" aria-describedby="defaultRegisterFormPasswordHelpBlock"/>
                <label for="inputPassword" class="sr-only">Password</label>
            </div>
            <small id="defaultPasswordFormPhoneHelpBlock" class="form-text text-muted mb-4">Minimal 8 characters lenght</small>
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput" placeholder="Adressline"/>
                <label for="inputAddressline" class="sr-only">Addressline</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput" placeholder="City"/>
                <label for="inputCity" class="sr-only">City</label>
            </div>
            <label for="inputBirthdate" class="form-label">Birth date</label>
            <div class="form-floating">
                <input type="date" class="form-control" id="floatingInput"/>
                <label for="inputBirthdate" class="sr-only">Birth date</label>
            </div>
            
            <label for="inputGender" class="form-label">Gender</label>
            <div class="d-flex align-center justify-content-center">
                <div class="form-check mx-1">
                  <input id="masc" name="gender" type="radio" class="form-check-input" checked="" required=""/>
                  <label class="form-check-label" for="masc">Masculine</label>
                </div>
                <div class="form-check mx-1">
                  <input id="fem" name="gender" type="radio" class="form-check-input" required=""/>
                  <label class="form-check-label" for="fem">Femenine</label>
                </div>
            </div>
            
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="defaultRegisterFormNewsletter">
                <label class="custom-control-label" for="defaultRegisterFormNewsletter">Subscribe to our newsletter</label>
            </div>
            
            <nav class="botones">
                <input type="submit" style="margin-right: 10px" class="w-100 btn btn-lg btn-primary" value="Register"/>
                <a href="login.jsp" style="margin-left: 10px" class="w-100 btn btn-lg btn-primary">Back</a>
            </nav>
            
            <div class="text-center">
            
            <hr>

            <p>By clicking
                <em>Register</em> you agree to our
                <a href="" target="_blank">terms of service</a> and
                <a href="" target="_blank">terms of service</a>.
            </p>
            </div>
            
            <p class="mt-5 mb-3 text-muted">© 2022, SwishBay</p>
        </form>
      </main>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
    </body>
