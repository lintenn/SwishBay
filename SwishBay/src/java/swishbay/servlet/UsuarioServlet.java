/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dto.RolUsuarioDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.service.RolUsuarioService;
import swishbay.service.UsuarioService;

/**
 *
 * @author Luis
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends SwishBayServlet {
    
    @EJB UsuarioService usuarioService;
    @EJB RolUsuarioService rolUsuarioService;

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
        
        if (super.comprobarAdminSession(request, response)) {
            
            String filtroNombre = request.getParameter("filtro");
            String filtroRol = request.getParameter("filtroRol");
            
            List<RolUsuarioDTO> roles = this.rolUsuarioService.listarRoles();
            
            List<UsuarioDTO> usuarios = this.usuarioService.listarUsuarios(filtroNombre, filtroRol);

            request.setAttribute("usuarios", usuarios);
            request.setAttribute("roles", roles);
            request.setAttribute("selected", filtroRol);
            request.getRequestDispatcher("WEB-INF/jsp/usuarios.jsp").forward(request, response);   
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
