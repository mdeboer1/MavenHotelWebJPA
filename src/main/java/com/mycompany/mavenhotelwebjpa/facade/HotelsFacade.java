/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.facade;

import com.mycompany.mavenhotelwebjpa.entity.Hotels;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mdeboer1
 */
@Stateless
public class HotelsFacade extends AbstractFacade<Hotels> {
    @PersistenceContext(unitName = "hotelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HotelsFacade() {
        super(Hotels.class);
    }
    
    // select * from hotel where city = ?"
    // select h from Hotel where h.city = ?1"
    public List<Hotels> findAllByColumnName(String columnName, String propertyName){
        Query query = getEntityManager().createQuery("select h from Hotels h where h." +  columnName + " = ?1");
        query.setParameter(1, propertyName);
        return query.getResultList();
    }
    
}
