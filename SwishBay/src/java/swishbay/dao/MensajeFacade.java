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
    
    public List<Mensaje> findByAsuntoAndIdGrupo (Integer idGrupo, String asunto) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Mensaje m where m.grupo.id = :grupo and m.asunto like :asunto");
        q.setParameter("asunto", '%' + asunto +'%');
        q.setParameter("grupo", idGrupo);
        return q.getResultList();
    }
    
    public List<Mensaje> findByIdGrupo (Integer idGrupo) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Mensaje m where m.grupo.id = :grupo");
        q.setParameter("grupo", idGrupo);
        return q.getResultList();
    }
    
    public List<Mensaje> findByAsuntoAndAreInAUser (String asunto, Integer idUsuario) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Usuario u Join u.mensajeList m where u.id = :idUsuario and m.asunto like :asunto");
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("asunto", '%' + asunto + '%');
        return q.getResultList();
    }
    
    public List<Mensaje> findByAsuntoAndIdGrupoByMessages (Integer idGrupo, String asunto, List<Integer> mensajes) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Mensaje m where m.grupo.id = :grupo and m.asunto like :asunto and m.id IN :mensajes");
        q.setParameter("asunto", '%' + asunto +'%');
        q.setParameter("grupo", idGrupo);
        q.setParameter("mensajes", mensajes);
        return q.getResultList();
    }
    
    public List<Mensaje> findByContenidoAndIdGrupoByMessages (Integer idGrupo, String contenido, List<Integer> mensajes) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Mensaje m where m.grupo.id = :grupo and m.contenido like :contenido and m.id IN :mensajes");
        q.setParameter("contenido", '%' + contenido +'%');
        q.setParameter("grupo", idGrupo);
        q.setParameter("mensajes", mensajes);
        return q.getResultList();
    }
    
    public List<Mensaje> findByAsuntoAndIdUserByMessages (Integer idUsuario, String asunto, List<Integer> mensajes) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Usuario u Join u.mensajeList m where u.id = :idUsuario and m.asunto like :asunto and m.id IN :mensajes");
        q.setParameter("asunto", '%' + asunto +'%');
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("mensajes", mensajes);
        return q.getResultList();
    }
    
    public List<Mensaje> findByContenidoAndIdUserByMessages (Integer idUsuario, String contenido, List<Integer> mensajes) {
        Query q;
        q = this.getEntityManager().createQuery("select m from Usuario u Join u.mensajeList m where u.id = :idUsuario and m.contenido like :contenido and m.id IN :mensajes");
        q.setParameter("contenido", '%' + contenido +'%');
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("mensajes", mensajes);
        return q.getResultList();
    }
    
}
