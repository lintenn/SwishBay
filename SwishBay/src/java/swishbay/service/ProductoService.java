/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swishbay.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.ProductoFacade;
import swishbay.dao.PujaFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.ProductoDTO;
import swishbay.entity.Categoria;
import swishbay.entity.Producto;
import swishbay.entity.Puja;
import swishbay.entity.PujaPK;
import swishbay.entity.Usuario;

/**
 *
 * @author galop 68%, Luis 27%, Miguel 5%
 */

@Stateless
public class ProductoService {
    
    @EJB ProductoFacade pf;
    @EJB CategoriaFacade cf;
    @EJB UsuarioFacade uf;
    @EJB PujaFacade puf;
    
    private List<ProductoDTO> listaEntityADTO (List<Producto> lista) { // Luis
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto producto : lista) {
                listaDTO.add(producto.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<ProductoDTO> listarProductos (String filtroNombre, String filtroCategoria) { // Luis
        List<Producto> productos = null;
        
        if (filtroNombre == null || filtroNombre.isEmpty()) {
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.pf.findAll();
                    
                } else {
                    productos= this.pf.findAll(filtroCategoria);

                }
        } else {
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.pf.findByNombre(filtroNombre);

                } else {
                    productos = pf.findByNombre(filtroNombre,filtroCategoria);

                }   
        }
        
        return this.listaEntityADTO(productos);
    }
    
