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

    public List<CategoriaDTO> listarCategorias(){
        List<Categoria> list = cf.findAll();
        return this.listaCategoriaEntityADTO(list);
    }
    
    
    public List<ProductoDTO> listarProductos(UsuarioDTO user, String filtroNombre, String filtroCategoria){
        
        List<Producto> productos = null;    
        if(filtroNombre == null || filtroNombre.isEmpty()){
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findVendidos(user.getId());
                }else{
                    productos= pf.findVendidosFiltered(user.getId(), filtroCategoria);

                }
        }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = pf.findVendidosByNombre(user.getId(),filtroNombre);
                    System.out.println("Productos: " + productos.size());

                }else{
                    productos = pf.findVendidosByNombreFiltered(user.getId(),filtroNombre,filtroCategoria);

                }   
        }
        return this.listaProductoEntityADTO(productos);
    }
    
}
