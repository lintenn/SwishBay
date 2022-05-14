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
 * @author Luis 56% , Galo 11%, Angel 33%
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
    
    public Usuario findByCorreo (String correo) { // Luis
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
    
    public Usuario comprobarUsuario (String strcorreo, String strclave) { // Luis
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
    
    public List<Usuario> findByNombre (String nombre) { // Luis
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.nombre like :nombre");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
    public List<Usuario> findAll (String filtroRol) { // Luis
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like :filtroRol");
        q.setParameter("filtroRol",  filtroRol);
        return q.getResultList();
    }
    
    public List<Usuario> findByNombre (String filtroNombre, String filtroRol) { // Luis
        Query q;   
        q = this.getEntityManager().createQuery("select u from Usuario u where u.nombre like :nombre and u.rol.nombre like :filtroRol");
        q.setParameter("nombre", '%' + filtroNombre + '%');
        q.setParameter("filtroRol",  filtroRol);
        return q.getResultList(); 
    }
    
    public Usuario findByID(int id){ // Galo
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.id = :id");
        q.setParameter("id", id);
        
        return (Usuario) q.getResultList().get(0);
    }
    
    public List<Usuario> findByCompradorVendedor(){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor'");
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByName(String nombre){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.nombre like :nombre");
        q.setParameter("nombre", '%' + nombre +'%');
        
        return q.getResultList();
    }
    
    public Usuario findByMarketing(){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'marketing'");
        List<Usuario> marketings = q.getResultList();
        
        return (marketings==null || marketings.isEmpty()) ? null : marketings.get(0);
    }
}
