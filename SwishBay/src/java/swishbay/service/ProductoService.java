/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swishbay.service;

import java.sql.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.ProductoFacade;
import swishbay.dao.PujaFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.ProductoDTO;
import swishbay.entity.Producto;
import swishbay.entity.Puja;

/**
 *
 * @author galop
 */

@Stateless
public class ProductoService {
    
    @EJB ProductoFacade pf;
    @EJB CategoriaFacade cf;
    @EJB UsuarioFacade uf;
    @EJB PujaFacade puf;
    
    public ProductoDTO buscarProducto(String strId){
        Producto p=null;
        if(strId !=null && !strId.isEmpty()){
            p = pf.find(Integer.parseInt(strId));
            
            
        }
        return (ProductoDTO) (p!=null ? p.toDTO(): p);
    }
    
    public Double precioMax(String strId){
        
        Double p=0.0;
        
        if(strId !=null && !strId.isEmpty()){
            p = pf.findPrecioMax(strId); 
        }
        return p;
    }
    
    public void rellenarProducto(Producto p, String titulo, String desc, String foto, Date date, String categoria, String precio, int vendedor){
        p.setTitulo(titulo);
        p.setDescripcion(desc);
        p.setFoto(foto);
        p.setFinPuja(date);
        p.setCategoria(cf.findByNombre(categoria).get(0));
        p.setPrecioSalida(Double.parseDouble(precio));
        short n=0;
        p.setEnPuja(n);
        p.setVendedor(uf.find(vendedor));
    }

    public void crearProducto(String titulo, String desc, String foto, Date date, String categoria, String precio, int vendedor) {

        Producto p = new Producto();
        
        rellenarProducto(p,titulo,desc,foto,date,categoria,precio,vendedor);
        
        pf.create(p);
    }

    public void modificarProducto(String strId, String titulo, String desc, String foto, Date date, String categoria, String precio, int vendedor) {

        Producto p = pf.find(Integer.parseInt(strId));
        
        rellenarProducto(p,titulo,desc,foto,date,categoria,precio,vendedor);
        
        pf.edit(p);

    }
    
    public void borrarProducto(Integer id){
        Producto p = pf.find(id);
        pf.remove(p);
    }
    
    public void ponerEnPuja(Integer id){
        Producto p = pf.find(id);
        p.setEnPuja((short) 1);
        pf.edit(p);
    }

    public void modificarPuja(String strId, String precio, java.util.Date d) {

        Producto p = pf.find(Integer.parseInt(strId));
        
        if(p.getEnPuja()==0){
            p.setEnPuja((short) 1);
            p.setPrecioSalida(Double.parseDouble(precio));
        }
        p.setFinPuja(d);
        
        pf.edit(p);
        
    }

    public void modificarPuja(String strId, java.util.Date d) {
        Producto p = pf.find(Integer.parseInt(strId));
        
        if(p.getEnPuja()==0){
            p.setEnPuja((short) 1);
        }
        p.setFinPuja(d);
        
        pf.edit(p);
    }

    public void quitarPuja(String str) {
        
        Producto p = this.pf.find(Integer.parseInt(str));
        p.getPujaList().clear();
        for(Puja pu : p.getPujaList()){
            puf.remove(pu);
            
        }
        p.setEnPuja((short) 0);
        pf.edit(p);
    }

    public void finalizarPuja(String id) {

        Producto p = pf.findByID(Integer.parseInt(id));
        Double d=p.getPrecioSalida();
        Puja puja=null;
        
        
        if(!p.getPujaList().isEmpty()){
            
            puja = puf.findMax(p.getId());

            p.setEnPuja((short) 0);
            p.setComprador(puja.getUsuario());

            this.pf.edit(p);
        }else{
             p.setEnPuja((short) 0);
             this.pf.edit(p);
        }

    }
    
}
