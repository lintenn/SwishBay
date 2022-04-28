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
import swishbay.dao.CategoriaFacade;
import swishbay.dto.CategoriaDTO;
import swishbay.entity.Categoria;

/**
 *
 * @author Luis
 */
@Stateless
public class CategoriaService {

    @EJB CategoriaFacade categoriaFacade;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private List<CategoriaDTO> listaEntityADTO (List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria categoria : lista) {
                listaDTO.add(categoria.toDTO());
            }
        }
        return listaDTO;
    }
    
    
    public List<CategoriaDTO> listarCategorias () {
        List<Categoria> lista = this.categoriaFacade.findAll();
        return this.listaEntityADTO(lista);
    }
    
    /*public List<CategoriaDTO> listarCategoriasPreferidas (Integer idUsuario) {
        List<Categoria> lista = this.categoriaFacade.findByUsuario(idUsuario);
        return this.listaEntityADTO(lista);
    }*/
}
