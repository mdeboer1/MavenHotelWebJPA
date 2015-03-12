/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.controller;

import com.mycompany.mavenhotelwebjpa.facade.HotelsFacade;
import com.mycompany.mavenhotelwebjpa.entity.Hotels;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mdeboer1
 */

public class HotelController extends HttpServlet {
    private static final String RESULT_PAGE = "/hotelmanagement.jsp"; 

    @EJB
    private HotelsFacade hotelsFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        // Create a list to fill the hotel list
        List<Hotels> hotelList = new ArrayList<>();
        String edit = request.getParameter("editHotel");
        String delete = request.getParameter("deleteHotel");
        List<Hotels> listOfNewHotels = new ArrayList<>();
        String addHotel = request.getParameter("addToList");
        String submitList = request.getParameter("submitToDb");
        String newHotelName = request.getParameter("editName");
        String newHotelAddress = request.getParameter("editAddress");
        String newHotelCity = request.getParameter("editCity");
        String newHotelState = request.getParameter("editState");
        String newHotelZip = request.getParameter("editZip");
        String hotelId = request.getParameter("hotelId");
        HttpSession session = request.getSession();
        String filter = request.getParameter("filter");
        String hId = request.getParameter("byId");
        String hotelName = request.getParameter("byName");
        String hotelAddress = request.getParameter("byAddress");
        String hotelCity = request.getParameter("byCity");
        String hotelState = request.getParameter("byState");
        String hotelZip = request.getParameter("byZip");
        String allHotels = request.getParameter("allHotels");
        HttpSession session3 = request.getSession();
        
        // This section is for the Lookup Wizard        
        if (filter != null){
            String columnName = null;
            String propertyName = null;
            if (allHotels != null){
                hotelList = hotelsFacade.findAll();
            }
            if (!hId.isEmpty()){
                columnName = "hotel_id";
                propertyName = hotelId;
            }
            else if (!hotelName.isEmpty()){
                columnName = "hotel_name";
                propertyName = hotelName;
            }
            else if (!hotelAddress.isEmpty()){
                columnName = "hotel_address";
                propertyName = hotelAddress;
            }
            else if (!hotelCity.isEmpty()){
                columnName = "hotel_city";
                propertyName = hotelCity;
            }
            else if (!hotelState.isEmpty()){
                columnName = "hotel_state";
                propertyName = hotelState;
            }
            else if (!hotelZip.isEmpty()){
                columnName = "hotel_zip";
                propertyName = hotelZip;
            }
            if (allHotels == null){
                hotelList = hotelsFacade.findAllByColumnName(columnName, propertyName);
            }
            request.setAttribute("hotelNameList", hotelList);
            session3.setAttribute("hotelNameList", hotelList);
        } 
        
        
        
        hotelList = hotelsFacade.findAll();
        request.setAttribute("hotelNameList", hotelList);
        
        RequestDispatcher view =
            request.getRequestDispatcher(response.encodeURL(RESULT_PAGE));
        view.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
