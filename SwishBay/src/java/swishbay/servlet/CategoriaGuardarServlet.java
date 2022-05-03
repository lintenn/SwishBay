/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.service.CategoriaService;

/**
 *
 * @author Luis
 */
@WebServlet(name = "CategoriaGuardarServlet", urlPatterns = {"/CategoriaGuardarServlet"})
public class CategoriaGuardarServlet extends SwishBayServlet {

    @EJB CategoriaService categoriaService;
    
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
            
            String strId;
            String nombre, descripcion, goTo = "CategoriaServlet";
            
            strId = request.getParameter("id");
            nombre = request.getParameter("nombre");
            descripcion = request.getParameter("descripcion");
            
            if (strId == null || strId.isEmpty()) { // Crear nueva categoria
                this.categoriaService.crearCategoria(nombre, descripcion);
                
            } else {                                // Editar categoria
                this.categoriaService.modificarCategoria(Integer.parseInt(strId), nombre, descripcion);
                
            }
            
            response.sendRedirect(request.getContextPath() + "/" + goTo); 
            
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
