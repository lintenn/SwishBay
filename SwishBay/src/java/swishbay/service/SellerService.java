/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package swishbay.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.ProductoFacade;
import swishbay.dto.CategoriaDTO;
import swishbay.dto.ProductoDTO;
import swishbay.dto.UsuarioDTO;
import swishbay.entity.Categoria;
import swishbay.entity.Producto;

/**
 *
 * @author galop
 */
@Stateless
public class SellerService {
    
    @EJB CategoriaFacade cf;
    @EJB ProductoFacade pf;
    
    private List<CategoriaDTO> listaCategoriaEntityADTO (List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria c : lista) {
                listaDTO.add(c.toDTO());
            }
        }
        return listaDTO;
    }
    
    private List<ProductoDTO> listaProductoEntityADTO (List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto p : lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }
    
    private void listaEnPujaEntityADTO (List<Object[]> lista) {
     
        if (lista != null) {
            for (Object[] p : lista) {
                Producto pr = (Producto) p[0];
                p[0]= pr.toDTO();
            }
        }
        
    }

    public List<CategoriaDTO> listarCategorias(){
        List<Categoria> list = cf.findAll();
        return this.listaCategoriaEntityADTO(list);
    }
    
    
    public List<ProductoDTO> listarProductos(UsuarioDTO user, String filtroNombre, String filtroCategoria, String filtroDesde, String filtroHasta){ // Galo
        
        List<Producto> productos = null;    
        if(filtroNombre == null || filtroNombre.isEmpty()){
            
            if(filtroDesde==null){   // En este caso filtroHasta también sería null         
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){                  
                    productos = pf.findVendidos(user.getId());
                }else{
                    productos= pf.findVendidosFiltered(user.getId(), filtroCategoria);
                }
            }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){                  
                    productos = pf.findVendidosDesde(user.getId(), filtroDesde, filtroHasta);
                }else{
                    productos= pf.findVendidosFilteredDesde(user.getId(), filtroCategoria, filtroDesde, filtroHasta);
                }
            }
            
        }else{
            if(filtroDesde==null){   // En este caso filtroHasta también sería null 
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findVendidosByNombre(user.getId(),filtroNombre);

                }else{
                    productos = pf.findVendidosByNombreFiltered(user.getId(),filtroNombre,filtroCategoria);

                } 
            }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findVendidosByNombreDesde(user.getId(),filtroNombre, filtroDesde, filtroHasta);

                }else{
                    productos = pf.findVendidosByNombreFilteredDesde(user.getId(),filtroNombre,filtroCategoria, filtroDesde, filtroHasta);

                }
            }
        }
        return this.listaProductoEntityADTO(productos);
    }
    
     public List<Object[]> listarEnPuja(UsuarioDTO user, String filtroNombre, String filtroCategoria){
        
        List<Object[]> productos = null;
        
        if(filtroNombre == null || filtroNombre.isEmpty()){
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findEnPuja(user.getId());

                }else{
                    productos= pf.findEnPujaFiltered(user.getId(),filtroCategoria);

                }
        }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findEnPujaByNombre(user.getId(),filtroNombre);

                }else{
                    productos = pf.findEnPujaByNombreFiltered(user.getId(),filtroNombre,filtroCategoria);

                }   
           }
        listaEnPujaEntityADTO(productos);
        return productos;
    }
     
    public List<Object[]> listarVendidos(UsuarioDTO user, String filtroNombre, String filtroCategoria){
        
        List<Object[]> productos = null;
        
        if(filtroNombre == null || filtroNombre.isEmpty()){
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findVendidosAUser(user.getId());

                }else{
                    productos= pf.findVendidosAUserFiltered(user.getId(), filtroCategoria);

                }
        }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findVendidosAUserByNombre(user.getId(), filtroNombre);

                }else{
                    productos = pf.findVendidosAUserByNombreFiltered(user.getId(), filtroNombre,filtroCategoria);

                }   
        }
        
        listaEnPujaEntityADTO(productos);
        return productos;
    }
    
}
