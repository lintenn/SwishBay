package swishbay.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.ProductoFacade;
import swishbay.entity.Categoria;
import swishbay.entity.Producto;

/**
 * Muestra todos lo productos registrados.
 * 
 * @author Miguel
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends SwishBayServlet {
    
    @EJB ProductoFacade productoFacade;  
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
        
        if (super.comprobarSession(request, response)) {
            
            String filtroTitulo = request.getParameter("filtro");
            String filtroCategoria = request.getParameter("filtroCategoria");
            
            List<Producto> productos;
            
            if(filtroTitulo == null || filtroTitulo.isEmpty()){
                if(filtroCategoria == null || filtroCategoria.equals("Categoria")){
                    productos = productoFacade.findAll();
                }else{
                    productos = productoFacade.findAll(filtroCategoria);
                }   
            }else{
                if(filtroCategoria == null || filtroCategoria.equals("Categoria")){
                    productos = productoFacade.findByNombre(filtroTitulo);
                }else{
                    productos = productoFacade.findByNombre(filtroTitulo, filtroCategoria);
                }
                
            }
            
            List<Categoria> categorias = categoriaFacade.findAll();

            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("selected", filtroCategoria);
            
            request.getRequestDispatcher("WEB-INF/jsp/productos.jsp").forward(request, response);
        
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
