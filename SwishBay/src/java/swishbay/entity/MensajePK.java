/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author migue
 */
@Embeddable
public class MensajePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MARKETING")
    private int marketing;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRUPO")
    private int grupo;

    public MensajePK() {
    }

    public MensajePK(int marketing, int grupo) {
        this.marketing = marketing;
        this.grupo = grupo;
    }

    public int getMarketing() {
        return marketing;
    }

    public void setMarketing(int marketing) {
        this.marketing = marketing;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) marketing;
        hash += (int) grupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajePK)) {
            return false;
        }
        MensajePK other = (MensajePK) object;
        if (this.marketing != other.marketing) {
            return false;
        }
        if (this.grupo != other.grupo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "swishbay.entity.MensajePK[ marketing=" + marketing + ", grupo=" + grupo + " ]";
    }
    
}
