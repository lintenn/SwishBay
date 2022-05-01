package swishbay.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dto.UsuarioDTO;
import swishbay.service.UsuarioService;

/**
 * Añade y elimina productos favoritos de un comprador.
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "CompradorManejoFavoritoServlet", urlPatterns = {"/CompradorManejoFavoritoServlet"})
public class CompradorManejoFavoritoServlet extends SwishBayServlet {
    
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
            
            String str = request.getParameter("id");
            
            if(str != null){
                UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuario");
                
                usuario = usuarioService.manejoFavoritos(Integer.parseInt(str), usuario.getId());    
                
                request.getSession().setAttribute("usuario", usuario);
                
                String servlet = (String) request.getSession().getAttribute("servlet");
                response.sendRedirect(request.getContextPath() + "/" + servlet);
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