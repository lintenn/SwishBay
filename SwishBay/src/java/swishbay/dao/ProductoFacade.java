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
import swishbay.entity.Puja;
import swishbay.entity.PujaPK;

/**
 *
 * @author Miguel Oña Guerrero, Luis 25%, Galo
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

    public List<Producto> findByNombre(String titulo) { // Luis
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo");
        q.setParameter("titulo", '%' + titulo + '%');
        return q.getResultList(); 
    }

    public List<Producto> findAll(String filtroCategoria) { // Luis
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroCategoria");
        q.setParameter("filtroCategoria",  filtroCategoria);
        return q.getResultList();
    }
    
    public List<Producto> findByNombre(String filtroNombre, String filtroCategoria) { // Luis
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        return q.getResultList(); 
    }
    
    public List<Producto> findAllDesde(String filtroDesde, String filtroHasta) { // Luis
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.precioSalida >= :desde and p.precioSalida <= :hasta");
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
        return q.getResultList();
    }
    
    public List<Producto> findAllFilteredDesde(String filtroCategoria, String filtroDesde, String filtroHasta) { // Luis
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.precioSalida >= :desde and p.precioSalida <= :hasta");
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
        return q.getResultList();
    }
    
    public List<Producto> findByNombreDesde(String filtroNombre, String filtroDesde, String filtroHasta) { // Luis
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.precioSalida >= :desde and p.precioSalida <= :hasta");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
        return q.getResultList(); 
    }
    
    public List<Producto> findByNombreFilteredDesde(String filtroNombre, String filtroCategoria, String filtroDesde, String filtroHasta) { // Luis
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.precioSalida >= :desde and p.precioSalida <= :hasta");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
        return q.getResultList(); 
    }

    public List<Producto> findVendidos(int user) { // Galo
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor.id= :user");
        q.setParameter("user", user);

        return q.getResultList();
    }
    
    public List<Producto> findVendidosDesde(int user, String filtroDesde, String filtroHasta) { // Galo
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor.id= :user and p.precioSalida>=:desde and p.precioSalida<=:hasta");
        q.setParameter("user", user);
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
        return q.getResultList();
    }
    
    public List<Object[]> findVendidosAUser(int user) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.comprador is not null GROUP BY p");
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Producto> findVendidosFiltered(int user, String filtroCategoria) { // Galo
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user");
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Producto> findVendidosFilteredDesde(int user, String filtroCategoria, String filtroDesde, String filtroHasta) { // Galo
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user and p.precioSalida>=:desde and p.precioSalida<=:hasta");
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
        return q.getResultList();
    }
    
    public List<Object[]> findVendidosAUserFiltered(int user, String filtroCategoria) { // Galo
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.categoria.nombre like :filtroCategoria and p.comprador is not null GROUP BY p");
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList();
    }
    
    public List<Producto> findVendidosByNombre(int user,String filtroNombre) { // Galo
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.vendedor.id=:user");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findVendidosByNombreDesde(int user,String filtroNombre, String filtroDesde, String filtroHasta) { // Galo
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.vendedor.id=:user and p.precioSalida>=:desde and p.precioSalida<=:hasta");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
        return q.getResultList(); 
    }
    
    public List<Object[]> findVendidosAUserByNombre(int user,String filtroNombre) {
        Query q;   
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.titulo like :titulo and p.comprador is not null GROUP BY p");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findVendidosByNombreFiltered(int user,String filtroNombre, String filtroCategoria) { // Galo
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        return q.getResultList(); 
    }
    
    public List<Producto> findVendidosByNombreFilteredDesde(int user,String filtroNombre, String filtroCategoria, String filtroDesde, String filtroHasta) { // Galo
        Query q;   
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user and p.precioSalida>=:desde and p.precioSalida<=:hasta");
        q.setParameter("titulo", '%' + filtroNombre + '%');
        q.setParameter("filtroCategoria",  filtroCategoria);
        q.setParameter("user", user);
        q.setParameter("desde", Double.parseDouble(filtroDesde));
        q.setParameter("hasta", Double.parseDouble(filtroHasta));
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
    
    public List<Object[]> findEnPuja(int user) {
        Query q;
        q = this.getEntityManager().createQuery("select p, MAX(pu.precio) from Producto p LEFT JOIN p.pujaList pu where p.enPuja=1 and p.vendedor.id= :user GROUP BY p");
        q.setParameter("user", user);
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
    
    public Producto findByID(int id) { //Miguel Oña Guerrero
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
    
    public List<Puja> findLosers(String productoId, PujaPK pujaPK) {
        Query q;
        q = this.getEntityManager().createQuery("select pu from Puja pu where pu.producto1.id= :pId and pu.pujaPK!=:pujaId");
        q.setParameter("pId", Integer.parseInt(productoId));
        q.setParameter("pujaId", pujaPK);
        
        return q.getResultList();
    }
    
    public List<Producto> findExistentesByFiltro(String filtroTitulo, String filtroCategoria, int usuario) { //Miguel Oña Guerrero
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo "
                + "and p.categoria.nombre like :filtroCategoria and p.vendedor.id != :id and p.comprador = null");
        q.setParameter("titulo", '%' + filtroTitulo + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        
        return q.getResultList(); 
    }
    
    public List<Producto> findFavoritosByFiltro(String filtroTitulo, String filtroCategoria, int usuario) { //Miguel Oña Guerrero
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p join p.usuarioList u where u.id = :id"
                + " and p.titulo like :titulo and p.categoria.nombre like :filtroCategoria and p.vendedor.id != :id "
                + "and (p.comprador.id = :id or p.comprador = null)");
        q.setParameter("titulo", '%' + filtroTitulo + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        
        return q.getResultList(); 
    }
    
    public List<Producto> findCompradosByFiltro(String filtroTitulo, String filtroCategoria, int usuario) { //Miguel Oña Guerrero
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.titulo like :titulo "
                + "and p.categoria.nombre like :filtroCategoria and p.comprador.id = :id");
        q.setParameter("titulo", '%' + filtroTitulo + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        
        return q.getResultList(); 
    }
    
        public List<Producto> findEnPujaByFiltro(String filtroTitulo, String filtroCategoria, int usuario) { //Miguel Oña Guerrero
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.enPuja = 1 and p.titulo like :titulo "
                + "and p.categoria.nombre like :filtroCategoria and p.vendedor.id != :id and p.comprador = null");
        q.setParameter("titulo", '%' + filtroTitulo + '%');
        q.setParameter("filtroCategoria",  '%' + filtroCategoria + '%');
        q.setParameter("id", usuario);
        
        return q.getResultList(); 
    }

}