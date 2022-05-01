package swishbay.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dto.CategoriaDTO;
import swishbay.dto.ProductoDTO;
import swishbay.dto.PujaDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.service.CategoriaService;
import swishbay.service.CompradorService;
import swishbay.service.PujaService;

/**
 * Servlet abstracto que recupera y muestra los productos aplicando un filtro específico.
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "CompradorServlet", urlPatterns = {"/CompradorServlet"})
public abstract class CompradorServlet extends SwishBayServlet {
    
    @EJB CategoriaService categoriaService;
    @EJB CompradorService compradorService;
    @EJB PujaService pujaService;
    
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
            
            UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuario");
            String filtroTitulo = request.getParameter("filtro");
            String filtroCategoria = request.getParameter("filtroCategoria");
            
            if(filtroTitulo == null || filtroTitulo.isEmpty()){
                filtroTitulo = "";
            }
            
            if(filtroCategoria == null || filtroCategoria.equals("Categoria")){
                filtroCategoria = "";
            }
            
            List<ProductoDTO> productos = this.getProductos(filtroTitulo, filtroCategoria, usuario);   
            List<CategoriaDTO> categorias = categoriaService.listarCategorias();
            
            List<PujaDTO> mayoresPujas = new ArrayList<>();
            for(ProductoDTO producto : productos){
                mayoresPujas.add(pujaService.mayorPuja(producto.getId()));
            }
            request.setAttribute("mayoresPujas", mayoresPujas);
            
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("selected", filtroCategoria);
            request.setAttribute("action", this.getServlet());
            
            forward(request, response);
        }
    }
    
    abstract protected List<ProductoDTO> getProductos(String filtroTitulo, String filtroCategoria, UsuarioDTO usuario);
    
    abstract protected String getServlet();
    
    protected void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/jsp/productos.jsp").forward(request, response);
    }

}
