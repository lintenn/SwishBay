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
import swishbay.dto.MensajeDTO;
import swishbay.service.MensajeService;
 
/**
 *
 * @author angel
 */
@WebServlet(name = "GrupoVerMensajeServlet", urlPatterns = {"/GrupoVerMensajeServlet"})
public class GrupoVerMensajeServlet extends SwishBayServlet {

     @EJB MensajeService mensajeService;
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
            String tipoFiltro = request.getParameter("filtroMensajes");
            String str = request.getParameter("id");
            List<MensajeDTO> mensajes = this.mensajeService.buscarMensajesPorIdGrupo(Integer.parseInt(str));
            
            if (filtroNombre != null && !filtroNombre.isEmpty() && mensajes.size() > 0) {     
                
                List<Integer> ids = new ArrayList<>();
                for(MensajeDTO mensaje : mensajes){
                    ids.add(mensaje.getId());
                }
                
                switch(tipoFiltro){
                    case "Asunto":
                        mensajes = this.mensajeService.listarMensajesDeUnGrupoPorAsuntoPorMensajes(filtroNombre, Integer.parseInt(str), ids);
                        break;
                    case "Cuerpo del mensaje":
                        mensajes = this.mensajeService.listarMensajesDeUnGrupoPorContenidoPorMensajes(filtroNombre, Integer.parseInt(str), ids);
                        break;
                }
            }

            request.setAttribute("filtro", filtroNombre);
            request.setAttribute("tipoFiltro", tipoFiltro);
            request.setAttribute("mensajes", mensajes);
            request.getRequestDispatcher("WEB-INF/jsp/mensajes.jsp").forward(request, response);   
            
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
