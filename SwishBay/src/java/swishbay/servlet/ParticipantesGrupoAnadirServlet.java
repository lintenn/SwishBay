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
import swishbay.dto.UsuarioDTO;
import swishbay.service.GrupoService;
import swishbay.service.UsuarioCompradorService;
import swishbay.service.UsuarioService;
 
/**
 *
 * @author angel
 */
@WebServlet(name = "ParticipantesGrupoAnadirServlet", urlPatterns = {"/ParticipantesGrupoAnadirServlet"})
public class ParticipantesGrupoAnadirServlet extends SwishBayServlet {

    @EJB GrupoService grupoService;
    @EJB UsuarioCompradorService usuarioCompradorService;
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
        
            String filtroNombre = request.getParameter("filtro");
            String tipoFiltro = request.getParameter("filtroUsuariosCompradores");
            String saldoDesde = request.getParameter("saldoDesde");
            String saldoHasta = request.getParameter("saldoHasta");
            Integer strId = Integer.parseInt(request.getParameter("id"));
            List<UsuarioDTO> usuarios = new ArrayList<>();
            
            List<Integer> idsUsuariosGrupo = this.grupoService.listarIdsUsuariosDeUnGrupo(strId);

            if (filtroNombre == null || filtroNombre.isEmpty()) {
                usuarios = this.grupoService.listarUsuariosQueNoPertenecenAUnGrupo(idsUsuariosGrupo);
            } else {
                switch(tipoFiltro){
                    case "Nombre":
                        usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorNombreQueNoPertencenAUnGrupo(filtroNombre, idsUsuariosGrupo);
                        break;
                    case "Correo":
                        usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCorreoQueNoPertencenAUnGrupo(filtroNombre, idsUsuariosGrupo);
                        break;
                    case "Apellidos":
                        usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorApellidosQueNoPertencenAUnGrupo(filtroNombre, idsUsuariosGrupo);
                        break;
                    case "Ciudad":
                        usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCiudadQueNoPertencenAUnGrupo(filtroNombre, idsUsuariosGrupo);
                        break;
                    case "Domicilio":
                        usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorDomicilioQueNoPertencenAUnGrupo(filtroNombre, idsUsuariosGrupo);
                        break;
                    case "Sexo":
                        usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSexoQueNoPertencenAUnGrupo(filtroNombre, idsUsuariosGrupo);
                        break;
                }
            }
            
            if(saldoDesde != null && !saldoDesde.isEmpty() && usuarios.size() > 0){
                
                List<Integer> ids = new ArrayList<>();
                for(UsuarioDTO user : usuarios){
                    ids.add(user.getId());
                }
                
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoDesdeQueNoPertencenAUnGrupo(Integer.parseInt(saldoDesde), ids, idsUsuariosGrupo);
                
            }
            
            if(saldoHasta != null && !saldoHasta.isEmpty() && usuarios.size() > 0){
                
                List<Integer> ids = new ArrayList<>();
                for(UsuarioDTO user : usuarios){
                    ids.add(user.getId());
                }
                
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoHastaQueNoPertencenAUnGrupo(Integer.parseInt(saldoHasta), ids, idsUsuariosGrupo);
                
            }

            request.setAttribute("filtro", filtroNombre);
            request.setAttribute("saldoDesde", saldoDesde);
            request.setAttribute("saldoHasta", saldoHasta);
            request.setAttribute("tipoFiltro", tipoFiltro);
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("WEB-INF/jsp/participantesGrupoAnadir.jsp").forward(request, response); 
            
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
