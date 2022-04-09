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
import swishbay.dao.ProductoFacade;
import swishbay.dao.PujaFacade;
import swishbay.entity.Categoria;
import swishbay.entity.Producto;
import swishbay.entity.Puja;
import swishbay.entity.Usuario;

/**
 *
 * @author galop
 */
@WebServlet(name = "EnPujaGuardarServlet", urlPatterns = {"/EnPujaGuardarServlet"})
public class EnPujaGuardarServlet extends HttpServlet {

    @EJB ProductoFacade pf;
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
        
        Usuario user=null;
        try{
            HttpSession session = request.getSession();
            user = (Usuario) session.getAttribute("usuario");

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        if(user!=null && user.getTipoUsuario().getTipo().equals("compradorvendedor")){
            Producto p;
            
            String strId,str;
            strId= request.getParameter("id");

            p = this.pf.find(Integer.parseInt(strId));
         
            if(p.getEnPuja()==0){
                p.setEnPuja((short) 1);
            }
            
            str = request.getParameter("time");
            SimpleDateFormat dateParser = new SimpleDateFormat("yy-MM-dd");
            Date d=new Date(); 
            
            try {
                d = dateParser.parse(str);
            } catch (ParseException ex) {
               System.err.println(ex.getLocalizedMessage());
            }
            
            p.setFinPuja(d);
            
            pf.edit(p);
            
                       
            response.sendRedirect(request.getContextPath() + "/PujasServlet");
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
