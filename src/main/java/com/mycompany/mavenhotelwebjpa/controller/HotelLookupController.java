/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.controller;

import com.mycompany.mavenhotelwebjpa.entity.Hotels;
import com.mycompany.mavenhotelwebjpa.facade.HotelsFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mdeboer1
 */
//@WebServlet(name = "HotelLookupController", urlPatterns = {"/LookupController"})
public class HotelLookupController extends HttpServlet {

    private static final String RESULT_PAGE = "/hotellookup.jsp";
    
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
        
        HttpSession session = request.getSession();
        String filter = request.getParameter("filter");
        String hotelName = request.getParameter("byName");
        String hotelAddress = request.getParameter("byAddress");
        String hotelCity = request.getParameter("byCity");
        String hotelState = request.getParameter("byState");
        String hotelZip = request.getParameter("byZip");
        String allHotels = request.getParameter("allHotels");
        List<Hotels> hotelList = new ArrayList<>();        
        
        if (filter != null){
            String columnName = null;
            //String propertyName = null;
            if (allHotels != null){
                hotelList = hotelsFacade.findAll();
            }
            else if (!hotelName.isEmpty()){
                columnName = request.getParameter("byName");
            }
            else if (!hotelAddress.isEmpty()){
                columnName = request.getParameter("byAddress");
            }
            else if (!hotelCity.isEmpty()){
                columnName = request.getParameter("byCity");
            }
            else if (!hotelState.isEmpty()){
                columnName = request.getParameter("byState");
            }
            else if (!hotelZip.isEmpty()){
                columnName = request.getParameter("byZip");
           }
            if (allHotels == null){
                hotelList = hotelsFacade.findAllByColumnName(columnName);
            }
            request.setAttribute("hotelNameList", hotelList);
            session.setAttribute("hotelNameList", hotelList);
        } 

        if (null == session.getAttribute("hotelNameList")){
            hotelList = hotelsFacade.findAll();
            request.setAttribute("hotelNameList", hotelList);
        }
        
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
