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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.UsuarioDTO;
import swishbay.entity.Usuario;

/**
 *
 * @author Luis
 */
@Stateless
public class UsuarioService {
    
    @EJB UsuarioFacade usuarioFacade;
    
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
