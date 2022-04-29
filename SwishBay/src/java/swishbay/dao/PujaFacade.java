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
import swishbay.entity.Producto;
import swishbay.entity.Puja;
import swishbay.entity.Usuario;

/**
 *
 * @author migue
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

    public Puja findPuja(Integer uId, Integer pId) {
        Query q;   
        q= this.getEntityManager().createQuery("select p from Puja p where p.usuario= :usuario and p.producto1= :producto");
        q.setParameter("usuario", uId );
        q.setParameter("producto", pId );
        return (Puja) q.getSingleResult();
    }

    public Puja findMax(Integer pId) {
        Query q;   
        q= this.getEntityManager().createQuery("select p from Puja p JOIN Puja pu where p.producto1.id = :producto and pu.producto1.id!= :producto and p.precio>pu.precio");
        q.setParameter("producto", pId );
        return (Puja) q.getSingleResult();

    }

    
}
