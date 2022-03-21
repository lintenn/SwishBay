/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author migue
 */
@Entity
@Table(name = "MENSAJE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m")
    , @NamedQuery(name = "Mensaje.findByMarketing", query = "SELECT m FROM Mensaje m WHERE m.mensajePK.marketing = :marketing")
    , @NamedQuery(name = "Mensaje.findByGrupo", query = "SELECT m FROM Mensaje m WHERE m.mensajePK.grupo = :grupo")
    , @NamedQuery(name = "Mensaje.findByAsunto", query = "SELECT m FROM Mensaje m WHERE m.asunto = :asunto")
    , @NamedQuery(name = "Mensaje.findByFecha", query = "SELECT m FROM Mensaje m WHERE m.fecha = :fecha")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MensajePK mensajePK;
    @Size(max = 150)
    @Column(name = "ASUNTO")
    private String asunto;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "CONTENIDO")
    private String contenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "GRUPO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Grupo grupo1;
    @JoinColumn(name = "MARKETING", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Mensaje() {
    }

    public Mensaje(MensajePK mensajePK) {
        this.mensajePK = mensajePK;
    }

    public Mensaje(MensajePK mensajePK, Date fecha) {
        this.mensajePK = mensajePK;
        this.fecha = fecha;
    }

    public Mensaje(int marketing, int grupo) {
        this.mensajePK = new MensajePK(marketing, grupo);
    }

    public MensajePK getMensajePK() {
        return mensajePK;
    }

    public void setMensajePK(MensajePK mensajePK) {
        this.mensajePK = mensajePK;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Grupo getGrupo1() {
        return grupo1;
    }

    public void setGrupo1(Grupo grupo1) {
        this.grupo1 = grupo1;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mensajePK != null ? mensajePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.mensajePK == null && other.mensajePK != null) || (this.mensajePK != null && !this.mensajePK.equals(other.mensajePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "swishbay.entity.Mensaje[ mensajePK=" + mensajePK + " ]";
    }
    
}
