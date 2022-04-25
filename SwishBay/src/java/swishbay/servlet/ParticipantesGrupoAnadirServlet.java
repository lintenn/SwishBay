/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dao.GrupoFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.Usuario;

/**
 *
 * @author angel
 */
@WebServlet(name = "ParticipantesGrupoAnadirServlet", urlPatterns = {"/ParticipantesGrupoAnadirServlet"})
public class ParticipantesGrupoAnadirServlet extends HttpServlet {

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
        
            String filtroNombre = request.getParameter("filtro");
            Integer strId = Integer.parseInt(request.getParameter("id"));
            List<Usuario> usuarios;
            List<Usuario> usuariosGrupo;
            List<Usuario> usuariosCompradores = new ArrayList<>();

            if (filtroNombre == null || filtroNombre.isEmpty()) {
                usuariosGrupo = this.grupoFacade.findById(strId);
                usuarios = this.usuarioFacade.findAll();
                for (Usuario usuario : usuarios){
                    if(usuario.getRol().getNombre().equals("compradorvendedor") && !usuariosGrupo.contains(usuario)){
                        usuariosCompradores.add(usuario);
                    }
                }
                usuarios = usuariosCompradores;
            } else {
                usuariosGrupo = this.grupoFacade.findByIdAndNombre(strId, filtroNombre);
                usuarios = this.usuarioFacade.findByNombre(filtroNombre);
                for (Usuario usuario : usuarios){
                    if(usuario.getRol().getNombre().equals("compradorvendedor") && !usuariosGrupo.contains(usuario)){
                        usuariosCompradores.add(usuario);
                    }
                }
                usuarios = usuariosCompradores;
            }

            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("WEB-INF/jsp/participantesGrupoAnadir.jsp").forward(request, response);   
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