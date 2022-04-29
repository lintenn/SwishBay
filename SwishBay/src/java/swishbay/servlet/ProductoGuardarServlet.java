/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dto.ProductoDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.service.ProductoService;


/**
 *
 * @author galop
 */
@WebServlet(name = "ProductoGuardarServlet", urlPatterns = {"/ProductoGuardarServlet"})
public class ProductoGuardarServlet extends SwishBayServlet {

    @EJB ProductoService ps;

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
        
        UsuarioDTO user=null;
        try{
            HttpSession session = request.getSession();
            user = (UsuarioDTO) session.getAttribute("usuario");

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        
        
        if(user!=null && user.getRol().getNombre().equals("compradorvendedor")){
            
            String strId, status=null;
            strId= request.getParameter("id");
            
            java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
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

                request.getRequestDispatcher("ProductoNuevoEditarServlet").forward(request, response);

            }
            
            if(strId == null || strId.isEmpty()){        
                
                ps.crearProducto(titulo, desc, foto, date, categoria, precio, user.getId());
            }else {
                ps.modificarProducto(strId, titulo, desc, foto, date, categoria, precio, user.getId());
            }           
            
            response.sendRedirect(request.getContextPath() + "/SellerServlet");
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
