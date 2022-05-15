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
import swishbay.service.ProductoService;

/**
 *
 * @author Luis
 */
@WebServlet(name = "ProductoAdminGuardarServlet", urlPatterns = {"/ProductoAdminGuardarServlet"})
public class ProductoAdminGuardarServlet extends SwishBayServlet {

    @EJB ProductoService productoService;
    
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
            
            String strId, status=null;
            strId = request.getParameter("id");
            
            String titulo = request.getParameter("nombre");
            String desc = request.getParameter("descripcion");
            String foto = request.getParameter("foto");
            if(foto==null || foto.isEmpty()){
                foto= "https://th.bing.com/th/id/OIP.KeKY2Y3R0HRBkPEmGWU3FwHaHa?pid=ImgDet&rs=1";
            }
            String categoria= request.getParameter("categoria");
            String precio = request.getParameter("precio");
            if(!precio.matches("[-+]?\\d*\\.?\\d+")){
                status= "Formato de precio incorrecto.";
                request.setAttribute("status", status);

                request.getRequestDispatcher("ProductoAdminEditarServlet").forward(request, response);

            }

            this.productoService.modificarProducto(strId, titulo, desc, foto, categoria, precio);
                   
            response.sendRedirect(request.getContextPath() + "/ProductoAdminServlet");
            
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
