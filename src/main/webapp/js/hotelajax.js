/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($, window, document){
    $(function() {
        
        var $editHotel = $('#editHotel');
        var $deleteHotel = $('#deleteHotel');
        var $addHotel = $('$add');
        var $hotelId = $('#hotelId');
        var $editName = $('#editName');
        var $editAddress = $('#editAddress');
        var $editCity = $('#editCity');
        var $editZip = $('#editZip');
        var $addName = $('#addName');
        var $addAddress = $('#addAddress');
        var $addCity = $('#addCity');
        var $addState = $('#addState');
        var $addZip = $('#addZip');
        var baseUrl = "control";
        var $filter = $('#filter');
        // vars to represent the wizard look up too
        
        // Get all hotels for list in jsp
        findAllHotels();
        
        function findAllHotels () {
            $.get(baseUrl + "?action=list").then(function (hotels) {
                renderList(hotels);
            }, handleError);
        }

        function renderList(hotels) {
            $('#hotelList li').remove();
            $.each(hotels, function (index, hotel) {
                $('#hotelList').append('<li><a href="#" data-identity="'
                        + baseUrl + '?action=findone&hotelId='
                        + hotel.hotelId + '">' + hotel.name + '</a></li>');
            });
        }
        
        function handleError(xhr, status, error) {
            alert("Sorry, there was a problem: " + error);
        }
    });
});
