<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.14.1/moment.min.js"></script>

<title>Hello world !!!!!</title>
</head>
<body>

<p align="center"> HELLO WORLD !!! </p>

<div class="container">
<div class="row">
<div style="margin: auto;width:30%;padding-top:50px">
<form id="form1" name="form1" method="post">
<div class="form-group">
Message <input type="text" class="form-control" placeholder= 'Enter Comment' id="message">
City <input type = "text"  class="form-control" placeholder= 'Enter Cityname' id="city" name="city"/>
Latitude <input type = "text" class="form-control" placeholder= 'Enter Latitude' id="latitude" name="city_latitude"/>
Longitude <input type = "text" class="form-control" placeholder= 'Enter longitude' id="longitude" name="city_longitude"/>
Temperature <input type = "text"   class="form-control" placeholder= 'Enter temperature' id="temperature" name="temperature"/>
<p align="center"><button type="submit" id="search">Done</button></p><br>


<br><br>
</div>
</form>

</div>
</div>

<%-- ${message} <br> --%>

<%-- ${user} <br> --%>

<%-- ${timestamp} --%>

<table class="table" id="location">
  <thead>
    <tr>
  			<th scope="col">Id</th>
      		<th scope="col">Comment</th>
      		<th scope="col">City Name</th>
            <th scope="col">City Latitude</th>
			<th scope="col">City Longitude</th>
			<th scope="col">City Temperature</th>
            <th scope="col">Create Date</th>
    </tr>
  </thead>

</table>
</div>


<script>
$(document).ready(function(){
    getAllSummary();

    $("#search").click(function(e) {
        e.preventDefault();    
        addSummary(); 
   		addDetails();

});
    
    function addDetails(){
    	var message= $('#message').val();
        var city_name= $('#city').val();
        var latitude= $('#latitude').val();
        var longitude= $('#longitude').val();
        var temperature= $('#temperature').val();

         var username = 'test';
         var sendInfo = {
             user_id: '',
             usr_comment: message,
             city_name: city_name,
                        city_alti: latitude,

             city_longi: longitude,
             city_temp: temperature,

             create_date:moment().format('MMM DD, YYYY'),
             update_date:moment().format('MMM DD, YYYY')
         };
  console.log(moment().format('MMM DD, YYYY'));

       $.ajax({
             type: "POST",
             url: "details",
             dataType: "json",
             data:JSON.stringify(sendInfo),
             success: function (msg) {
                 if (msg) {
                     console.log(" details are added");
               } else {
                     console.log("Cannot add to list !");
                 }
             },
         }); 
  } 
    
    function addSummary(){
        var message= $('#message').val();
         var username = 'test';
         var sendInfo = {
             user_id: '',
             usr_comment: message,
             create_date:moment().format('MMM DD, YYYY'),
             update_date:moment().format('MMM DD, YYYY')
         };
    console.log(moment().format('MMM DD, YYYY'));

       $.ajax({
             type: "POST",
             url: "summary",
             dataType: "json",
             data:JSON.stringify(sendInfo),
             success: function (msg) {
                 if (msg) {
                   $('#status').text('Data is added succesfully')
                     console.log("Somebody" + name + " was added in list !");
               } else {
                     console.log("Cannot add to list !");
                 }
             },
                        complete: function (msg) {
                       // $("#location").empty();
                          getAllSummary();
                        }


         }); 
    } 
    
      
    
    
    function getAllSummary(){
    	 $.ajax({
    	                    url : 'users',
    	                    
    	                    success : function(data) {
    	                                                $('#location').append('');

    	                        //alert(data); //here you will see an alert displaying the callback result coming from your spring controller
    	                        console.log("Request succeeded!");
    	                        console.log(data);
    	                        var trHTML = '<tbody>';
    	                        $.map(data, function (item, index) {
    	                                                  console.log(item);
    	                        console.log(index);

    	                          trHTML += '<tr><td>' + item.usr_id + '</td><td>' + index+ '</td><td>'+
    	                           item.city_name+ '</td><td>'+ 
    	                           item.city_alti+ '</td><td>'+ 
    	                           item.city_longi+ '</td><td>'+
    	                           item.city_temp+ '</td><td>'+
    	                          item.create_date + '</td></tr>';
    	                          });
    	                          trHTML += '</tbody>';    	                          
    	                          $('#location').append(trHTML);
    	                          },
    	                          error : function(xhr, ajaxOptions, thrownError) {
    	                            console.log("Request error!",thrownError);
    	                            if (xhr.status == 404) {
    	                            alert(thrownError);
    	                        }
    	                    }

    	                });
    	}

 

});
</script>
</body>
</html>
