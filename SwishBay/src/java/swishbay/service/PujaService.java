package swishbay.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.PujaFacade;
import swishbay.dto.ProductoDTO;
import swishbay.dto.PujaDTO;
import swishbay.entity.Puja;

/**
 *
 * @author galop 20%, Miguel Oña Guerrero 80%
 */

@Stateless
public class PujaService {
    
    @EJB PujaFacade pujaFacade;
    
    private List<PujaDTO> listaEntityADTO (List<Puja> lista) { // Miguel Oña Guerrero
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja puja : lista) {
                listaDTO.add(puja.toDTO());
            }
        }
        return listaDTO;
    }
    
    public PujaDTO buscarPuja(Integer id){ //Galo
        
        Puja p = pujaFacade.find(id);
        
        return p.toDTO();
    }
    
    public PujaDTO buscarMayorPuja(Integer idProducto){ //Miguel Oña Guerrero
        Puja puja = pujaFacade.findMayor(idProducto);
        
        return (puja == null) ? null : puja.toDTO();
    }
    
    public List<PujaDTO> buscarMayoresPujas(List<ProductoDTO> productos){ //Miguel Oña Guerrero
        List<PujaDTO> pujas = new ArrayList();
        
        for(ProductoDTO producto : productos){
            pujas.add(this.buscarMayorPuja(producto.getId()));
        }
        
        return pujas;
    }
    
    public List<PujaDTO> buscarPujasOrdenadas(Integer idProducto){ //Miguel Oña Guerrero
        List<Puja> pujas = pujaFacade.findOrdenado(idProducto);
        
        return this.listaEntityADTO(pujas);
    }
 
}