/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.ProductoFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.Categoria;
import swishbay.entity.Producto;
import swishbay.entity.Usuario;

/**
 *
 * @author galop
 */
@WebServlet(name = "ProductoGuardarServlet", urlPatterns = {"/ProductoGuardarServlet"})
public class ProductoGuardarServlet extends HttpServlet {

    @EJB ProductoFacade pf;
    @EJB CategoriaFacade cf;

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
            String strId,str, status=null;
            strId= request.getParameter("id");

            
            
            if(strId == null || strId.isEmpty()){
                p = new Producto();
                java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
                p.setFinPuja(date);
            }else {
                p = this.pf.find(Integer.parseInt(strId));
            }

         
            str = request.getParameter("nombre");
            
            p.setTitulo(str);  
            
            

            str = request.getParameter("descripcion");
            p.setDescripcion(str);

            str = request.getParameter("foto");
            if(str==null || str.isEmpty()){
                str= "https://th.bing.com/th/id/OIP.KeKY2Y3R0HRBkPEmGWU3FwHaHa?pid=ImgDet&rs=1";
            }
            p.setFoto(str);

            str = request.getParameter("categoria");
            for(Categoria c : cf.findAll()){
                if(c.getNombre().equals(str))
                    p.setCategoria(c);
            }
            
            str = request.getParameter("precio");
            if(!str.matches("[-+]?\\d*\\.?\\d+")){
                status= "Formato de precio incorrecto.";
                request.setAttribute("status", status);
                
                request.getRequestDispatcher("ProductoNuevoEditarServlet").forward(request, response);
               
            }
            
            p.setPrecioSalida(Double.parseDouble(str));
                
           
           
            
            short n=0;
            p.setEnPuja(n);
            
            p.setVendedor(user);
            

            if(strId == null || strId.isEmpty()){
                pf.create(p);
            }else {
                pf.edit(p);
            }
            response.sendRedirect(request.getContextPath() + "/SellerServlet");
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
