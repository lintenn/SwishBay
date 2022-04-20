package swishbay.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dao.ProductoFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.Producto;
import swishbay.entity.Usuario;

/**
 * Añade y elimina productos favoritos de un comprador.
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "ManejoFavoritoServlet", urlPatterns = {"/ManejoFavoritoServlet"})
public class ManejoFavoritoServlet extends SwishBayServlet {
    
    @EJB ProductoFacade productoFacade;
    @EJB UsuarioFacade usuarioFacade;
    
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
            
            String str = request.getParameter("id");
            
            if(str != null){
                Producto producto = productoFacade.find(Integer.parseInt(str));
                
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario)session.getAttribute("usuario");
                List<Producto> favoritos = usuario.getProductoList();
                
                if(favoritos.contains(producto)){
                    favoritos.remove(producto);
                }else{
                    favoritos.add(producto);
                }
                
                usuario.setProductoList(favoritos);
                
                usuarioFacade.edit(usuario);
                
                session.setAttribute("usuario", usuario);
                
                response.sendRedirect(request.getContextPath() + "/ProductoServlet");
                //request.getRequestDispatcher("WEB-INF/jsp/productos.jsp").forward(request, response);
            }
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
