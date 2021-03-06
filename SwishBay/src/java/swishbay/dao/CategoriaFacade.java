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
import swishbay.entity.Categoria;

/**
 *
 * @author Luis 50%, Galo 50%
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "SwishBayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public List<Categoria> findByNombre (String nombre) { // Luis
        Query q;
        q = this.getEntityManager().createQuery("select c from Categoria c where c.nombre like :nombre");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
    public Categoria findByName(String nombre) { // Galo
        Query q;
        q = this.getEntityManager().createQuery("select c from Categoria c where c.nombre like :nombre");
        q.setParameter("nombre",  nombre );
        
        return (q.getResultList()==null || q.getResultList().isEmpty()) ? null: (Categoria) q.getResultList().get(0);
    }

}
