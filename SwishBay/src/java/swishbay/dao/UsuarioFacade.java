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
import swishbay.entity.Usuario;

/**
 *
 * @author Luis
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SwishBayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findByCorreo (String correo) {
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.correo like :correo");
        q.setParameter("correo", correo );
        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }    
    }
    
    public Usuario comprobarUsuario (String strcorreo, String strclave) {
        Query q;
        
        q = this.getEntityManager().createQuery("select u from Usuario u where u.correo = :correo and"
                + " u.password = :clave");
        q.setParameter("correo", strcorreo);
        q.setParameter("clave", strclave);
        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }        
    }
    
    public List<Usuario> findByNombre (String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.nombre like :nombre");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
}
