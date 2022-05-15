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
 * @author Miguel Oña Guerrero
 */
@Embeddable
public class PujaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPRADOR")
    private int comprador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCTO")
    private int producto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;

    public PujaPK() {
    }

    public PujaPK(int comprador, int producto, double precio) {
        this.comprador = comprador;
        this.producto = producto;
        this.precio = precio;
    }

    public int getComprador() {
        return comprador;
    }

    public void setComprador(int comprador) {
        this.comprador = comprador;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) comprador;
        hash += (int) producto;
        hash += (int) precio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PujaPK)) {
            return false;
        }
        PujaPK other = (PujaPK) object;
        if (this.comprador != other.comprador) {
            return false;
        }
        if (this.producto != other.producto) {
            return false;
        }
        if (this.precio != other.precio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "swishbay.entity.PujaPK[ comprador=" + comprador + ", producto=" + producto + ", precio=" + precio + " ]";
    }
    
}
