/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author angel
 */
public class MensajeDTO {
    
    private Integer id;
    private String asunto;
    private String contenido;
    private Date fecha;
    private List<UsuarioDTO> usuarioList;
    private GrupoDTO grupo;
    private UsuarioDTO marketing;
    
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
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * @return the grupo
     */
    public GrupoDTO getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(GrupoDTO grupo) {
        this.grupo = grupo;
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
}