/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.dto;

import java.util.List;

/**
 *
 * @author angel
 */
public class GrupoDTO {
    
    private Integer id;
    private String nombre;
    private List<UsuarioDTO> usuarioList;
    private UsuarioDTO marketing;
    private List<MensajeDTO> mensajeList;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the usuarioList
     */
    public List<UsuarioDTO> getUsuarioList() {
        return usuarioList;
    }

    /**
     * @param usuarioList the usuarioList to set
     */
    public void setUsuarioList(List<UsuarioDTO> usuarioList) {
        this.usuarioList = usuarioList;
    }

    /**
     * @return the marketing
     */
    public UsuarioDTO getMarketing() {
        return marketing;
    }

    /**
     * @param marketing the marketing to set
     */
    public void setMarketing(UsuarioDTO marketing) {
        this.marketing = marketing;
    }

    /**
     * @return the mensajeList
     */
    public List<MensajeDTO> getMensajeList() {
        return mensajeList;
    }

    /**
     * @param mensajeList the mensajeList to set
     */
    public void setMensajeList(List<MensajeDTO> mensajeList) {
        this.mensajeList = mensajeList;
    }
}
