<%-- 
    Document   : hotellookup
    Created on : Apr 7, 2015, 1:18:44 PM
    Author     : mdeboer1
--%>


<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" 
           uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    Object object = request.getAttribute("hotelNameList");
    if (object == null){
        response.sendRedirect("control");
    }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
        <link href="css/management.css" rel="stylesheet" type="text/css"/>
        <title>Hotel Lookup System</title>
    </head>
    <body>
        <h1>Welcome to the hotel lookup system</h1>
        <div id="container"><br>
            <div class="row">
                <div id="fullHotelList">
                    <form id="hotels" name="hotels" method="POST" action='<%= 
                    response.encodeURL("control")%>'>
                        <div class="col-md-12">
                            <fieldset>
                                <legend>Hotel List</legend>
                                <table class="table table-striped header-fixed sortable">
                                    <thead>
                                        <th class="sortable">Name</th>
                                        <th class="sortable">Address</th>
                                        <th class="sortable">City</th>
                                        <th class="sortable">State</th>
                                        <th class="sortable">Zip</th>
                                    </thead>
                                    <tbody>
                                    <!--ul id="hList" class="list-group" height="600px"-->
                                        <c:forEach var="hotel" items="${hotelNameList}" >
                                            <!--li class="list-group-item"-->
                                            <tr>
                                                <td>${hotel.hotelName}</td>
                                                <td>${hotel.hotelAddress}</td>
                                                <td>${hotel.hotelCity}</td>
                                                <td>${hotel.hotelState}</td>
                                                <td>${hotel.hotelZip}</td>
                                            </tr>
                                        </c:forEach>
                                    <!--/ul-->
                                    </tbody>
                                </table>
                            </fieldset>
                        </div>
                    </form>
                </div>
            </div>
        </div>                
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>            
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script src="resources/js/bootstrap-sortable.js"></script>
        <script src="js/hotel.js" type="text/javascript"></script>        
    </body>
</html>
