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

    public List<Producto> findVendidos(int user) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor.id= :user");
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Object[]> findVendidosAUser(int user) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.comprador is not null GROUP BY p");
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Producto> findVendidosFiltered(int user, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user");
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Object[]> findVendidosAUserFiltered(int user, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.categoria.nombre like :filtroCategoria and p.comprador is not null GROUP BY p");
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
    
    public List<Producto> findVendidosByNombre(int user,String filtroNombre) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.vendedor.id=:user");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Object[]> findVendidosAUserByNombre(int user,String filtroNombre) {
        Query q;   
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.titulo like :titulo and p.comprador is not null GROUP BY p");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findVendidosByNombreFiltered(int user,String filtroNombre, String filtroCategoria) {
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Object[]> findVendidosAUserByNombreFiltered(int user,String filtroNombre, String filtroCategoria) {
        Query q;   
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.titulo like :filtroNombre and p.categoria.nombre like :filtroCategoria and p.comprador is not null BY p");
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
    
    public List<Object[]> findEnPuja(int user) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p LEFT JOIN p.pujaList pu where p.enPuja=1 and p.vendedor.id= :user GROUP BY p");
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
    
    public List<Object[]> findEnPujaFiltered(int user, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p LEFT JOIN p.pujaList pu where p.categoria.nombre like :filtroCategoria and p.enPuja=1 and p.vendedor.id= :user GROUP BY p");
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Object[]> findEnPujaByNombre(int user, String filtroNombre) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p LEFT JOIN p.pujaList pu where p.titulo like :titulo and p.enPuja=1 and p.vendedor.id= :user GROUP BY p");
        q.setParameter("titulo",  '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Object[]> findEnPujaByNombreFiltered(int user, String filtroNombre, String filtroCategoria) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p LEFT JOIN p.pujaList pu where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.enPuja=1 and p.vendedor.id= :user GROUP BY p");
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
    
    public Double findPrecioMax(String id){
        
        Query q;
        q = this.getEntityManager().createQuery("select MAX(pu.precio) from Producto p LEFT JOIN p.pujaList pu where p.id=:id Group by p");
        q.setParameter("id", Integer.parseInt(id));
        Double d = (Double) q.getSingleResult();
        return d;
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
    
    public List<Producto> findExistentesByFiltro(String filtroTitulo, String filtroCategoria, int usuario) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.vendedor.id != :id");
        q.setParameter("titulo", '%' + filtroTitulo + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        return q.getResultList(); 
    }
}
