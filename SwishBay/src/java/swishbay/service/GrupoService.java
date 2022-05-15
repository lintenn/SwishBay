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
import swishbay.dao.ProductoFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.GrupoDTO;
import swishbay.dto.ProductoDTO;
import swishbay.dto.PujaDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.entity.Grupo;
import swishbay.entity.Puja;
import swishbay.entity.Usuario;

/**
 *
 * @author angel
 */
@Stateless
public class GrupoService {
    
    @EJB GrupoFacade grupoFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB UsuarioService usuarioService;
    @EJB MensajeService mensajeService;
    @EJB ProductoFacade productoFacade;
    
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
    
    public void anadirUsuarioAListaUsuariosGrupo(Integer idUsuario, Integer idGrupo){ // angel
        
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
    
    public List<UsuarioDTO> listarUsuariosQueNoPertenecenAUnGrupo(List<Integer> ids){ // angel
        
                
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedor();
        
        if(!ids.isEmpty()){
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
    
    public List<Integer> listarIdsUsuariosDeUnGrupo(Integer idGrupo){ // angel
        
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
    
    public void comprobarExistenciaGrupoPorNombre(String nombre){ // angel
        
        List<GrupoDTO> grupos = this.buscarGruposPorNombre(nombre);
        
        if(grupos == null || grupos.isEmpty()){
            
            this.crearGrupo(nombre, this.usuarioService.buscarUsuarioMarketing());
            
        }
        
    }
    
    public void notificarComienzoPuja(String nombre, ProductoDTO producto){ // angel
        
        this.comprobarExistenciaGrupoPorNombre(nombre);
        
        GrupoDTO grupo = this.buscarGruposPorNombre(nombre).get(0);
        
        this.mensajeService.crearMensaje(grupo.getId(), grupo.getMarketing().getId(), "Inicio de puja de " + producto.getTitulo(), "Se ha iniciado una nueva puja del producto " + producto.getTitulo() + ".");
        
    }
    
    public void notificarFinPuja(String nombre, ProductoDTO producto){ // angel
        
        GrupoDTO grupo = this.buscarGruposPorNombre(nombre).get(0);
        
        List<Integer> usuariosGrupo = new ArrayList<>();
        
        for(UsuarioDTO usuario : grupo.getUsuarioList()){
            usuariosGrupo.add(usuario.getId());
        }
        
        List<PujaDTO> usuariosPujaNoGrupo = null;
        
        if(usuariosGrupo == null || usuariosGrupo.isEmpty()){
            usuariosPujaNoGrupo = producto.getPujaList();
        } else {
            usuariosPujaNoGrupo = this.listaPujasEntityADTO(this.productoFacade.findUsersPujaNoGrupo(producto.getId(), usuariosGrupo));
        }
        
        for(PujaDTO puja : usuariosPujaNoGrupo){
            
            this.usuarioService.anadirUsuarioAGrupoADarleFavoritoAProducto(producto.getId(), puja.getComprador().getId());
            
        }
        
        if(producto.getPujaList() == null || producto.getPujaList().isEmpty()){
            
            this.mensajeService.crearMensaje(grupo.getId(), grupo.getMarketing().getId(), "Fin de puja de " + producto.getTitulo(), "Se ha terminado la puja del producto " + producto.getTitulo() + ". Nadie ha ganado la puja.");

        } else {
           
            UsuarioDTO ganadorPuja = producto.getPujaList().get(producto.getPujaList().size() - 1).getComprador();
            this.mensajeService.crearMensaje(grupo.getId(), grupo.getMarketing().getId(), "Fin de puja de " + producto.getTitulo(), "Se ha terminado la puja del producto " + producto.getTitulo() + ". " + ganadorPuja.getNombre() + " " + ganadorPuja.getApellidos() + " ha sido el ganador de la puja.");
            
        }
        
        for(PujaDTO puja : usuariosPujaNoGrupo){
            
            this.usuarioService.eliminarUsuarioAGrupoADarleFavoritoAProducto(producto.getId(), puja.getComprador().getId());
            
        }
                
    }
    
    private List<PujaDTO> listaPujasEntityADTO(List<Puja> pujas){ // angel
        
        List<PujaDTO> pujasDTO = null;
        
        if(pujas != null) {
            pujasDTO = new ArrayList<>();
            for(Puja puja : pujas) {
                pujasDTO.add(puja.toDTO());
            }
        }
        
        return pujasDTO;
        
    }
    
    public List<GrupoDTO> buscarGruposPorNombreYGrupos(String nombre, List<Integer> ids){ // angel
        
        List<Grupo> grupos = this.grupoFacade.findGrupoByGrupoNombreAndGroups(nombre, ids);
        
        return this.listaGruposEntityADTO(grupos);
        
    }
    
    public List<GrupoDTO> buscarGruposPorNombreYApellidosCreador(String nombre, String apellidos, List<Integer> ids){ // angel
        
        List<Grupo> grupos = this.grupoFacade.findGrupoByGrupoNombreCreadorAndApellidosCreadorAndGroups(nombre, apellidos, ids);
        
        return this.listaGruposEntityADTO(grupos);
        
    }
    
    public List<GrupoDTO> buscarGruposPorNombreCreador(String nombre, List<Integer> ids){ // angel
        
        List<Grupo> grupos = this.grupoFacade.findGrupoByGrupoNombreCreadorAndGroups(nombre, ids);
        
        return this.listaGruposEntityADTO(grupos);
        
    }
    
    public List<GrupoDTO> buscarGruposPorApellidosCreador(String apellidos, List<Integer> ids){ // angel
        
        List<Grupo> grupos = this.grupoFacade.findGrupoByGrupoApellidosCreadorAndGroups(apellidos, ids);
        
        return this.listaGruposEntityADTO(grupos);
        
    }
}
