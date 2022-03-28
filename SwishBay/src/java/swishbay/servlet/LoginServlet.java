/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dao.TipoUsuarioFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.TipoUsuario;
import swishbay.entity.Usuario;
import swishbay.service.UsuarioService;

/**
 *
 * @author Luis
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB UsuarioFacade usuarioFacade;
    @EJB TipoUsuarioFacade tipoUsuarioFacade;
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
        response.setContentType("text/html;charset=UTF-8");
        String correo, status = "OK", goTo = "prueba.jsp", password, contrasenaDelUsuario = "";
        correo = request.getParameter("correo");
        password = request.getParameter("password");
        //byte[] contrasenaIntroducida = usuarioService.hashPassword(contrasena);
        
        Usuario user = null;
        //TipoUsuario tipoUsuario = null;
        
        try{
            List<Usuario> users = usuarioFacade.findAll();
            
            for (Usuario u : users) {
                if ((u.getCorreo()).equals(correo)) {
                    user = u;
                    //tipoUsuario = tipoUsuarioFacade.find(u.getId());
                    contrasenaDelUsuario = user.getPassword();
                }
            }
        }
        catch(EJBException ex){
            user = null;
        }
        
        HttpSession session = request.getSession();
        if(user == null){
           status = "El correo es incorrecto.";
           //request.setAttribute("status", status);
           goTo = "login.jsp";
        }else if(!password.equals(contrasenaDelUsuario)){
           status = "La contraseña es incorrecta";
           //request.setAttribute("status", status);
           goTo = "login.jsp";
        }else{
            //request.getSession().setAttribute("usuario", user);
            session.setAttribute("usuario", user);
            //session.setAttribute("tipoUsuario", tipoUsuario);
        }
        session.setAttribute("status", status);
        //System.out.println(status);
        
        //request.getRequestDispatcher(goTo).forward(request, response); 
        response.sendRedirect(request.getContextPath() + "/" + goTo);
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
