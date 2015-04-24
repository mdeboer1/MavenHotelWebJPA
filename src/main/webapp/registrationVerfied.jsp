<%-- 
    Document   : registrationVerfied
    Created on : Apr 24, 2015, 2:31:58 PM
    Author     : markr_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" 
           uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
        <link href="css/management.css" rel="stylesheet" type="text/css"/>
        <title>Hotel Management System</title>
    </head>
    <body>
        <h1>Registration Verified</h1>
        <div id="container">
            <p>${success}</p><br>
            <br>
            <p><a href="login.jsp">Hotel Lookup</a></p>
        </div>
    </body>
</html>
