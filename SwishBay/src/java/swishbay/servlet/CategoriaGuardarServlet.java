/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dao.CategoriaFacade;
import swishbay.entity.Categoria;
import swishbay.entity.Usuario;

/**
 *
 * @author Luis
 */
@WebServlet(name = "CategoriaGuardarServlet", urlPatterns = {"/CategoriaGuardarServlet"})
public class CategoriaGuardarServlet extends HttpServlet {

    @EJB CategoriaFacade categoriaFacade;
    
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
        
        if (user == null || user.getTipoUsuario().getTipo().equals("administrador")) {
            
            String strId;
            String nombre, descripcion, goTo = "CategoriaServlet";
            Categoria newCategoria;
            
            strId = request.getParameter("id");
            nombre = request.getParameter("nombre");
            descripcion = request.getParameter("descripcion");
            
            if (strId == null || strId.isEmpty()) { // Crear nueva categoria
                newCategoria = new Categoria();
            } else {                             // Editar categoria
                newCategoria = this.categoriaFacade.find(Integer.parseInt(strId));
            }
            
            newCategoria.setNombre(nombre);
            newCategoria.setDescripcion(descripcion);
            
            if (strId == null || strId.isEmpty()) {    // Crear nueva categoría
                categoriaFacade.create(newCategoria);
            } else {                                   // Editar categoría
                categoriaFacade.edit(newCategoria);
            }  
            
            response.sendRedirect(request.getContextPath() + "/" + goTo); 
            
        } else {
            
            String redirectTo = "ProductoServlet";
            if (user.getTipoUsuario().getTipo().equals("compradorvendedor")) {
                redirectTo = "ProductoServlet";
            } else if (user.getTipoUsuario().getTipo().equals("marketing")) {
                redirectTo = "prueba.jsp";
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
