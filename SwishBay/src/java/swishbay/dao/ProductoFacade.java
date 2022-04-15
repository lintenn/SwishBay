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

/**
 *
 * @author migue
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "SwishBayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public List<Producto> findByNombre(String titulo) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo");
        q.setParameter("titulo", '%' + titulo + '%');
        return q.getResultList(); 
    }

    public List<Producto> findAll(String filtroNombre) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroNombre");
        q.setParameter("filtroNombre",  filtroNombre);
        return q.getResultList();
    }

    public List<Producto> findByNombre(String filtroNombre, String filtroCategoria) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        return q.getResultList(); 
    }
    
    public List<Producto> findEnPuja() {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.enPuja = 1");
        return q.getResultList(); 
    }
    
    public List<Producto> findByComprador(int id){
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.comprador.id = :id");
        q.setParameter("id", id);
        return q.getResultList(); 
    }
}
