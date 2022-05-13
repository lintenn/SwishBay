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
    
    private List<CategoriaDTO> listaEntityADTO (List<Categoria> lista) { // Luis
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria categoria : lista) {
                listaDTO.add(categoria.toDTO());
            }
        }
        return listaDTO;
    }
    
    
    public List<CategoriaDTO> listarCategorias () { // Luis
        List<Categoria> lista = this.categoriaFacade.findAll();
        return this.listaEntityADTO(lista);
    }
    
    public List<CategoriaDTO> listarCategorias (String filtroNombre) { // Luis
        List<Categoria> categorias = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            categorias = this.categoriaFacade.findAll();        
        } else {
            categorias = this.categoriaFacade.findByNombre(filtroNombre);
        }
        
        return this.listaEntityADTO(categorias);
    }
    
    public CategoriaDTO buscarCategoria (Integer id) { // Luis
        Categoria categoria = this.categoriaFacade.find(id);
        return categoria.toDTO();
    }
    
    public void borrarCategoria (Integer id) { // Luis
        Categoria categoria = this.categoriaFacade.find(id);
        
        this.categoriaFacade.remove(categoria);
    }
    
    private void rellenarCategoria(Categoria categoria, String nombre, String descripcion) { // Luis
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
    }
    
    public void crearCategoria (String nombre, String descripcion) { // Luis
        Categoria categoria = new Categoria();
        
        this.rellenarCategoria(categoria, nombre, descripcion);
        
        this.categoriaFacade.create(categoria);
    }
    
    public void modificarCategoria (Integer id, String nombre, String descripcion) { // Luis
        Categoria categoria = this.categoriaFacade.find(id);
        
        this.rellenarCategoria(categoria, nombre, descripcion);
        
        this.categoriaFacade.edit(categoria);
    }
    
}
