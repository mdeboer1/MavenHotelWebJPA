/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.controller;

import com.mycompany.mavenhotelwebjpa.entity.Users;
import com.mycompany.mavenhotelwebjpa.facade.UsersFacade;
import com.mycompany.mavenhotelwebjpa.util.EmailVerificationSender;
import java.io.IOException;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.codec.Base64;

/**
 *
 * @author markr_000
 */
@WebServlet(name = "RegistrationVerifier", urlPatterns = {"/verifier"})
public class RegistrationVerifierController extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    // Note that we are using @Inject vs. @EJB. Inject is better. But
    // you must have bean.xml installed in your web app under the
    // "WEB-INF" directory.
    @Inject
    UsersFacade userFacade;
    @Inject 
    EmailVerificationSender emailService;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String errMsg = "";
        String destination = "/registrationVerified.jsp";
        
        try {
            String id = request.getParameter("id");
            byte[] decoded = Base64.decode(id.getBytes());
            String username = new String(decoded);
            
            Users user = userFacade.find(username);
            if(user == null) {
                throw new RuntimeException("Sorry, that user is not in our system");
            }
            user.setEnabled(true);
            userFacade.edit(user);
//            user.setDateVerified(new Date());
//            user.saveAndFlush(user);
            String message = "You have been successfully registered.  Please click"
                    + "on the login link below to navigate to the login screen";
            request.setAttribute("success", message);
        } catch(Exception dae) {
            errMsg = "VERIFICATION ERROR: " + dae.getLocalizedMessage();
            request.setAttribute("errMsg", errMsg);
            destination = "/verificationError.jsp";
        }
           
        RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(destination);
                dispatcher.forward(request, response);     
		
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
