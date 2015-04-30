/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenhotelwebjpa.util;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.codec.Base64;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

   @Stateless
   @Interceptors(SpringBeanAutowiringInterceptor.class) // required if using @Autowired as below
   public class EmailVerificationSender implements Serializable {
       private static final long serialVersionUID = 1L;
    
       @Autowired // required by Spring to inject object from Spring config file
       private MailSender mailSender;
       @Autowired
       private SimpleMailMessage templateMessage;
    
       /**
        * Uses Spring's SimleMailMessage technique to send a verification email
        * to the candidate member who attempted registration on Bit Bay. This 
        * email is necessary to prevent robots or scammers from registering. A 
        * base64 encoded username is generated to hide details of the key used
        * by the servlet to identify the user.
        * 
        * @param userEmail
        */
        public void sendEmail(String userEmail, Object data) throws MailException {
           // Create a Base64 encode of the username
           byte[] encoded = Base64.encode(userEmail.getBytes()); 
           String base64Username = new String(encoded);
           
           // Create a thread safe "copy" of the template message and customize it
           // See Spring config in applicationContext.xml
           SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
           msg.setTo(userEmail);

           msg.setText("Thank you for registering with Hotel Management Application."
                   + "To complete the registration process for user email [" 
                   + userEmail + "], please click on the link below."
                   + "\n\nCAUTION: if you did not register with bitBay, somebody "
                   + "else is trying use your email to scam the system. Please "
                   + "do not click on the link below unless you intend to confirm "
                   + "your registraiton with bitBay.\n\n"
                   + "Here's the link to complete the registraiton process: \n\n"
                   + "http://localhost:8080/MavenHotelWebJPA/verifier?id=" + base64Username); // change the URL to match your app
        
           try {
               mailSender.send(msg);
           } catch(NullPointerException npe) {
               throw new MailSendException(
                       "Email send error from EmailVerificationSender");
           }
       }
   }
