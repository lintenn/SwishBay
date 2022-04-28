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
import swishbay.entity.Usuario;

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

    public List<Producto> findVendidos(Usuario user) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor=:user");
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Object[]> findVendidosAUser(Usuario user) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor=:user and p.comprador is not null GROUP BY p");
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Producto> findVendidosFiltered(Usuario user, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.vendedor=:user");
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Object[]> findVendidosAUserFiltered(Usuario user, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor=:user and p.categoria.nombre like :filtroCategoria and p.comprador is not null GROUP BY p");
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Producto> findByNombre(String filtroNombre, String filtroCategoria) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        return q.getResultList(); 
    }
    
    public List<Producto> findVendidosByNombre(Usuario user,String filtroNombre) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.vendedor=:user and p.comprador is not null");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Object[]> findVendidosAUserByNombre(Usuario user,String filtroNombre) {
        Query q;   
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor=:user and p.titulo like :filtroNombre and p.comprador is not null GROUP BY p");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findVendidosByNombreFiltered(Usuario user,String filtroNombre, String filtroCategoria) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.vendedor=:user");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Object[]> findVendidosAUserByNombreFiltered(Usuario user,String filtroNombre, String filtroCategoria) {
        Query q;   
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor=:user and p.titulo like :filtroNombre and p.categoria.nombre like :filtroCategoria and p.comprador is not null BY p");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
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
    
    public List<Producto> findEnPuja(Usuario user) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor= :user and p.enPuja = 1");
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findEnPujaByFiltro(String filtroNombre, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.enPuja = 1");
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("titulo", '%' + filtroNombre + '%');
        return q.getResultList(); 
    }
    
    public List<Producto> findEnPujaFiltered(Usuario user, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor= :user and p.categoria.nombre like :filtroCategoria and p.enPuja = 1");
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findEnPujaByNombre(Usuario user, String filtroNombre) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor= :user and p.titulo like :titulo and p.enPuja = 1");
        q.setParameter("titulo",  '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findEnPujaByNombreFiltered(Usuario user, String filtroNombre, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.vendedor= :user and p.categoria.nombre like :filtroCategoria and p.enPuja = 1");
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
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
    }*/
    
    public List<Producto> findFavoritosByFiltro(String filtroNombre, String filtroCategoria, int usuario) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p"
                + " JOIN p.usuarioList u where p. = u.id and u.id = :id");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        return q.getResultList(); 
    }
}
