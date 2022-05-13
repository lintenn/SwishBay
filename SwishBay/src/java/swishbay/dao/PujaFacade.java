/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import swishbay.entity.Puja;

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
        q= this.getEntityManager().createQuery("select pu from Producto p JOIN p.pujaList pu WHERE p.id = :producto ORDER BY pu.precio DESC ");
        q.setParameter("producto", pId );
        return (q.getResultList().isEmpty()) ? null : (Puja)q.getResultList().get(0);

    }

    
}
