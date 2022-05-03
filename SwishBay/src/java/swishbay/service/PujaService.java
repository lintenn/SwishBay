/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swishbay.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.PujaFacade;
import swishbay.dto.PujaDTO;
import swishbay.entity.Puja;

/**
 *
 * @author galop
 */

@Stateless
public class PujaService {
    
    @EJB PujaFacade pf;
    
    public PujaDTO buscarPuja(Integer id){
        
        Puja p = pf.find(id);
        
        return p.toDTO();
    }
    
    public PujaDTO mayorPuja(Integer id){
        Puja puja = pf.findMax(id);
        
        return (puja == null) ? null : puja.toDTO();
    }
    
}
