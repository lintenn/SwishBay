/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dao.GrupoFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.Grupo;
import swishbay.entity.Usuario;

/**
 *
 * @author angel
 */
@WebServlet(name = "ParticipanteGrupoBorrarServlet", urlPatterns = {"/ParticipanteGrupoBorrarServlet"})
public class ParticipanteGrupoBorrarServlet extends HttpServlet {

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
        
            String str = request.getParameter("id");
            String strIdusuario = request.getParameter("idUsuario");
            
            Usuario usuario = this.usuarioFacade.find(Integer.parseInt(strIdusuario));
            Grupo grupo = this.grupoFacade.find(Integer.parseInt(str));
            
            List<Usuario> usuarioList = grupo.getUsuarioList();
            usuarioList.remove(usuario);
            grupo.setUsuarioList(usuarioList);
            
            List<Grupo> grupoList = usuario.getGrupoList();
            grupoList.remove(grupo);
            usuario.setGrupoList(grupoList);
            
            this.grupoFacade.edit(grupo);
            this.usuarioFacade.edit(usuario);
            
            response.sendRedirect(request.getContextPath() + "/ParticipantesGrupoEditarServlet?id=" + Integer.parseInt(str));
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
