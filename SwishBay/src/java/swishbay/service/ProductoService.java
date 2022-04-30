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
    
    private List<ProductoDTO> listaEntityADTO (List<Producto> lista) { //Luis
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto producto : lista) {
                listaDTO.add(producto.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<ProductoDTO> listarProductos (String filtroNombre, String filtroCategoria) { //Luis
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
    
    private void rellenarProducto(Producto p, String titulo, String desc, String foto, Date date, String categoria, String precio, int vendedor){
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
    
    private void rellenarProducto(Producto p, String titulo, String desc, String foto, String categoria, String precio){ //Luis
        p.setTitulo(titulo);
        p.setDescripcion(desc);
        p.setFoto(foto);
        //p.setFinPuja(date);
        p.setCategoria(cf.findByNombre(categoria).get(0));
        p.setPrecioSalida(Double.parseDouble(precio));
        //short n=0;
        //p.setEnPuja(n);
    }
    
    public void modificarProducto(String strId, String titulo, String desc, String foto, String categoria, String precio) { //Luis

        Producto p = pf.find(Integer.parseInt(strId));
        
        rellenarProducto(p,titulo,desc,foto,categoria,precio);
        
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
