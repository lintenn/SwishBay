package swishbay.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dto.ProductoDTO;
import swishbay.dto.UsuarioDTO;

/**
 * Recupera todos los productos en puja que no sean vendidos por el usuario.
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "CompradorEnPujaServlet", urlPatterns = {"/CompradorEnPujaServlet"})
public class CompradorEnPujaServlet extends CompradorServlet {

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
    
    @Override
    protected List<ProductoDTO> getProductos(String filtroTitulo, String filtroCategoria, Double filtroPrecio, UsuarioDTO usuario) {
        return compradorService.listarProductosEnPuja(filtroTitulo, filtroCategoria, filtroPrecio, usuario.getId());
    }
    
    @Override
    protected String getServlet() {
        return this.getServletName();
    }
    
    @Override
    protected void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/jsp/productosenpuja.jsp").forward(request, response);
    }
}