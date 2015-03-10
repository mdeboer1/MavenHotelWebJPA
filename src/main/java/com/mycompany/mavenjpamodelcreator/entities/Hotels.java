/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenjpamodelcreator.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markr_000
 */
@Entity
@Table(name = "hotels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotels.findAll", query = "SELECT h FROM Hotels h"),
    @NamedQuery(name = "Hotels.findByHotelId", query = "SELECT h FROM Hotels h WHERE h.hotelId = :hotelId"),
    @NamedQuery(name = "Hotels.findByHotelName", query = "SELECT h FROM Hotels h WHERE h.hotelName = :hotelName"),
    @NamedQuery(name = "Hotels.findByHotelAddress", query = "SELECT h FROM Hotels h WHERE h.hotelAddress = :hotelAddress"),
    @NamedQuery(name = "Hotels.findByHotelCity", query = "SELECT h FROM Hotels h WHERE h.hotelCity = :hotelCity"),
    @NamedQuery(name = "Hotels.findByHotelState", query = "SELECT h FROM Hotels h WHERE h.hotelState = :hotelState"),
    @NamedQuery(name = "Hotels.findByHotelZip", query = "SELECT h FROM Hotels h WHERE h.hotelZip = :hotelZip")})
public class Hotels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hotel_id")
    private Integer hotelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "hotel_name")
    private String hotelName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "hotel_address")
    private String hotelAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "hotel_city")
    private String hotelCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hotel_state")
    private String hotelState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hotel_zip")
    private String hotelZip;

    public Hotels() {
    }

    public Hotels(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Hotels(Integer hotelId, String hotelName, String hotelAddress, String hotelCity, String hotelState, String hotelZip) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelCity = hotelCity;
        this.hotelState = hotelState;
        this.hotelZip = hotelZip;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelState() {
        return hotelState;
    }

    public void setHotelState(String hotelState) {
        this.hotelState = hotelState;
    }

    public String getHotelZip() {
        return hotelZip;
    }

    public void setHotelZip(String hotelZip) {
        this.hotelZip = hotelZip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelId != null ? hotelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotels)) {
            return false;
        }
        Hotels other = (Hotels) object;
        if ((this.hotelId == null && other.hotelId != null) || (this.hotelId != null && !this.hotelId.equals(other.hotelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenjpamodelcreator.entities.Hotels[ hotelId=" + hotelId + " ]";
    }
    
}
