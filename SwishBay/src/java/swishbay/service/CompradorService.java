package swishbay.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.ProductoFacade;
import swishbay.dto.ProductoDTO;
import swishbay.entity.Producto;

/**
 * Service para mostrar productos al comprador
 * 
 * @author Miguel Oña Guerrero
 */
@Stateless
public class CompradorService {
    
    @EJB CategoriaFacade categoriaFacade;
    @EJB ProductoFacade productoFacade;
    
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
    
    public List<ProductoDTO> listarProductosExistentes(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoFacade.findExistentesByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);
        
        return this.listaProductoEntityADTO(productos);
    }
    
    public List<ProductoDTO> listarProductosFavoritos(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoFacade.findFavoritosByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);
        
        return this.listaProductoEntityADTO(productos);
    }
    
    public List<ProductoDTO> listarProductosComprados(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoFacade.findCompradosByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);
        
        return this.listaProductoEntityADTO(productos);
    }
    
    public List<ProductoDTO> listarProductosEnPuja(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoFacade.findEnPujaByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);
        
        return this.listaProductoEntityADTO(productos);
    }

}