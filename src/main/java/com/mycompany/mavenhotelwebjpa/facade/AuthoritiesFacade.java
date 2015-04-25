/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.facade;

import com.mycompany.mavenhotelwebjpa.entity.Authorities;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author markr_000
 */
@Stateless
public class AuthoritiesFacade extends AbstractFacade<Authorities> {
    @PersistenceContext(unitName = "hotelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthoritiesFacade() {
        super(Authorities.class);
    }
    
}
