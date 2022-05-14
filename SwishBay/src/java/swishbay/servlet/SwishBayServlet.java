/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dto.UsuarioDTO;

/**
 *
 * @author Luis 66%, Galo 33%
 */
public abstract class SwishBayServlet extends HttpServlet {
    
    @Override
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    
    @Override
    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected boolean comprobarSession (HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        if (user == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        } else {
            return true;
        }        
        
    }
    
    protected boolean comprobarCompradorVendedorSession (HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        }  else if (user.getRol().getNombre().equals("marketing")) {
            response.sendRedirect(request.getContextPath() + "/UsuarioCompradorServlet");
            return false;
        } else if (user.getRol().getNombre().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/UsuarioServlet");
            return false;
        }else {
            return true;
        }          
        
    }
    
    protected boolean comprobarAdminSession (HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        } else if (user.getRol().getNombre().equals("compradorvendedor")) {
            response.sendRedirect(request.getContextPath() + "/CompradorProductosServlet");
            return false;
        } else if (user.getRol().getNombre().equals("marketing")) {
            response.sendRedirect(request.getContextPath() + "/UsuarioCompradorServlet");
            return false;
        } else {
            return true;
        }     
    }
    
    protected boolean comprobarMarketingSession (HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException { // angel
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        } else if (user.getRol().getNombre().equals("compradorvendedor")) {
            response.sendRedirect(request.getContextPath() + "/CompradorProductosServlet");
            return false;
        } else if (user.getRol().getNombre().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/UsuarioServlet");
            return false;
        } else {
            return true;
        }     
    }
}
