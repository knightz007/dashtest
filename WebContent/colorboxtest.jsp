<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <link rel="stylesheet" href="colorbox/example1/colorbox.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css" type="text/css" /> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="colorbox/jquery.colorbox.js"></script>       
              <script>
                     $(document).ready(function(){
                           //Examples of how to assign the Colorbox event to elements
                           $(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});
                     });
              </script>
    
    
  </head>
  <body>
  
  <input type="button" class="iframe" value="Details" href='http://localhost:8080/ServletTest/ReleaseArtifactView.jsp'>
<!-- <p><a class='iframe' href="http://localhost:8080/ServletTest/ReleaseArtifactView.jsp">Get Details</a></p> -->
<!--  <a href="http://localhost:8080/ServletTest/ReleaseArtifactView.jsp" class="iframe btn btn-secondary">Details</a> -->
  </body>
</html>
