/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.facade;

import com.mycompany.mavenhotelwebjpa.entity.Hotels;
import com.mycompany.mavenhotelwebjpa.entity.Hotels_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    
//    public Integer findHighestHotelId(){
////        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
////        CriteriaQuery<Hotels> criteriaQuery = builder.createQuery(Hotels.class);
////        Root<Hotels> hotel = criteriaQuery.from(Hotels.class);
////
////        criteriaQuery.where(hotel.equals(hotel.get("hotel_id"), hotelId));
//        return (Integer)em.createQuery("select max(hotels.hotel_id) from  hotel hotels").getSingleResult();
//    }
    
    // select * from hotel where city = ?"
    // select h from Hotel where h.city = ?1"
    public List<Hotels> findAllByColumnName(String searchKey) {

        searchKey = new StringBuilder("%").append(searchKey).append("%").toString();

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Hotels> criteriaQuery = builder.createQuery(Hotels.class);
        Root<Hotels> hotel = criteriaQuery.from(Hotels.class);
        
        criteriaQuery.where(builder.like(hotel.get(Hotels_.hotelName),searchKey));
        TypedQuery<Hotels> q = getEntityManager().createQuery(criteriaQuery);
        List<Hotels> hotels = q.getResultList();
        
        if(hotels.isEmpty()) {
            
            criteriaQuery.where(builder.like(hotel.get(Hotels_.hotelAddress),searchKey));
            q = getEntityManager().createQuery(criteriaQuery);
            hotels = q.getResultList();
            
            if (hotels.isEmpty()){

            criteriaQuery.where(builder.like(hotel.get(Hotels_.hotelCity),searchKey));
            q = getEntityManager().createQuery(criteriaQuery);
            hotels = q.getResultList();
            }
            
            if (hotels.isEmpty()){
                criteriaQuery.where(builder.like(hotel.get(Hotels_.hotelState),searchKey));
                q = getEntityManager().createQuery(criteriaQuery);
                hotels = q.getResultList();
            }
            
            if(hotels.isEmpty()) {

                criteriaQuery.where(builder.like(hotel.get(Hotels_.hotelZip),searchKey));
                q = getEntityManager().createQuery(criteriaQuery);
                hotels = q.getResultList();
            }
        }
        
        return hotels;
    
    }
}    
    
