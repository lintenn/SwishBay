/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dto.UsuarioDTO;
import swishbay.service.UsuarioService;

/**
 *
 * @author Luis
 */
@WebServlet(name = "UsuarioGuardarServlet", urlPatterns = {"/UsuarioGuardarServlet"})
public class UsuarioGuardarServlet extends HttpServlet {

    @EJB UsuarioService usuarioService;
    
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
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        
        if (user == null || user.getRol().getNombre().equals("administrador")) {
            
            String strId, strTipoUsuario, strSaldo;
            String nombre, apellidos, correo, password, domicilio, ciudad, sexo, status = null, goTo = "CompradorProductosServlet";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = null;
            double saldo = 0;
            
            strId = request.getParameter("id");
            correo = request.getParameter("correo");
            nombre = request.getParameter("nombre");
            apellidos = request.getParameter("apellidos");
            password = request.getParameter("password");
            domicilio = request.getParameter("domicilio");
            ciudad = request.getParameter("ciudad");
            sexo = request.getParameter("sexo");
            strTipoUsuario = request.getParameter("tipo");
            String[] categorias = request.getParameterValues("categoria");
            strSaldo = request.getParameter("saldo");
            
            try {
                fechaNacimiento = formato.parse(request.getParameter("fechaNacimiento"));
                status = this.usuarioService.comprobarInformacionUsuario(fechaNacimiento, strId, correo, strSaldo);
            } catch (ParseException ex) {
                status = "Formato de fecha de nacimiento incorrecto.";
                System.out.println(ex);
            }
            
            if (status != null) {
                
                request.setAttribute("status", status);
               
               if (user == null) {
                   goTo = "CargarRegisterServlet";
               } else {
                   goTo = "UsuarioNuevoEditarServlet";
               }
               
               request.getRequestDispatcher(goTo).forward(request, response);
                
            } else {               
               
               if (user == null) {                          // Registro de compradorvendedor
                   
                   strTipoUsuario = "compradorvendedor";
                   UsuarioDTO usuario = this.usuarioService.crearUsuario(nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario, categorias);
                   
                   session.setAttribute("usuario", usuario);
                   goTo = "CompradorProductosServlet";
               } else if (strId == null || strId.isEmpty()) {   // Creación desde administrador
                   
                   saldo = Double.parseDouble(strSaldo);
                   this.usuarioService.crearUsuario(nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario, categorias);
                   
                   goTo = "UsuarioServlet";
               } else {                                     // Modificación desde administrador
                   
                   saldo = Double.parseDouble(strSaldo);
                   int id = Integer.parseInt(strId);
                   UsuarioDTO usuario = this.usuarioService.modificarUsuario(id, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario, categorias);
                   
                   if (user.getId() == id) {    // si se modifica al propio administrador, hay que actualizar el usuario de la sesión
                       session.setAttribute("usuario", usuario);
                   }
                   
                   goTo = "UsuarioServlet";
               }

               response.sendRedirect(request.getContextPath() + "/" + goTo); 
            
            } 
            
        } else {
            
            String redirectTo = "CompradorProductosServlet";
            if (user.getRol().getNombre().equals("compradorvendedor")) {
                redirectTo = "CompradorProductosServlet";
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
