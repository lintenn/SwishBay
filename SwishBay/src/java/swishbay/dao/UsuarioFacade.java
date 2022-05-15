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
    
    public List<Usuario> findByCompradorVendedorByCorreo(String correo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.correo like :correo");
        q.setParameter("correo", '%' + correo +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByApellidos(String apellidos){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.apellidos like :apellidos");
        q.setParameter("apellidos", '%' + apellidos +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByCiudad(String ciudad){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.ciudad like :ciudad");
        q.setParameter("ciudad", '%' + ciudad +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByDomicilio(String domicilio){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.domicilio like :domicilio");
        q.setParameter("domicilio", '%' + domicilio +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorBySexo(String sexo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.sexo like :sexo");
        q.setParameter("sexo", '%' + sexo +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorBySaldoDesde(Integer saldoDesde, List<Integer> usuarios){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.saldo >= :saldoDesde and u.id IN :usuarios");
        q.setParameter("saldoDesde", saldoDesde);
        q.setParameter("usuarios", usuarios);
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorBySaldoHasta(Integer saldoDesde, List<Integer> usuarios){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.saldo <= :saldoDesde and u.id IN :usuarios");
        q.setParameter("saldoDesde", saldoDesde);
        q.setParameter("usuarios", usuarios);
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByNameQueNoPertencenAUnGrupo(String nombre, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.nombre like :nombre and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("nombre", '%' + nombre +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByCorreoQueNoPertencenAUnGrupo(String correo, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.correo like :correo and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("correo", '%' + correo +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByApellidosQueNoPertencenAUnGrupo(String apellidos, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.apellidos like :apellidos and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("apellidos", '%' + apellidos +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByCiudadQueNoPertencenAUnGrupo(String ciudad, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.ciudad like :ciudad and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("ciudad", '%' + ciudad +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorByDomicilioQueNoPertencenAUnGrupo(String domicilio, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.domicilio like :domicilio and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("domicilio", '%' + domicilio +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorBySexoQueNoPertencenAUnGrupo(String sexo, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.sexo like :sexo and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("sexo", '%' + sexo +'%');
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorBySaldoDesdeQueNoPertencenAUnGrupo(Integer saldoDesde, List<Integer> usuarios, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.saldo >= :saldoDesde and u.id IN :usuarios and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("saldoDesde", saldoDesde);
        q.setParameter("usuarios", usuarios);
        
        return q.getResultList();
    }
    
    public List<Usuario> findByCompradorVendedorBySaldoHastaQueNoPertencenAUnGrupo(Integer saldoDesde, List<Integer> usuarios, List<Integer> idsGrupo){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.saldo <= :saldoDesde and u.id IN :usuarios and u.id NOT IN :idsGrupo");
        q.setParameter("idsGrupo", idsGrupo);
        q.setParameter("saldoDesde", saldoDesde);
        q.setParameter("usuarios", usuarios);
        
        return q.getResultList();
    }
    
    public Usuario findByMarketing(){ // angel
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.rol.nombre like 'marketing'");
        List<Usuario> marketings = q.getResultList();
        
        return (marketings==null || marketings.isEmpty()) ? null : marketings.get(0);
    }
}
