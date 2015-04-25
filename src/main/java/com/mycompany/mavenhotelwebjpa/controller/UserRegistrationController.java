/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.controller;

import com.mycompany.mavenhotelwebjpa.entity.Authorities;
import com.mycompany.mavenhotelwebjpa.entity.Users;
import com.mycompany.mavenhotelwebjpa.facade.AuthoritiesFacade;
import com.mycompany.mavenhotelwebjpa.facade.UsersFacade;
import com.mycompany.mavenhotelwebjpa.util.EmailVerificationSender;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author mdeboer1
 */

public class UserRegistrationController extends HttpServlet {
    
    private static final String RESULT_PAGE = "/registrationresult.jsp"; 
    private Users user;
    private static int authId;
//    private final String FIRST_NAME = "firstName";
//    private final String LAST_NAME = "lastName";
//    private final String ADDRESS = "address";
//    private final String CITY = "city";
//    private final String STATE = "state";
//    private final String ZIP_CODE = "zipCode";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String REGISTRATION_RESULT = "registrationResult";
    private EmailVerificationSender emailService;
    private String registrationResult;
    
    @Inject
    private UsersFacade userFacade;
    @Inject
    private AuthoritiesFacade authFacade;
    
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
        String userName = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        saveNewRegistation(userName, password);
        request.setAttribute(REGISTRATION_RESULT, registrationResult);
        RequestDispatcher view =
            request.getRequestDispatcher(response.encodeURL(RESULT_PAGE));
        view.forward(request, response);
    }

    private void saveNewRegistation(String userName, String password) {
	       // retrieve userName and password from registration form then save new account:
	       user = new Users();
	       user.setUserName(userName);
	       user.setPassword(encodeSha512(password,userName));
	       user.setEnabled(false); // don't want enabled until email verified!
//	       System.out.println(user.getUserName() + " " + user.getPassword());
	       List<Authorities> auths = new ArrayList<>();
	       Authorities auth = new Authorities();
//               auth.setAuthoritiesId(5);
	       auth.setAuthority("ROLE_USER"); // or, use any role you want
	       auths.add(auth);
	       user.setAuthoritiesCollection(auths);
	       auth.setUsername(user);

               userFacade = new UsersFacade();
               authFacade = new AuthoritiesFacade();
               userFacade.create(user);
               authFacade.create(auth);
	        // you need a UserService (UserFacade)
	       
	       try {
	            // you need an email service class
	            emailService.sendEmail(user.getUserName(), null);
		    registrationResult = "Please log in to the email address"
                            + "that you provided during registration for a"
                            + " varification email to finalize ths process.  "
                            + "Thank you!";
	       } catch (MailException ex) {
	            throw new RuntimeException(registrationResult = 
                                "Sorry, the verification email could not be "
	                           + "sent. Please notify the webmaster at "
	                           + "webmaster@gmail.com and we'll complete the "
	                           + "process for you. Thanks for your patience.");
               }
               
       }
       
       /*
        * Helper method that creates a salted SHA-512 hash composed of password (pwd) and 
        * salt (username).
        */
        private String encodeSha512(String pwd, String salt) {
            ShaPasswordEncoder pe = new ShaPasswordEncoder(512);
            pe.setIterations(1024);
            String hash = pe.encodePassword(pwd, salt);
            return hash;
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
