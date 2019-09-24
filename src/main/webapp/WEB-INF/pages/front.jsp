<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Hello world !!!!!</title>
</head>
<body>

<p align="center"> HELLO WORLD !!! </p>

<div class="container">
<div class="row">
<div style="margin: auto;width:30%;padding-top:50px">
<form id="form1" name="form1" method="post">
<div class="form-group">
<input type="text" class="form-control" id="message">

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
        getAllSummary();
   		addSummary(); 

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
                      getAllSummary();
                    }


     }); 
} 

});
</script>
</body>
</html>
