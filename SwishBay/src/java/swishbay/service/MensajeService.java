/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.GrupoFacade;
import swishbay.dao.MensajeFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.MensajeDTO;
import swishbay.entity.Grupo;
import swishbay.entity.Mensaje;
import swishbay.entity.Usuario;

/**
 *
 * @author angel
 */
@Stateless
public class MensajeService {
    
     @EJB MensajeFacade mensajeFacade;
     @EJB GrupoFacade grupoFacade;
     @EJB UsuarioService usuarioService;
     @EJB UsuarioFacade usuarioFacade;
     
     public List<MensajeDTO> buscarMensajesPorIdGrupo(Integer id){
         
         List<Mensaje> mensajes = this.mensajeFacade.findByIdGrupo(id);
         
         return this.listaMensajesEntityADTO(mensajes);
         
     }
    
    private List<MensajeDTO> listaMensajesEntityADTO(List<Mensaje> mensajes){ // angel
        
        List<MensajeDTO> mensajesDTO = null;
        
        if(mensajes != null) {
            mensajesDTO = new ArrayList<>();
            for(Mensaje mensaje : mensajes) {
                mensajesDTO.add(mensaje.toDTO());
            }
        }
        
        return mensajesDTO;
        
    }
     
     public List<MensajeDTO> buscarMensajesPorIdGrupoYPorAsunto(Integer id, String asunto){ // angel
         
         List<Mensaje> mensajes = this.mensajeFacade.findByAsuntoAndIdGrupo(id, asunto);
         
         return this.listaMensajesEntityADTO(mensajes);
         
     }
     
     private Mensaje buscarMensaje(Integer id){ // angel
         
         Mensaje mensaje = this.mensajeFacade.find(id);
         
         return mensaje;
         
     }
     
     public MensajeDTO buscarMensajeDTO(Integer id){ // angel
         
         Mensaje mensaje = this.mensajeFacade.find(id);
         
         return mensaje.toDTO();
         
     }
     
     public void borrarMensaje(Integer id){ // angel
         
         Mensaje mensaje = this.buscarMensaje(id);
         
         this.mensajeFacade.remove(mensaje);
         
     }
     
     private void rellenarMensaje(Mensaje mensaje, String asunto, String contenido){ // angel
         
         mensaje.setAsunto(asunto);
         mensaje.setContenido(contenido);
         
     }
     
     public void crearMensaje(Integer idGrupo, Integer idMarketing, String asunto, String contenido){ // angel
         
         Mensaje mensaje = new Mensaje();
         Grupo grupo = this.grupoFacade.find(idGrupo);
         Usuario marketing = this.usuarioFacade.find(idMarketing);
         mensaje.setGrupo(grupo);
         mensaje.setMarketing(marketing);
         mensaje.setFecha(new Date());
         mensaje.setUsuarioList(new ArrayList<Usuario>());
         this.rellenarMensaje(mensaje, asunto, contenido);
         
         this.mensajeFacade.create(mensaje);
         
         for(Usuario usuario : grupo.getUsuarioList()){
             List<Mensaje> mensajeList = usuario.getMensajeList();
             mensajeList.add(mensaje);
             this.usuarioService.modificarListaMensajesUsuario(usuario.getId(), mensajeList);
         };
     }
     
     public void modificarMensaje(Integer id, String asunto, String contenido){ // angel
         
         Mensaje mensaje = this.buscarMensaje(id);
         
         this.rellenarMensaje(mensaje, asunto, contenido);
         
         this.mensajeFacade.edit(mensaje);
         
     }
     
     public List<MensajeDTO> listarMensajesDeUnUsuarioPorAsunto(String asunto, Integer idUsuario){
         
         List<Mensaje> mensajes = this.mensajeFacade.findByAsuntoAndAreInAUser(asunto, idUsuario);
         
         return this.listaMensajesEntityADTO(mensajes);
                 
     }
     
     public List<MensajeDTO> listarMensajesDeUnGrupoPorAsuntoPorMensajes(String asunto, Integer idGrupo, List<Integer> ids){
         
         List<Mensaje> mensajes = this.mensajeFacade.findByAsuntoAndIdGrupoByMessages(idGrupo, asunto, ids);
         
         return this.listaMensajesEntityADTO(mensajes);
                 
     }
     
     public List<MensajeDTO> listarMensajesDeUnGrupoPorContenidoPorMensajes(String contenido, Integer idGrupo, List<Integer> ids){
         
         List<Mensaje> mensajes = this.mensajeFacade.findByContenidoAndIdGrupoByMessages(idGrupo, contenido, ids);
         
         return this.listaMensajesEntityADTO(mensajes);
                 
     }
     
     public List<MensajeDTO> listarMensajesDeUnUsuarioPorAsuntoPorMensajes(String asunto, Integer idUsuario, List<Integer> ids){
         
         List<Mensaje> mensajes = this.mensajeFacade.findByAsuntoAndIdUserByMessages(idUsuario, asunto, ids);
         
         return this.listaMensajesEntityADTO(mensajes);
                 
     }
     
     public List<MensajeDTO> listarMensajesDeUnUsuarioPorContenidoPorMensajes(String contenido, Integer idUsuario, List<Integer> ids){
         
         List<Mensaje> mensajes = this.mensajeFacade.findByContenidoAndIdUserByMessages(idUsuario, contenido, ids);
         
         return this.listaMensajesEntityADTO(mensajes);
                 
     }
}
