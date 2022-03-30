/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.TipoUsuarioFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.entity.Categoria;
import swishbay.entity.TipoUsuario;
import swishbay.entity.Usuario;
import swishbay.service.UsuarioService;

/**
 *
 * @author Linten
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @EJB UsuarioFacade usuarioFacade;
    @EJB TipoUsuarioFacade tipoUsuarioFacade;
    @EJB CategoriaFacade categoriaFacade;
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
        String nombre, apellidos, correo, password, domicilio, ciudad, sexo, status = null, goTo = "prueba.jsp";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        int mes, dia;
        double saldo = 0;

        try {
            fechaNacimiento = formato.parse(request.getParameter("fechaNacimiento"));
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        Date fechaSistema = new Date();
        int edad = fechaSistema.getYear() - fechaNacimiento.getYear();
        //RequestDispatcher rd;
        Usuario newUser = null, posibleUser = null;
        //byte[] contrasenaCifrada;
        boolean esMenor = edad < 18;

        if (edad == 18) {
            mes = fechaSistema.getMonth() - fechaNacimiento.getMonth();
            if (mes == 0) {
                dia = fechaSistema.getDay() - fechaNacimiento.getDay();
                if (dia >= 0) {
                    esMenor = false;
                } else {
                    esMenor = true;
                    edad = 17;
                }
            } else if (mes < 0) {
                esMenor = true;
                edad = 17;
            }else{
                esMenor = false;
            }
        }

        correo = request.getParameter("correo");
        try {
            List<Usuario> users = usuarioFacade.findAll();
            
            for (Usuario u : users) {
                if ((u.getCorreo()).equals(correo)) {
                    posibleUser = u;
                }
            }
               
        } catch (EJBException e) {
            posibleUser = null;
        }

        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        password = request.getParameter("password");
        //contrasenaCifrada = usuarioService.hashPassword(contrasena);
        domicilio = request.getParameter("domicilio");
        ciudad = request.getParameter("ciudad");
        sexo = request.getParameter("sexo");
        String[] categorias = request.getParameterValues("categoria");
        

        HttpSession session = request.getSession();
        
        if (posibleUser != null) {
           status = "El correo introducido ya existe en el sistema";
           session.setAttribute("status", status);
           goTo = "register.jsp";
        } else if (esMenor) {
           status = "Lo siento, eres menor de edad";
           session.setAttribute("status", status);
           goTo = "register.jsp";
        } else {
           status = "Todo correcto";
           request.setAttribute("status", status);
           newUser = new Usuario();
           newUser.setNombre(nombre);
           newUser.setApellidos(apellidos);
           newUser.setCorreo(correo);
           newUser.setPassword(password);
           newUser.setDomicilio(domicilio);
           newUser.setCiudad(ciudad);
           newUser.setSexo(sexo);
           newUser.setFechaNacimiento(fechaNacimiento);
           newUser.setSaldo(saldo);
           // Faltarían las categorías...
           
           if (categorias.length > 0) {
                List<Categoria> categoriaList = new ArrayList<>();
                for (String categoriaId : categorias) {
                    categoriaList.add(categoriaFacade.find(Integer.parseInt(categoriaId)));
                }

                newUser.setCategoriaList(categoriaList);

           }
                                 
           //newUser.setTipoUsuario(tipoUsuario); //????
           
           usuarioFacade.create(newUser); 
           
           TipoUsuario tipoUsuario = new TipoUsuario(newUser.getId(),"compradorvendedor");
           tipoUsuarioFacade.create(tipoUsuario);
           
           session.setAttribute("usuario", newUser);
           
           //Properties mailProperties = new Properties();
           //mailProperties.setProperty("to", newUser.getCorreo());
           //mailProperties.setProperty("subject", "Welcome to Efake");
           //mailProperties.setProperty("userName", newUser.getNombre());
           //mailProperties.setProperty("template", TemplatesEnum.REGISTER_USER.toString());
           //emailService.sendEmail(mailProperties);
        }

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
