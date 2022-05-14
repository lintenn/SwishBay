/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author galop 33%, Miguel 66%
 */

@Stateless
public class PujaService {
    
    @EJB PujaFacade pujaFacade;
    
    public PujaDTO buscarPuja(Integer id){ //Galo
        
        Puja p = pujaFacade.find(id);
        
        return p.toDTO();
    }
    
    public PujaDTO mayorPuja(Integer id){ //Miguel
        Puja puja = pujaFacade.findMax(id);
        
        return (puja == null) ? null : puja.toDTO();
    }
    
    public PujaDTO buscarMayorPuja(Integer id){ //Miguel Oña Guerrero
        Puja puja = pujaFacade.findMayor(id);
        
        return (puja == null) ? null : puja.toDTO();
    }
    
    public List<PujaDTO> buscarMayoresPujas(List<ProductoDTO> productos){ //Miguel Oña Guerrero
        List<PujaDTO> pujas = new ArrayList();
        
        for(ProductoDTO producto : productos){
            pujas.add(this.buscarMayorPuja(producto.getId()));
        }
        
        return pujas;
    }
}
