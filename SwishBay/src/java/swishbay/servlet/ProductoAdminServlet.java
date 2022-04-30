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
import swishbay.dto.CategoriaDTO;
import swishbay.dto.ProductoDTO;
import swishbay.service.CategoriaService;
import swishbay.service.ProductoService;

/**
 *
 * @author Luis
 */
@WebServlet(name = "ProductoAdminServlet", urlPatterns = {"/ProductoAdminServlet"})
public class ProductoAdminServlet extends SwishBayServlet {

    @EJB ProductoService productoService;
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
            
            String filtroNombre = request.getParameter("filtro");
            String filtroCategoria = request.getParameter("filtroCategoria");
            
            List<CategoriaDTO> categorias = this.categoriaService.listarCategorias();
            
            List<ProductoDTO> productos = this.productoService.listarProductos(filtroNombre, filtroCategoria);
            
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("selected", filtroCategoria);
            request.getRequestDispatcher("WEB-INF/jsp/productosAdmin.jsp").forward(request, response);
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
