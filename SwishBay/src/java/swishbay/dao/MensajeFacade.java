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
import swishbay.entity.Mensaje;

/**
 *
 * @author Ángel
 */
@Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> {

    @PersistenceContext(unitName = "SwishBayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeFacade() {
        super(Mensaje.class);
    }
    
    public List<Mensaje> findByAsunto (String asunto) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Mensaje m where m.asunto like :asunto");
        q.setParameter("asunto", '%' + asunto +'%');
        return q.getResultList();
    }
    
    public List<Mensaje> findByIdGrupo (Integer idGrupo) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Mensaje m where m.grupo.id = :grupo");
        q.setParameter("grupo", idGrupo);
        return q.getResultList();
    }
    
}
