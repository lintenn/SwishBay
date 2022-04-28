/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dao.MensajeFacade;
import swishbay.entity.Mensaje;
import swishbay.dao.GrupoFacade;
import swishbay.entity.Grupo;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.Usuario;

/**
 *
 * @author angel
 */
@WebServlet(name = "MensajeGuardarServlet", urlPatterns = {"/MensajeGuardarServlet"})
public class MensajeGuardarServlet extends HttpServlet {

    @EJB MensajeFacade mensajeFacade;
    @EJB GrupoFacade grupoFacade;
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

            HttpSession session = request.getSession();
            Usuario user = (Usuario)session.getAttribute("usuario");
            String asunto, contenido, goTo = "GrupoVerMensajeServlet", strId, strIdGrupo;

            strId = request.getParameter("id");
            strIdGrupo = request.getParameter("idGrupo");
            goTo += "?id="+strIdGrupo;
            Mensaje newMensaje = null;
            
            asunto = request.getParameter("asunto");
            contenido = request.getParameter("contenido");
            Grupo grupo = null;
            
            if(strId == null || strId.isEmpty()){
                newMensaje = new Mensaje();
                grupo = this.grupoFacade.find(Integer.parseInt(strIdGrupo));
                newMensaje.setGrupo(grupo);
                newMensaje.setMarketing(user);
                newMensaje.setFecha(new Date());
                newMensaje.setUsuarioList(new ArrayList<Usuario>());
            } else {
                newMensaje = this.mensajeFacade.find(Integer.parseInt(strId));
            }
               
            newMensaje.setAsunto(asunto);
            newMensaje.setContenido(contenido);
               
            if(strId == null || strId.isEmpty()){
                mensajeFacade.create(newMensaje);
                for(Usuario usuario : grupo.getUsuarioList()){
                    List<Mensaje> mensajeList = usuario.getMensajeList();
                    mensajeList.add(newMensaje);
                    usuario.setMensajeList(mensajeList);
                    usuarioFacade.edit(usuario);
                };
            } else {
                mensajeFacade.edit(newMensaje);   
            }
            
            

            response.sendRedirect(request.getContextPath() + "/" + goTo); 
            
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
