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
import swishbay.dao.CategoriaFacade;
import swishbay.dao.ProductoFacade;
import swishbay.entity.Categoria;
import swishbay.entity.Producto;
import swishbay.entity.Usuario;

/**
 *
 * @author galop
 */
@WebServlet(name = "SellerServlet", urlPatterns = {"/SellerServlet"})
public class SellerServlet extends SwishBayServlet {

    @EJB ProductoFacade productoFacade;
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
        
        if (super.comprobarSession(request, response)) {
            Usuario user = (Usuario)request.getSession().getAttribute("usuario");
        
            String filtroNombre = request.getParameter("filtro");
            String filtroCategoria = request.getParameter("filtroCategoria");
            List<Producto> productos = null;
            List<Categoria> categorias= cf.findAll();

            if(filtroNombre == null || filtroNombre.isEmpty()){
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = productoFacade.findAll();

                }else{
                    productos= productoFacade.findAll(filtroCategoria);

                }
            }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = productoFacade.findByNombre(filtroNombre);

                }else{
                    productos = productoFacade.findByNombre(filtroNombre,filtroCategoria);

                }   
            }

            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("selected", filtroCategoria);
            
            if (user.getTipoUsuario().getTipo().equals("administrador")) {
                request.getRequestDispatcher("WEB-INF/jsp/productosAdmin.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("seller.jsp").forward(request, response);
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
