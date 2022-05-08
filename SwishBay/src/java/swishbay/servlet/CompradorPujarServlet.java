package swishbay.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dto.UsuarioDTO;
import swishbay.service.ProductoService;
import swishbay.service.UsuarioService;

/**
 * Este servlet efectua las pujas por parte del comprador
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "CompradorPujarServlet", urlPatterns = {"/CompradorPujarServlet"})
public class CompradorPujarServlet extends SwishBayServlet {
    
    @EJB ProductoService productoService;
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
        
        if(super.comprobarSession(request, response)){
            
            Double cantidad = Double.parseDouble(request.getParameter("cantidad"));
            UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuario");
            String productoid = request.getParameter("productoid");
        
            usuario = usuarioService.sumarSaldo(-cantidad, usuario.getId());
            //productoService.
            
            request.getSession().setAttribute("usuario", usuario);
            
            response.sendRedirect(request.getContextPath() + "/CompradorVerProductoServlet?id=" + productoid);
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
