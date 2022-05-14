/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.GrupoFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.GrupoDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.entity.Grupo;
import swishbay.entity.Usuario;

/**
 *
 * @author angel
 */
@Stateless
public class GrupoService {
    
    @EJB GrupoFacade grupoFacade;
    @EJB UsuarioFacade usuarioFacade;
    
    private Grupo buscarGrupo(Integer id){ // angel
        
        Grupo grupo = this.grupoFacade.find(id);
        
        return grupo;
        
    }
    
    public GrupoDTO buscarGrupoDTO(Integer id){ // angel
        
        GrupoDTO grupoDTO = this.buscarGrupo(id).toDTO();
        
        return grupoDTO;
        
    }
    
    public void borrarGrupo(Integer id){ // angel
        
        Grupo grupo = this.buscarGrupo(id);
        
        this.grupoFacade.remove(grupo);
        
    }
    
    private void rellenarGrupo(Grupo grupo, String nombre, Usuario marketing){ // angel
        
        grupo.setNombre(nombre);
        grupo.setMarketing(marketing);
        
    }
    
    public void crearGrupo(String nombre, Integer idMarketing){ // angel
        
        Grupo grupo = new Grupo();
        
        Usuario marketing = this.usuarioFacade.find(idMarketing);
        
        this.rellenarGrupo(grupo, nombre, marketing);
        
        this.grupoFacade.create(grupo);
        
    }
    
    public void editarGrupo(Integer id, String nombre, Integer idMarketing){ // angel
     
        Grupo grupo = this.buscarGrupo(id);
        
        Usuario marketing = this.usuarioFacade.find(idMarketing);
        
        this.rellenarGrupo(grupo, nombre, marketing);
        
        this.grupoFacade.edit(grupo);
        
    }
    
    public List<GrupoDTO> buscarTodosGrupos(){ // angel
        
        List<Grupo> grupos = this.grupoFacade.findAll();
        
        return this.listaGruposEntityADTO(grupos);
        
    }
    
    public List<GrupoDTO> buscarGruposPorNombre(String nombre){ // angel
        
        List<Grupo> grupos = this.grupoFacade.findGrupoByGrupoNombre(nombre);
        
        return this.listaGruposEntityADTO(grupos);
        
    }
    
    private List<GrupoDTO> listaGruposEntityADTO(List<Grupo> grupos){ // angel
        
        List<GrupoDTO> gruposDTO = null;
        
        if(grupos != null) {
            gruposDTO = new ArrayList<>();
            for(Grupo grupo : grupos) {
                gruposDTO.add(grupo.toDTO());
            }
        }
        
        return gruposDTO;
        
    }
    
    public void añadirUsuarioAListaUsuariosGrupo(Integer idUsuario, Integer idGrupo){ // angel
        
        Usuario usuario = this.usuarioFacade.find(idUsuario);
        Grupo grupo = this.buscarGrupo(idGrupo);
        
        List<Usuario> usuarioList = grupo.getUsuarioList();
        usuarioList.add(usuario);
        grupo.setUsuarioList(usuarioList);
        
        this.grupoFacade.edit(grupo);
        
    }
    
    public void eliminarUsuarioAListaUsuariosGrupo(Integer idUsuario, Integer idGrupo){ // angel
        
        Usuario usuario = this.usuarioFacade.find(idUsuario);
        Grupo grupo = this.buscarGrupo(idGrupo);
        
        List<Usuario> usuarioList = grupo.getUsuarioList();
        usuarioList.remove(usuario);
        grupo.setUsuarioList(usuarioList);
        
        this.grupoFacade.edit(grupo);
        
    }
    
    public List<UsuarioDTO> listarUsuariosQueNoPertenecenAUnGrupo(Integer idGrupo){ // angel
        
        List<Integer> ids = this.listarIdsUsuariosDeUnGrupo(idGrupo);
                
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedor();
        
        if(ids.size() != 0){
            usuarios = this.grupoFacade.findUsuariosNotInGrupoId(ids);
        }
        
        return this.listaUsuarioEntityADTO(usuarios);
        
    }
    
    public List<UsuarioDTO> listarUsuariosQueNoPertenecenAUnGrupoPorNombre(Integer idGrupo, String nombre){ // angel
        
        List<Integer> ids = this.listarIdsUsuariosDeUnGrupo(idGrupo);
        
        List<Usuario> usuarios = this.grupoFacade.findUsuariosNotInGrupoIdByNombre(ids, nombre);
        
        return this.listaUsuarioEntityADTO(usuarios);
        
    }
    
    public List<UsuarioDTO> listarUsuariosDeUnGrupo(Integer idGrupo){ // angel
        
        List<Usuario> usuarios = this.grupoFacade.findUsuariosByGrupoId(idGrupo);
        
        return this.listaUsuarioEntityADTO(usuarios);
        
    }
    
    public List<UsuarioDTO> listarUsuariosDeUnGrupoPorNombre(Integer idGrupo, String nombre){ // angel
        
        List<Usuario> usuarios = this.grupoFacade.findUsuariosByGrupoIdAndGrupoNombre(idGrupo, nombre);
        
        return this.listaUsuarioEntityADTO(usuarios);
        
    }
    
    private List<Integer> listarIdsUsuariosDeUnGrupo(Integer idGrupo){ // angel
        
        List<UsuarioDTO> usuarios = this.listarUsuariosDeUnGrupo(idGrupo);
        
        List<Integer> ids = new ArrayList<>();
        
        for(UsuarioDTO usuario : usuarios){
            ids.add(usuario.getId());
        }
        
        return ids;
        
    }
    
    private List<UsuarioDTO> listaUsuarioEntityADTO (List<Usuario> lista) { // angel
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
