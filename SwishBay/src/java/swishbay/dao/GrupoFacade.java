/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.dao;

import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import swishbay.entity.Grupo;
import swishbay.entity.Usuario;

/**
 *
 * @author Ángel
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
    
    public List<Grupo> findGrupoByGrupoNombre (String nombre) { // angel
        Query q;
        q = this.getEntityManager().createQuery("select g from Grupo g where g.nombre like :nombre");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
    public List<Usuario> findUsuariosByGrupoId (Integer id) { // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Grupo g JOIN g.usuarioList u where g.id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public List<Usuario> findUsuariosByGrupoIdAndGrupoNombre (Integer id, String nombre) { // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Grupo g JOIN g.usuarioList u where g.id = :id and u.nombre like :nombre");
        q.setParameter("id", id);
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
    public List<Usuario> findUsuariosNotInGrupoId (List<Integer> idsUsuarios) { // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.id NOT IN :idsUsuarios");
        q.setParameter("idsUsuarios", idsUsuarios);
        return q.getResultList();
    }
    
    public List<Usuario> findUsuariosNotInGrupoIdByNombre (List<Integer> idsUsuarios, String nombre) { // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.nombre like :nombre and u.id NOT IN :idsUsuarios");
        q.setParameter("idsUsuarios", idsUsuarios);
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
}
