<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div style="margin: auto;width:30%;padding-top:50px">
<form id="form1" name="form1" method="post">
<div class="form-group">
<input type="text" class="form-control" placeholder= 'Enter Comment' id="message"><br>
<input type="text" class="form-control" placeholder= 'Enter Comment' id="message"><br>

<p align="center"><button type="submit" class="btn btn-primary btn-default" id="search">Done</button></p><br>
<label for="city">Display city name of student:</label>
<input type="email" class="form-control" placeholder= 'City name' id="city_name"><br><br>
</div>
</form>

</div>
</div>
<table class="table" id="location">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Comment</th>
      <th scope="col">Create Date</th>
    </tr>
  </thead>

</table>
</div>

<script>
$(document).ready(function(){
    getAllSummary();

$("#search").click(function() {
    var roll_no= $('#message').val();

});
function getAllSummary(){
 $.ajax({
                    url : 'summary',
                    
                    success : function(data) {
                        //alert(data); //here you will see an alert displaying the callback result coming from your spring controller
                        console.log("Request succeeded!");
                        console.log(data);
var trHTML = '<tbody>';

    $.map(data, function (item, index) {
    trHTML += '<tr><td>' + item.usr_id + '</td><td>' + item.usr_comment+ '</td><td>'+ item.create_date + '</td></tr>';
});
 trHTML += '</tbody>';
 console.log('kiran',trHTML);
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
