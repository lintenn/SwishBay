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
import swishbay.entity.Grupo;
import swishbay.entity.Usuario;

/**
 *
 * @author migue
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {

    @PersistenceContext(unitName = "SwishBayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
    public List<Grupo> findByNombre (String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select g from Grupo g where g.nombre like :nombre");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
    public List<Usuario> findById (Integer id) {
        Query q;
        q = this.getEntityManager().createQuery("select u from Grupo g JOIN g.usuarioList u where g.id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public List<Usuario> findByIdAndNombre (Integer id, String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select u from Grupo g JOIN g.usuarioList u where g.id = :id and u.nombre like :nombre");
        q.setParameter("id", id);
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
    public List<Usuario> findByIdNotAre (Integer id) {
        Query q;
        q = this.getEntityManager().createQuery("select u from Grupo g JOIN g.usuarioList ug where g.id = :id and u.nombre like :nombre");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
}
