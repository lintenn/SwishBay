/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.UsuarioDTO;
import swishbay.entity.Usuario;

/**
 *
 * @author angel
 */
public class UsuarioCompradorService {
    
    @EJB UsuarioFacade usuarioFacade;
    @EJB UsuarioService usuarioService;
    
    public List<UsuarioDTO> buscarPorCompradorVendedor(){ // angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedor();
        
        return this.listaEntityADTO(usuarios);
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorNombre(String nombre){ // angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByName(nombre);
        
        return this.listaEntityADTO(usuarios);
        
    }
    
    public Integer buscarUsuarioMarketing(){ // angel
        
        Usuario usuario = this.usuarioFacade.findByMarketing();
        
        if(usuario == null){
            this.usuarioService.crearUsuario("Usuario marketing", "Numero 1", "usuarioMarketingNumero1@gmail.com", "123456", null, null, null, null, null, "marketing", null);
        }
        
        return this.usuarioFacade.findByMarketing().getId();
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorCorreo(String correo){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByCorreo(correo);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorApellidos(String apellidos){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByApellidos(apellidos);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorCiudad(String ciudad){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByCiudad(ciudad);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorDomicilio(String domicilio){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByDomicilio(domicilio);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSexo(String sexo){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySexo(sexo);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoDesde(Integer desde, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySaldoDesde(desde, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoHasta(Integer desde, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySaldoHasta(desde, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorNombreQueNoPertencenAUnGrupo(String nombre, List<Integer> ids){ // angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByNameQueNoPertencenAUnGrupo(nombre, ids);
        
        return this.listaEntityADTO(usuarios);
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorCorreoQueNoPertencenAUnGrupo(String correo, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByCorreoQueNoPertencenAUnGrupo(correo, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorApellidosQueNoPertencenAUnGrupo(String apellidos, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByApellidosQueNoPertencenAUnGrupo(apellidos, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorCiudadQueNoPertencenAUnGrupo(String ciudad, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByCiudadQueNoPertencenAUnGrupo(ciudad, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorDomicilioQueNoPertencenAUnGrupo(String domicilio, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByDomicilioQueNoPertencenAUnGrupo(domicilio, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSexoQueNoPertencenAUnGrupo(String sexo, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySexoQueNoPertencenAUnGrupo(sexo, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoDesdeQueNoPertencenAUnGrupo(Integer desde, List<Integer> ids, List<Integer> idsGrupo){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySaldoDesdeQueNoPertencenAUnGrupo(desde, ids, idsGrupo);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoHastaQueNoPertencenAUnGrupo(Integer desde, List<Integer> ids, List<Integer> idsGrupo){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySaldoHastaQueNoPertencenAUnGrupo(desde, ids, idsGrupo);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorNombreQuePertencenAUnGrupo(String nombre, List<Integer> ids){ // angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByNameQuePertencenAUnGrupo(nombre, ids);
        
        return this.listaEntityADTO(usuarios);
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorCorreoQuePertencenAUnGrupo(String correo, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByCorreoQuePertencenAUnGrupo(correo, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorApellidosQuePertencenAUnGrupo(String apellidos, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByApellidosQuePertencenAUnGrupo(apellidos, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorCiudadQuePertencenAUnGrupo(String ciudad, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByCiudadQuePertencenAUnGrupo(ciudad, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorDomicilioQuePertencenAUnGrupo(String domicilio, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByDomicilioQuePertencenAUnGrupo(domicilio, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSexoQuePertencenAUnGrupo(String sexo, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySexoQuePertencenAUnGrupo(sexo, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoDesdeQuePertencenAUnGrupo(Integer desde, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySaldoDesdeQuePertencenAUnGrupo(desde, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoHastaQuePertencenAUnGrupo(Integer desde, List<Integer> ids){// angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorBySaldoHastaQuePertencenAUnGrupo(desde, ids);
        
        return this.listaEntityADTO(usuarios);    
        
    }
    
    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) { // Luis
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario : lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }
    
}
