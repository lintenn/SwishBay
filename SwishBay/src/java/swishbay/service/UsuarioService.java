/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.RolUsuarioFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.UsuarioDTO;
import swishbay.entity.Categoria;
import swishbay.entity.RolUsuario;
import swishbay.entity.Usuario;

/**
 *
 * @author Luis
 */
@Stateless
public class UsuarioService {
    
    @EJB UsuarioFacade usuarioFacade;
    @EJB CategoriaFacade categoriaFacade;
    @EJB RolUsuarioFacade rolUsuarioFacade;
    
    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario : lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<UsuarioDTO> listarUsuarios (String filtroNombre) {
        List<Usuario> usuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            usuarios = this.usuarioFacade.findAll();        
        } else {
            usuarios = this.usuarioFacade.findByNombre(filtroNombre);
        }
        
        return this.listaEntityADTO(usuarios);
    }
    
    public UsuarioDTO buscarUsuario (Integer id) {
        Usuario usuario = this.usuarioFacade.find(id);
        return usuario.toDTO();
    }
    
    public void borrarUsuario (Integer id) {
        Usuario usuario = this.usuarioFacade.find(id);
        
        this.usuarioFacade.remove(usuario);
    }
    
    public String comprobarInformacionUsuario (Date fechaNacimiento, String strId, String correo, String strSaldo) {
        
        String status;
        Date fechaSistema = new Date();
        int edad = fechaSistema.getYear() - fechaNacimiento.getYear();
        Usuario posibleUser = null;
        //byte[] contrasenaCifrada;
        boolean esMenor = edad < 18;
        int mes, dia;
        if (edad == 18) {
            mes = fechaSistema.getMonth() - fechaNacimiento.getMonth();
            if (mes == 0) {
                dia = fechaSistema.getDay() - fechaNacimiento.getDay();
                esMenor = dia < 0;
            } else {
                esMenor = mes < 0;
            }
        }
        
        if (strId == null || strId.isEmpty()) { // si estamos añadiendo
            try {
                posibleUser = usuarioFacade.findByCorreo(correo);
            } catch (EJBException e) {
                posibleUser = null;
            }
        }
        
        if (strSaldo != null && !strSaldo.isEmpty() && !strSaldo.matches("[-+]?\\d*\\.?\\d+")) {
            status= "Formato de saldo incorrecto.";
                
        } else if (posibleUser != null) {
            status = "El correo introducido ya existe en el sistema";
               
        } else if (esMenor) {
            status = "No se permiten usuarios menores de edad";
            
        } else {
            status = null;
        }
        
        return status;
    }
    
    private void rellenarUsuario (Usuario usuario, String nombre, String apellidos, String correo,
                                String password, String domicilio, String ciudad, String sexo,
                                Date fechaNacimiento, Double saldo, String strTipoUsuario) {
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setDomicilio(domicilio);
        usuario.setCiudad(ciudad);
        usuario.setSexo(sexo);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setSaldo(saldo);
               
        RolUsuario rol = this.rolUsuarioFacade.findByNombre(strTipoUsuario);
        usuario.setRol(rol);
               
        // Faltarían las categorias...
    }
    
    private void rellenarCategoriasUsuario (String[] categorias, Usuario newUser) {
        // Cargamos las categorías...
        if (categorias != null && categorias.length > 0) {
            List<Categoria> categoriaList = newUser.getCategoriaList();
            //System.out.println(categoriaList.toString());
            // Borramos al usuario de las categorias anteriores
            if (categoriaList != null) {
                for (Categoria categoria : categoriaList) {
                    //System.out.println(categoria.toString());
                    List<Usuario> usuariosCategoria = categoria.getUsuarioList();
                        
                    usuariosCategoria.remove(newUser);
                        
                    categoria.setUsuarioList(usuariosCategoria);

                    categoriaFacade.edit(categoria);
                }
            }
      
            // Añadimos al usuario en las nuevas categorías
            for (String categoriaId : categorias) {
                Categoria categoria = categoriaFacade.find(Integer.parseInt(categoriaId));

                List<Usuario> usuariosCategoria = categoria.getUsuarioList();

                if (!usuariosCategoria.contains(newUser)) usuariosCategoria.add(newUser);

                categoria.setUsuarioList(usuariosCategoria);

                categoriaFacade.edit(categoria);
            }
            
            // Añadimos las categorías al usuario
            List<Categoria> listaCategorias = new ArrayList<>();
            for (String categoriaId : categorias) {
                Categoria categoria = categoriaFacade.find(Integer.parseInt(categoriaId));
                
                listaCategorias.add(categoria);
            }

            newUser.setCategoriaList(listaCategorias); // no actualiza la BD pero sí la entity
            usuarioFacade.edit(newUser);
        }
    }
    
    public UsuarioDTO crearUsuario (String nombre, String apellidos, String correo,
                                String password, String domicilio, String ciudad, String sexo,
                                Date fechaNacimiento, Double saldo, String strTipoUsuario, String[] categorias) {
        Usuario usuario = new Usuario();
        
        this.rellenarUsuario(usuario, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario);
        
        this.usuarioFacade.create(usuario);
        
        this.rellenarCategoriasUsuario(categorias, usuario);
        
        return usuario.toDTO();
    }
    
    public UsuarioDTO modificarUsuario (Integer id, String nombre, String apellidos, String correo,
                                String password, String domicilio, String ciudad, String sexo,
                                Date fechaNacimiento, Double saldo, String strTipoUsuario, String[] categorias) {
        Usuario usuario = this.usuarioFacade.find(id);
        
        this.rellenarUsuario(usuario, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario);
        
        this.usuarioFacade.edit(usuario);
        
        this.rellenarCategoriasUsuario(categorias, usuario);
        
        return usuario.toDTO();
    }
    
    public UsuarioDTO comprobarCredenciales (String correo, String password) {
        UsuarioDTO userdto = null;
        
        try {
            Usuario user = usuarioFacade.comprobarUsuario(correo, password);
            userdto = user.toDTO();
        } catch(EJBException ex){
            userdto = null;
        }
        
        return userdto;
    }
    
    public byte[] hashPassword(String password){
        byte[] hash = null;
        
        try {
            //Create Hash algorithm instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            //Set text that's gonna be hashed in UTF-8 encoding
            md.update(password.getBytes("UTF-8"));
            //Apply hash function
            hash = md.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            return hash;
        }
    }
}
