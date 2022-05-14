/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dto.ProductoDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.service.ProductoService;


/**
 *
 * @author galop
 */
@WebServlet(name = "EnPujaGuardarServlet", urlPatterns = {"/EnPujaGuardarServlet"})
public class EnPujaGuardarServlet extends SwishBayServlet {

    @EJB ProductoService ps;
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
        
        if (super.comprobarCompradorVendedorSession(request, response)) {
            UsuarioDTO user = (UsuarioDTO)request.getSession().getAttribute("usuario");

        
      
            ProductoDTO p;

            String strId,str, status= null;
            strId= request.getParameter("id");

            p = ps.buscarProducto(strId);

            str = request.getParameter("time");
            SimpleDateFormat dateParser = new SimpleDateFormat("yy-MM-dd");
            Date d=new Date(); 

            try {
                d = dateParser.parse(str);
            } catch (ParseException ex) {
                System.err.println(ex.getLocalizedMessage());
            }

            Date actual = new Date();
            if(actual.before(d)){

                if(p.getEnPuja()==0){
                    p.setEnPuja((short) 1);
                    str = request.getParameter("precio");
                    ps.modificarPuja(strId,str,d);
                }else{
                    ps.modificarPuja(strId, d);
                }             

                response.sendRedirect(request.getContextPath() + "/PujasServlet");
            }else{
                    status= "La fecha introducida es anterior a la actual";
                    request.setAttribute("status", status);

                    request.getRequestDispatcher("/EnPujaNuevoServlet").forward(request, response);

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
