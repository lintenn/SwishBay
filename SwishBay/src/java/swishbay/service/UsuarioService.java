/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import swishbay.dao.CategoriaFacade;
import swishbay.dao.GrupoFacade;
import swishbay.dao.ProductoFacade;
import swishbay.dao.RolUsuarioFacade;
import swishbay.dao.UsuarioFacade;
import swishbay.dto.UsuarioDTO;
import swishbay.entity.Categoria;
import swishbay.entity.Grupo;
import swishbay.entity.Mensaje;
import swishbay.entity.Producto;
import swishbay.entity.RolUsuario;
import swishbay.entity.Usuario;

/**
 *
 * @author Luis
 */
@Stateless
public class UsuarioService {
    
    @EJB UsuarioFacade usuarioFacade;
    @EJB CategoriaFacade categoriaFacade;
    @EJB RolUsuarioFacade rolUsuarioFacade;
    @EJB ProductoFacade productoFacade;
    @EJB GrupoFacade grupoFacade;
    @EJB GrupoService grupoService;
    
    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) { // Luis
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario : lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<UsuarioDTO> listarUsuarios (String filtroNombre) { // Luis
        List<Usuario> usuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            usuarios = this.usuarioFacade.findAll();        
        } else {
            usuarios = this.usuarioFacade.findByNombre(filtroNombre);
        }
        
        return this.listaEntityADTO(usuarios);
    }
    
    public List<UsuarioDTO> listarUsuarios (String filtroNombre, String filtroRol) { // Luis
        List<Usuario> usuarios = null;
        
        if (filtroNombre == null || filtroNombre.isEmpty()) {
                if (filtroRol == null || filtroRol.equals("Tipo")) {
                    usuarios = this.usuarioFacade.findAll();
                    
                } else {
                    usuarios = this.usuarioFacade.findAll(filtroRol);

                }
        } else {
                if (filtroRol == null || filtroRol.equals("Tipo")) {
                    usuarios = this.usuarioFacade.findByNombre(filtroNombre);

                } else {
                    usuarios = this.usuarioFacade.findByNombre(filtroNombre, filtroRol);

                }   
        }
        
        return this.listaEntityADTO(usuarios);
    }
    
    public UsuarioDTO buscarUsuario (Integer id) { // Luis
        Usuario usuario = this.buscarUsuarioById(id);
        return usuario.toDTO();
    }
    
    private Usuario buscarUsuarioById (Integer id) { // angel
        Usuario usuario = this.usuarioFacade.find(id);
        return usuario;
    }
    
    public void borrarUsuario (Integer id) { // Luis
        Usuario usuario = this.buscarUsuarioById(id);
        
        this.usuarioFacade.remove(usuario);
    }
    
    public String comprobarInformacionUsuario (Date fechaNacimiento, String strId, String correo, String strSaldo) { // Luis
        
        String status;
        Date fechaSistema = new Date();
        int edad = fechaSistema.getYear() - fechaNacimiento.getYear();
        Usuario posibleUser = null;

        boolean esMenor = edad < 18;
        int mes, dia;
        if (edad == 18) {
            mes = fechaSistema.getMonth() - fechaNacimiento.getMonth();
            if (mes == 0) {
                dia = fechaSistema.getDay() - fechaNacimiento.getDay();
                esMenor = dia < 0;
            } else {
                esMenor = mes < 0;
            }
        }
        
        if (strId == null || strId.isEmpty()) { // si estamos aÃ±adiendo
            try {
                posibleUser = usuarioFacade.findByCorreo(correo);
            } catch (EJBException e) {
                posibleUser = null;
            }
        }
        
        if (strSaldo != null && !strSaldo.isEmpty() && !strSaldo.matches("[-+]?\\d*\\.?\\d+")) {
            status= "Formato de saldo incorrecto.";
                
        } else if (posibleUser != null) {
            status = "El correo introducido ya existe en el sistema";
               
        } else if (esMenor) {
            status = "No se permiten usuarios menores de edad";
            
        } else {
            status = null;
        }
        
        return status;
    }
    
    private void rellenarUsuario (Usuario usuario, String nombre, String apellidos, String correo,
                                String password, String domicilio, String ciudad, String sexo,
                                Date fechaNacimiento, Double saldo, String strTipoUsuario) { // Luis
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setDomicilio(domicilio);
        usuario.setCiudad(ciudad);
        usuario.setSexo(sexo);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setSaldo(saldo);
        
        RolUsuario rol = this.rolUsuarioFacade.findByNombre(strTipoUsuario);
        usuario.setRol(rol);
               
        // FaltarÃ­an las categorias...
    }
    
    private void actualizarRolUsuario (Usuario newUser) {
        RolUsuario rol = newUser.getRol();
        
        rol.getUsuarioList().add(newUser);
        this.rolUsuarioFacade.edit(rol);
    }
    
    private void rellenarCategoriasUsuario (String[] categorias, Usuario newUser) {
        // Cargamos las categorÃ­as...
        
        // Borramos al usuario de las categorias anteriores
        for (Categoria categoria : newUser.getCategoriaList()) {
            categoria.getUsuarioList().remove(newUser);
                
            this.categoriaFacade.edit(categoria);
        } 
        // Borramos las categorÃ­as anteriores del usuario
        newUser.getCategoriaList().clear();
        
        if (categorias != null) {
            for (String categoriaId : categorias) {
                // AÃ±adimos al usuario en las nuevas categorÃ­as
                Categoria categoria = categoriaFacade.find(Integer.parseInt(categoriaId));
                
                categoria.getUsuarioList().add(newUser);
                
                this.categoriaFacade.edit(categoria);
                
                // AÃ±adimos las nuevas categorÃ­as al usuario
                newUser.getCategoriaList().add(categoria);
            }
            
            this.usuarioFacade.edit(newUser);
            
        }
    }
    
    public UsuarioDTO crearUsuario (String nombre, String apellidos, String correo,
                                String password, String domicilio, String ciudad, String sexo,
                                Date fechaNacimiento, Double saldo, String strTipoUsuario, String[] categorias) {
        Usuario usuario = new Usuario();
        
        this.rellenarUsuario(usuario, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario);
        
        //usuario.setCategoriaList(new ArrayList<>());
        
        this.usuarioFacade.create(usuario);
        
        usuario = this.usuarioFacade.findByCorreo(usuario.getCorreo()); // No deberÃ­a hacer falta hacer esto, pero por algÃºn motivo al hacer create(usuario) el id (aÃºn siendo autoincrementado y guardÃ¡ndose en la base de datos) no se setea y se queda como null
        
        this.actualizarRolUsuario(usuario);
        
        this.rellenarCategoriasUsuario(categorias, usuario);
        
        return usuario.toDTO();
    }
    
    public UsuarioDTO modificarUsuario (Integer id, String nombre, String apellidos, String correo,
                                String password, String domicilio, String ciudad, String sexo,
                                Date fechaNacimiento, Double saldo, String strTipoUsuario, String[] categorias) {
        Usuario usuario = this.buscarUsuarioById(id);
        
        RolUsuario rolAntiguo = usuario.getRol();
        rolAntiguo.getUsuarioList().remove(usuario);
        this.rolUsuarioFacade.edit(rolAntiguo);
        
        this.rellenarUsuario(usuario, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario);
        
        this.usuarioFacade.edit(usuario);
        
        this.actualizarRolUsuario(usuario);
        
        this.rellenarCategoriasUsuario(categorias, usuario);
        
        return usuario.toDTO();
    }
    
    public void modificarListaMensajesUsuario(Integer id, List<Mensaje> mensajes){ // angel
        
        Usuario usuario = this.buscarUsuarioById(id);
        
        usuario.setMensajeList(mensajes);
        
        this.usuarioFacade.edit(usuario);
        
    }
    
    public void anadirGrupoAListaGruposUsuario(Integer idUsuario, Integer idGrupo){ // angel
        
        Usuario usuario = this.buscarUsuarioById(idUsuario);
        Grupo grupo = this.grupoFacade.find(idGrupo);
        
        List<Grupo> grupoList = usuario.getGrupoList();
        grupoList.add(grupo);
        usuario.setGrupoList(grupoList);
        
        this.usuarioFacade.edit(usuario);
        
    }
    
    public void eliminarGrupoAListaGruposUsuario(Integer idUsuario, Integer idGrupo){ // angel
        
        Usuario usuario = this.buscarUsuarioById(idUsuario);
        Grupo grupo = this.grupoFacade.find(idGrupo);
        
        List<Grupo> grupoList = usuario.getGrupoList();
        grupoList.remove(grupo);
        usuario.setGrupoList(grupoList);
        
        this.usuarioFacade.edit(usuario);
        
    }
    
    public UsuarioDTO comprobarCredenciales (String correo, String password) { // Luis
        UsuarioDTO userdto = null;
        
        try {
            Usuario user = usuarioFacade.comprobarUsuario(correo, password);
            if (user != null) userdto = user.toDTO();
        } catch(EJBException ex){
            userdto = null;
        }
        
        return userdto;
    }
    
    public UsuarioDTO manejoFavoritos(int idProducto, int idUsuario){
        
        Usuario usuario = this.buscarUsuarioById(idUsuario);
        Producto producto = this.productoFacade.findByID(idProducto);
        
        if(usuario.getProductoList().contains(producto)){
            usuario.getProductoList().remove(producto);
            this.eliminarUsuarioAGrupoADarleFavoritoAProducto(idProducto, idUsuario);
        }else{
            usuario.getProductoList().add(producto);
            this.anadirUsuarioAGrupoADarleFavoritoAProducto(idProducto, idUsuario);
        }
        
        this.usuarioFacade.edit(usuario);
        
        return usuario.toDTO();
    }
    
    public UsuarioDTO sumarSaldo(double cantidad, int idUsuario){ //Miguel
        Usuario usuario = this.buscarUsuarioById(idUsuario);
        
        double saldo = usuario.getSaldo();
        saldo += cantidad;
        usuario.setSaldo(saldo);
        
        usuarioFacade.edit(usuario);
        
        return usuario.toDTO();
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedor(){ // angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedor();
        
        return this.listaEntityADTO(usuarios);
        
    }
    
    public List<UsuarioDTO> buscarPorCompradorVendedorPorNombre(String nombre){ // angel
        
        List<Usuario> usuarios = this.usuarioFacade.findByCompradorVendedorByName(nombre);
        
        return this.listaEntityADTO(usuarios);
        
    }
    
    public Integer buscarUsuarioMarketing(){ // angel
        
        Usuario usuario = this.usuarioFacade.findByMarketing();
        
        if(usuario == null){
            this.crearUsuario("Usuario marketing", "Numero 1", "usuarioMarketingNumero1@gmail.com", "123456", null, null, null, null, null, "marketing", null);
        }
        
        return this.usuarioFacade.findByMarketing().getId();
    }
    
    public void anadirUsuarioAGrupoADarleFavoritoAProducto(int idProducto, int idUsuario){ // angel
        
        this.grupoService.comprobarExistenciaGrupoPorNombre("Grupo_"+idProducto);
        Integer idGrupo = this.grupoService.buscarGruposPorNombre("Grupo_"+idProducto).get(0).getId();
        this.grupoService.anadirUsuarioAListaUsuariosGrupo(idUsuario, idGrupo);
        this.anadirGrupoAListaGruposUsuario(idUsuario, idGrupo);
            
    }
    
    public void eliminarUsuarioAGrupoADarleFavoritoAProducto(int idProducto, int idUsuario){ // angel

        Integer idGrupo = this.grupoService.buscarGruposPorNombre("Grupo_"+idProducto).get(0).getId();
        this.grupoService.eliminarUsuarioAListaUsuariosGrupo(idUsuario, idGrupo);
        this.eliminarGrupoAListaGruposUsuario(idUsuario, idGrupo);
            
    }
}