    public List<ProductoDTO> listarProductos (String filtroNombre, String filtroCategoria, String filtroDesde, String filtroHasta) { // Luis
        List<Producto> productos = null;
        
        if (filtroNombre == null || filtroNombre.isEmpty()) {
            
            if (filtroDesde == null) {   // En este caso filtroHasta también sería null  
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.pf.findAll();
                    
                } else {
                    productos= this.pf.findAll(filtroCategoria);

                }
            } else {
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.pf.findAllDesde(filtroDesde, filtroHasta);
                    
                } else {
                    productos= this.pf.findAllFilteredDesde(filtroCategoria, filtroDesde, filtroHasta);

                }
            }
                
        } else {
            
            if (filtroDesde == null) {   // En este caso filtroHasta también sería null 
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.pf.findByNombre(filtroNombre);

                } else {
                    productos = pf.findByNombre(filtroNombre, filtroCategoria);

                } 
            } else {
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.pf.findByNombreDesde(filtroNombre, filtroDesde, filtroHasta);

                } else {
                    productos = pf.findByNombreFilteredDesde(filtroNombre, filtroCategoria, filtroDesde, filtroHasta);

                }  
            }
            
                 
        }
        
        return this.listaEntityADTO(productos);
    }
    
    public ProductoDTO buscarProducto(String strId){ // Galo
        Producto p=null;
        if(strId !=null && !strId.isEmpty()){
            p = pf.find(Integer.parseInt(strId));
            
            
        }
        return (ProductoDTO) (p!=null ? p.toDTO(): p);
    }
    
    public Double precioMax(String strId){ // Galo
        
        Double p=0.0;
        
        if(strId !=null && !strId.isEmpty()){
            p = pf.findPrecioMax(strId); 
        }
        return p;
    }
    
    private void rellenarProducto(Producto p, String titulo, String desc, String foto, Date date, String categoria, String precio, int vendedor){ // Galo
        p.setTitulo(titulo);
        p.setDescripcion(desc);
        p.setFoto(foto);
        p.setFinPuja(date);
        p.setCategoria(cf.findByName(categoria));
        p.setPrecioSalida(Double.parseDouble(precio));
        short n=0;
        p.setEnPuja(n);
        p.setVendedor(uf.find(vendedor));
    }
    
    private void rellenarProducto(Producto p, String titulo, String desc, String foto, Date date, String categoria, String precio){ // Galo
        p.setTitulo(titulo);
        p.setDescripcion(desc);
        p.setFoto(foto);
        p.setFinPuja(date);
        p.setCategoria(cf.findByName(categoria));
        p.setPrecioSalida(Double.parseDouble(precio));
        short n=0;
        p.setEnPuja(n);
       
    }

    public void crearProducto(String titulo, String desc, String foto, Date date, String categoria, String precio, int vendedor) { // Galo

        Producto p = new Producto();
        Categoria c = cf.findByName(categoria);
        Usuario seller = uf.find(vendedor);
        
        rellenarProducto(p,titulo,desc,foto,date,categoria,precio,vendedor);
        c.getProductoList().add(p);
        seller.getProductoList2().add(p);
        
        pf.create(p);
        cf.edit(c);
        uf.edit(seller);
    }

    public void modificarProducto(String strId, String titulo, String desc, String foto, Date date, String categoria, String precio) { // Galo

        Producto p = pf.find(Integer.parseInt(strId));
        Categoria anteriorCategoria = p.getCategoria();
        Categoria c = cf.findByName(categoria);
        
        anteriorCategoria.getProductoList().remove(p);
        cf.edit(anteriorCategoria);
        
        rellenarProducto(p,titulo,desc,foto,date,categoria,precio);
        c.getProductoList().add(p);
        
        cf.edit(c);
        pf.edit(p);

    }
    
    private void rellenarProducto(Producto p, String titulo, String desc, String foto, String categoria, String precio){ //Luis
        p.setTitulo(titulo);
        p.setDescripcion(desc);
        p.setFoto(foto);

        p.setCategoria(cf.findByName(categoria));
        p.setPrecioSalida(Double.parseDouble(precio));

    }
    
    public void modificarProducto(String strId, String titulo, String desc, String foto, String categoria, String precio) { //Luis

        Producto p = pf.find(Integer.parseInt(strId));
        
        rellenarProducto(p,titulo,desc,foto,categoria,precio);
        
        pf.edit(p);

    }
    
    public void borrarProducto(Integer id){ // Galo
        Producto p = pf.find(id);
        pf.remove(p);
    }
    
    public void ponerEnPuja(Integer id){ //Galo
        Producto p = pf.find(id);
        p.setEnPuja((short) 1);
        pf.edit(p);
    }

    public void modificarPuja(String strId, String precio, java.util.Date d) { //Galo

        Producto p = pf.find(Integer.parseInt(strId));
        
        if(p.getEnPuja()==0){
            p.setEnPuja((short) 1);
            p.setPrecioSalida(Double.parseDouble(precio));
        }
        p.setFinPuja(d);
        
        pf.edit(p);
        
    }

    public void modificarPuja(String strId, java.util.Date d) { //Galo
        Producto p = pf.find(Integer.parseInt(strId));
        
        if(p.getEnPuja()==0){
            p.setEnPuja((short) 1);
        }
        p.setFinPuja(d);
        
        pf.edit(p);
    }

    public void quitarPuja(String str) { //Galo
        
        Producto p = this.pf.find(Integer.parseInt(str));
        p.getPujaList().clear();
        for(Puja pu : p.getPujaList()){
            puf.remove(pu);
            
        }
        p.setEnPuja((short) 0);
        pf.edit(p);
    }

    public void finalizarPuja(String id) { //Galo

        Producto p = pf.findByID(Integer.parseInt(id));
        Double d=p.getPrecioSalida();
        Puja puja=null;
        
        
        if(!p.getPujaList().isEmpty()){
            
            puja = puf.findMax(p.getId());
            Usuario comprador =puja.getUsuario();
            List<Puja> pujasPerdedoras = pf.findLosers(id, puja.getPujaPK());
            
            
            p.setEnPuja((short) 0);
            p.setComprador(puja.getUsuario());
            comprador.getProductoList1().add(p);
            
            this.uf.edit(comprador);
            this.pf.edit(p);
            
            for(Puja pu : pujasPerdedoras){                 
                sumarSaldo(pu.getPrecio(),pu.getUsuario());                
             }       
            
        }else{
             p.setEnPuja((short) 0);
             this.pf.edit(p);
             
        }

    }
    
    private void sumarSaldo(double cantidad, Usuario user){ //Galo
        
        double saldo = user.getSaldo();
        saldo += cantidad;
        user.setSaldo(saldo);
        
        uf.edit(user);
    }
    
    public void realizarPuja(int idproducto, double cantidad, int idusuario){ //Miguel
        Producto producto = pf.findByID(idproducto);
        Usuario usuario = uf.findByID(idusuario);
        
        Puja puja = new Puja();
        
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());
        
        puja.setFecha(sqlDate);
        puja.setPrecio(cantidad);
        puja.setUsuario(usuario);
        puja.setProducto1(producto);
        
        PujaPK pujapk = new PujaPK();
        pujapk.setComprador(idusuario);
        pujapk.setProducto(idproducto);
        
        puja.setPujaPK(pujapk);
        puf.create(puja);
        
        producto.getPujaList().add(puja);
        
        pf.edit(producto);
    }
}
