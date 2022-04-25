package swishbay.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.entity.Producto;
import swishbay.entity.Usuario;

/**
 * Recupera los productos favoritos de un comprador.
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "ProductoFavoritoServlet", urlPatterns = {"/ProductoFavoritoServlet"})
public class ProductoFavoritoServlet extends ProductosServlet {

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
        super.processRequest(request, response);
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
        super.processRequest(request, response);
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
    protected List<Producto> getProductos(String filtroTitulo, String filtroCategoria, Usuario usuario) {
        //return usuario.getProductoList();
        return productoFacade.findFavoritosByFiltro("", "", 5);
    }
    
    @Override
    protected String getServlet() {
        return this.getServletName();
    }
}
