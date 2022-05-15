/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import swishbay.entity.Puja;

/**
 *
 * @author Miguel Oña Guerrero 75%, Galo 25%
 */
@Stateless
public class PujaFacade extends AbstractFacade<Puja> {

    @PersistenceContext(unitName = "SwishBayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PujaFacade() {
        super(Puja.class);
    }

    public Puja findPuja(Integer idUsuario, Integer idProducto) { // Miguel Oña Guerrero
        Query q;   
        q= this.getEntityManager().createQuery("select p from Puja p where p.usuario.id = :usuario and p.producto1.id = :producto");
        q.setParameter("usuario", idUsuario );
        q.setParameter("producto", idProducto );
        
        return (q.getResultList().isEmpty()) ? null : (Puja) q.getResultList().get(0);
    }

    public Puja findMax(Integer pId) { //Galo
        Query q;   
        q= this.getEntityManager().createQuery("select pu from Producto p JOIN p.pujaList pu WHERE p.id = :producto ORDER BY pu.pujaPK.precio DESC ");
        q.setParameter("producto", pId );
        return (q.getResultList().isEmpty()) ? null : (Puja)q.getResultList().get(0);

    }
    
    public Puja findMayor(Integer id){ //Miguel Oña Guerrero
        Query q; 
        q = this.getEntityManager().createQuery("select p from Puja p where p.producto1.id = :id and p.pujaPK.precio = (select max(p.pujaPK.precio) from Puja p where p.producto1.id = :id)");
        q.setParameter("id", id );
        
        return (q.getResultList().isEmpty()) ? null : (Puja) q.getResultList().get(0);
    }   
    
    public List<Puja> findOrdenado(Integer id){ //Miguel Oña Guerrero
        Query q;
        q = this.getEntityManager().createQuery("select p from Puja p where p.producto1.id = :id order by p.pujaPK.precio desc");
        q.setParameter("id", id);
        
        return (List) q.getResultList();
    }
    
}
