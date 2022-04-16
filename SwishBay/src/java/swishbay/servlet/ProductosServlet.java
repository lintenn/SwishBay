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
import swishbay.entity.Usuario;

/**
 * Servlet abstracto que recupera y muestra los productos aplicando un filtro específico.
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "ProductosServlet", urlPatterns = {"/ProductosServlet"})
public abstract class ProductosServlet extends SwishBayServlet {
    
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
            
            Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
            String filtroTitulo = request.getParameter("filtro");
            String filtroCategoria = request.getParameter("filtroCategoria");
            
            if(filtroTitulo == null || filtroTitulo.isEmpty()){
                filtroTitulo = "";
            }
            
            if(filtroCategoria == null || filtroCategoria.equals("Categoria")){
                filtroCategoria = "";
            } 
            
            List<Producto> productos = this.getProductos(filtroTitulo, filtroCategoria, usuario);   
            List<Categoria> categorias = categoriaFacade.findAll();

            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("selected", filtroCategoria);
            request.setAttribute("action", this.getServlet());
            
            forward(request, response);
        }
    }
    
    abstract protected List<Producto> getProductos(String filtroTitulo, String filtroCategoria, Usuario usuario);
    
    abstract protected String getServlet();
    
    protected void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/jsp/productos.jsp").forward(request, response);
    }

}
