package swishbay.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swishbay.dto.ProductoDTO;
import swishbay.dto.PujaDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.service.ProductoService;
import swishbay.service.PujaService;
import swishbay.service.UsuarioService;

/**
 * Este servlet efectua las pujas por parte del comprador
 * 
 * @author Miguel Oña Guerrero
 */
@WebServlet(name = "CompradorPujarServlet", urlPatterns = {"/CompradorPujarServlet"})
public class CompradorPujarServlet extends SwishBayServlet {
    
    @EJB ProductoService productoService;
    @EJB UsuarioService usuarioService;
    @EJB PujaService pujaService;
    
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
        
        if(super.comprobarSession(request, response)){
            
            Double cantidad = Double.parseDouble(request.getParameter("cantidad"));
            UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuario");
            int productoid = Integer.parseInt(request.getParameter("productoid"));
            String error = "";
            
            ProductoDTO producto = productoService.buscarProducto(productoid + "");
            PujaDTO mayorPuja = pujaService.mayorPuja(productoid);
            
            boolean saldoSuficiente = usuario.getSaldo() >= cantidad;
            boolean cantidadSuficiente;
            if(mayorPuja == null){
                cantidadSuficiente = cantidad >= producto.getPrecioSalida();
            }else{
                cantidadSuficiente = mayorPuja.getPrecio() < cantidad;
            }
            
            if(saldoSuficiente && cantidadSuficiente){
                usuario = usuarioService.sumarSaldo(-cantidad, usuario.getId());
                productoService.realizarPuja(productoid, cantidad, usuario.getId());
            }else{
                if(!saldoSuficiente){
                    error = "¡No tienes suficiente saldo!";
                }
                if(!cantidadSuficiente){
                    error = "¡Cantidad insuficinete para realizar la puja!";
                }        
                List<PujaDTO> pujas = producto.getPujaList();
                
                request.setAttribute("pujas", pujas);
                request.setAttribute("error", error);
                request.setAttribute("producto", producto);
                
                request.getRequestDispatcher("WEB-INF/jsp/verproducto.jsp").forward(request, response);
            }
             
            request.getSession().setAttribute("usuario", usuario);
            
            response.sendRedirect(request.getContextPath() + "/CompradorVerProductoServlet?id=" + productoid);
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