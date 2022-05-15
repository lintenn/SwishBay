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
 * @author Miguel Oña Guerrero 66%, Galo 33%
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

    public Puja findPuja(Integer uId, Integer pId) { // Miguel Oña Guerrero
        Query q;   
        q= this.getEntityManager().createQuery("select p from Puja p where p.usuario= :usuario and p.producto1= :producto");
        q.setParameter("usuario", uId );
        q.setParameter("producto", pId );
        return (Puja) q.getSingleResult();
    }

    public Puja findMax(Integer pId) { //Galo
        Query q;   
        q= this.getEntityManager().createQuery("select pu from Producto p JOIN p.pujaList pu WHERE p.id = :producto ORDER BY pu.precio DESC ");
        q.setParameter("producto", pId );
        return (q.getResultList().isEmpty()) ? null : (Puja)q.getResultList().get(0);

    }
    
    public Puja findMayor(Integer id){ //Miguel Oña Guerrero
        Query q; 
        q = this.getEntityManager().createQuery("select p from Puja p where p.producto1.id = :id and p.precio = (select max(p.precio) from Puja p where p.producto1.id = :id)");
        q.setParameter("id", id );
        
        return (q.getResultList().isEmpty()) ? null : (Puja) q.getResultList().get(0);
    }   
    
    public List<Puja> findOrdenado(Integer id){ //Miguel Oña Guerrero
        Query q;
        q = this.getEntityManager().createQuery("select p from Puja p where p.producto1.id = :id order by p.precio desc");
        q.setParameter("id", id);
        
        return (List) q.getResultList();
    }
    
}
