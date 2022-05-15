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
import swishbay.dto.GrupoDTO;
import swishbay.service.GrupoService;

/**
 *
 * @author angel
 */
@WebServlet(name = "GrupoServlet", urlPatterns = {"/GrupoServlet"})
public class GrupoServlet extends SwishBayServlet {

    @EJB GrupoService grupoService;
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
        
        if(super.comprobarMarketingSession(request, response)){
            
            String filtroNombreGrupo = request.getParameter("filtroNombreGrupo");
            String filtroNombreCreador = request.getParameter("filtroNombreCreador");
            String filtroApellidosCreador = request.getParameter("filtroApellidoCreador");
            List<GrupoDTO> grupos = this.grupoService.buscarTodosGrupos();

            if(filtroNombreGrupo != null && !filtroNombreGrupo.isEmpty() && grupos.size() > 0){
                
                List<Integer> ids = new ArrayList<>();
                for(GrupoDTO grupo : grupos){
                    ids.add(grupo.getId());
                }
                
                grupos = this.grupoService.buscarGruposPorNombreYGrupos(filtroNombreGrupo, ids);
                
            }
            
            if(((filtroApellidosCreador != null && !filtroApellidosCreador.isEmpty()) || (filtroNombreCreador != null && !filtroNombreCreador.isEmpty())) && grupos.size() > 0){
                
                List<Integer> ids = new ArrayList<>();
                for(GrupoDTO grupo : grupos){
                    ids.add(grupo.getId());
                }
                
                if((filtroNombreCreador == null || filtroNombreCreador.isEmpty()) && (filtroApellidosCreador != null && !filtroApellidosCreador.isEmpty())){
                    grupos = this.grupoService.buscarGruposPorApellidosCreador(filtroApellidosCreador, ids);
                } else if((filtroApellidosCreador == null || filtroApellidosCreador.isEmpty()) && (filtroNombreCreador != null && !filtroNombreCreador.isEmpty())){
                    grupos = this.grupoService.buscarGruposPorNombreCreador(filtroNombreCreador, ids);
                } else {
                    grupos = this.grupoService.buscarGruposPorNombreYApellidosCreador(filtroNombreCreador, filtroApellidosCreador, ids);
                }
                
                
            }

            request.setAttribute("nombreCreador", filtroNombreCreador);
            request.setAttribute("apellidoCreador", filtroApellidosCreador);
            request.setAttribute("nombreGrupo", filtroNombreGrupo);
            request.setAttribute("grupos", grupos);
            request.getRequestDispatcher("WEB-INF/jsp/grupos.jsp").forward(request, response);  
            
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
