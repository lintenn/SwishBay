/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.service.GrupoService;
import swishbay.service.UsuarioService;
 
/**
 *
 * @author angel
 */
@WebServlet(name = "ParticipanteAnadirGrupoServlet", urlPatterns = {"/ParticipanteAnadirGrupoServlet"})
public class ParticipanteAnadirGrupoServlet extends HttpServlet {

    @EJB GrupoService grupoService;
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
        
            String str = request.getParameter("id");
            String strIdusuario = request.getParameter("idUsuario");
            
            this.grupoService.añadirUsuarioAListaUsuariosGrupo(Integer.parseInt(strIdusuario), Integer.parseInt(str));
            this.usuarioService.añadirGrupoAListaGruposUsuario(Integer.parseInt(strIdusuario), Integer.parseInt(str));
            
            response.sendRedirect(request.getContextPath() + "/ParticipantesGrupoAnadirServlet?id=" + Integer.parseInt(str));
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
