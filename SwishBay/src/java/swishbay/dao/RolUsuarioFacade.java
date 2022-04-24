/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swishbay.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import swishbay.entity.RolUsuario;

/**
 *
 * @author Luis
 */
@Stateless
public class RolUsuarioFacade extends AbstractFacade<RolUsuario> {

    @PersistenceContext(unitName = "SwishBayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolUsuarioFacade() {
        super(RolUsuario.class);
    }
    
}
