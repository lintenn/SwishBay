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
 * @author Miguel Oña Guerrero
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

    public List<Producto> findAll(String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroCategoria");
        q.setParameter("filtroCategoria",  filtroCategoria);
        return q.getResultList();
    }

    public List<Producto> findByNombre(String filtroNombre, String filtroCategoria) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        return q.getResultList(); 
    }
    
    /*
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
    */
    
    public List<Producto> findAllByFiltro(String filtroNombre, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        return q.getResultList(); 
    }
    
    public List<Producto> findEnPujaByFiltro(String filtroNombre, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.enPuja = 1");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        return q.getResultList(); 
    }
    
    public List<Producto> findCompradosByFiltro(String filtroNombre, String filtroCategoria, int usuario) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.comprador.id = :id");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        return q.getResultList(); 
    }
    
    public Producto findByID(int id) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.id = :id");
        q.setParameter("id", id);
        
        Producto producto = (Producto)q.getResultList().get(0);
        return producto; 
    }
    
    /*
    public List<Producto> findFavoritosByFiltro(String filtroNombre, String filtroCategoria, int usuario) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.usuarioList.id = :id");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        return q.getResultList(); 
    }
    */
}
