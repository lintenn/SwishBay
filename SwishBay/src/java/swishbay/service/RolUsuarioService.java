/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import swishbay.dao.RolUsuarioFacade;
import swishbay.dto.RolUsuarioDTO;
import swishbay.entity.RolUsuario;

/**
 *
 * @author Luis
 */
@Stateless
public class RolUsuarioService {

    @EJB RolUsuarioFacade rolUsuarioFacade;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private List<RolUsuarioDTO> listaEntityADTO (List<RolUsuario> lista) {
        List<RolUsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (RolUsuario rol : lista) {
                listaDTO.add(rol.toDTO());
            }
        }
        return listaDTO;
    }
    
    
    public List<RolUsuarioDTO> listarRoles () {
        List<RolUsuario> lista = this.rolUsuarioFacade.findAll();
        return this.listaEntityADTO(lista);
    }
}
