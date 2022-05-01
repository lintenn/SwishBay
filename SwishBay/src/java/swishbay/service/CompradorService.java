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
    
    public List<ProductoDTO> listarProductosExistentes(String filtroTitulo, String filtroCategoria, int usuario){
        List<Producto> productos = productoFacade.findExistentesByFiltro(filtroTitulo, filtroCategoria, usuario);
        
        return this.listaProductoEntityADTO(productos);
    }
}