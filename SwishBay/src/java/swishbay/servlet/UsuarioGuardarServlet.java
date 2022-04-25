/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.RolUsuarioFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.Categoria;
import swishbay.entity.RolUsuario;
import swishbay.entity.Usuario;

/**
 *
 * @author Luis
 */
@WebServlet(name = "UsuarioGuardarServlet", urlPatterns = {"/UsuarioGuardarServlet"})
public class UsuarioGuardarServlet extends HttpServlet {

    @EJB UsuarioFacade usuarioFacade;
    @EJB CategoriaFacade categoriaFacade;
    @EJB RolUsuarioFacade rolUsuarioFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario user = (Usuario)session.getAttribute("usuario");
        
        if (user == null || user.getRol().getNombre().equals("administrador")) {
            
            String strId, strTipoUsuario, strSaldo;
            String nombre, apellidos, correo, password, domicilio, ciudad, sexo, status = null, goTo = "ProductoServlet";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = null;
            double saldo = 0;

            try {
                fechaNacimiento = formato.parse(request.getParameter("fechaNacimiento"));
            } catch (ParseException ex) {
                System.out.println(ex);
            }

            Date fechaSistema = new Date();
            int edad = fechaSistema.getYear() - fechaNacimiento.getYear();
            Usuario newUser = null, posibleUser = null;
            //byte[] contrasenaCifrada;
            boolean esMenor = edad < 18;
            int mes, dia;
            if (edad == 18) {
                mes = fechaSistema.getMonth() - fechaNacimiento.getMonth();
                if (mes == 0) {
                    dia = fechaSistema.getDay() - fechaNacimiento.getDay();
                    esMenor = dia < 0;
                } else {
                    esMenor = mes < 0;
                }
            }

            strId = request.getParameter("id");
            correo = request.getParameter("correo");
            
            if (strId == null || strId.isEmpty()) { // si estamos añadiendo
                try {
                    posibleUser = usuarioFacade.findByCorreo(correo);
                } catch (EJBException e) {
                    posibleUser = null;
                }
            }
            
            nombre = request.getParameter("nombre");
            apellidos = request.getParameter("apellidos");
            password = request.getParameter("password");
            //contrasenaCifrada = usuarioService.hashPassword(contrasena);
            domicilio = request.getParameter("domicilio");
            ciudad = request.getParameter("ciudad");
            sexo = request.getParameter("sexo");
            strTipoUsuario = request.getParameter("tipo");
            String[] categorias = request.getParameterValues("categoria");

            strSaldo = request.getParameter("saldo");
            if(strSaldo != null && !strSaldo.isEmpty() && !strSaldo.matches("[-+]?\\d*\\.?\\d+")){
                status= "Formato de saldo incorrecto.";
                request.setAttribute("status", status);
                
                request.getRequestDispatcher("UsuarioNuevoEditarServlet").forward(request, response);
                
            } else if (posibleUser != null) {
               status = "El correo introducido ya existe en el sistema";
               request.setAttribute("status", status);
               
               if (user == null) {
                   goTo = "CargarRegisterServlet";
               } else {
                   goTo = "UsuarioNuevoEditarServlet";
               }
               
               request.getRequestDispatcher(goTo).forward(request, response);
            } else if (esMenor) {
               status = "No se permiten usuarios menores de edad";
               request.setAttribute("status", status);
               
               if (user == null) {
                   goTo = "CargarRegisterServlet";
               } else {
                   goTo = "UsuarioNuevoEditarServlet";
               }
               
               request.getRequestDispatcher(goTo).forward(request, response);
            } else {
                
               if (strId == null || strId.isEmpty()) { // Crear nuevo usuario
                   newUser = new Usuario();
               } else {                             // Editar usuario
                   newUser = this.usuarioFacade.find(Integer.parseInt(strId));
               }
               
               newUser.setNombre(nombre);
               newUser.setApellidos(apellidos);
               newUser.setCorreo(correo);
               newUser.setPassword(password);
               newUser.setDomicilio(domicilio);
               newUser.setCiudad(ciudad);
               newUser.setSexo(sexo);
               newUser.setFechaNacimiento(fechaNacimiento);
               
               if (strSaldo != null && !strSaldo.isEmpty()) saldo = Double.parseDouble(strSaldo);
               newUser.setSaldo(saldo);
               
               RolUsuario rol;
               if (strTipoUsuario == null || strTipoUsuario.isEmpty()) { // Crear usuario a registrar
                   //TipoUsuario tipoUsuario = new TipoUsuario(newUser.getId(),"compradorvendedor");
                   //tipoUsuarioFacade.create(tipoUsuario);
                   rol = rolUsuarioFacade.findByNombre("compradorvendedor");
                   
               } else {                                 // Crear/editar usuario desde panel administrador
                   //TipoUsuario tipoUsuario = new TipoUsuario(newUser.getId(),strTipoUsuario);
                   //tipoUsuarioFacade.create(tipoUsuario);
                   rol = rolUsuarioFacade.findByNombre(strTipoUsuario);
                   
               } 
               newUser.setRol(rol);
               
               // Faltarían las categorias...

               if (strId == null || strId.isEmpty()) {    // Crear nuevo usuario
                   usuarioFacade.create(newUser);
               } else {                                   // Editar usuario
                   usuarioFacade.edit(newUser);
               }  
               
               // Cargamos las categorías...
               if (categorias != null && categorias.length > 0) {
                    List<Categoria> categoriaList = newUser.getCategoriaList();
                    //System.out.println(categoriaList.toString());
                    // Borramos las categorias anteriores
                    for (Categoria categoria : categoriaList) {
                        //System.out.println(categoria.toString());
                        List<Usuario> usuariosCategoria = categoria.getUsuarioList();
                        
                        usuariosCategoria.remove(newUser);
                        
                        categoria.setUsuarioList(usuariosCategoria);

                        categoriaFacade.edit(categoria);
                    }
                    // Añadimos al usuario en las nuevas categorías
                    for (String categoriaId : categorias) {
                        Categoria categoria = categoriaFacade.find(Integer.parseInt(categoriaId));

                        List<Usuario> usuariosCategoria = categoria.getUsuarioList();

                        if (!usuariosCategoria.contains(newUser)) usuariosCategoria.add(newUser);

                        categoria.setUsuarioList(usuariosCategoria);

                        categoriaFacade.edit(categoria);
                    }

                    //newUser.setCategoriaList(categoriaList); // no actualiza la bd

               }
               
               if (user == null) {              // Registro de compradorvendedor
                   session.setAttribute("usuario", newUser);
                   goTo = "ProductoServlet";
               } else {                         // Creación/modificación de administrador
                   goTo = "UsuarioServlet";
               }

               response.sendRedirect(request.getContextPath() + "/" + goTo); 
            
            } 
            
        } else {
            
            String redirectTo = "ProductoServlet";
            if (user.getRol().getNombre().equals("compradorvendedor")) {
                redirectTo = "ProductoServlet";
            } else if (user.getRol().getNombre().equals("marketing")) {
                redirectTo = "UsuarioCompradorServlet";
            }
            response.sendRedirect(request.getContextPath() + "/" + redirectTo);
                
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
