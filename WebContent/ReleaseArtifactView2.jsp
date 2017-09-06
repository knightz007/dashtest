<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.anup.webapp.ReleaseArtifactInfo,com.anup.webapp.queryDB"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Release Artifact List</title>

 <!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->
 
  
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css" type="text/css" />  
<LINK href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<LINK href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="colorbox/example1/colorbox.css" type="text/css" />
  
<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/dataTables.jqueryui.min.js"></script>
 <script src="colorbox/jquery.colorbox.js" type="text/javascript"></script>


 <style>

body {
  font: 90%/1.45em "Helvetica Neue", HelveticaNeue, Verdana, Arial, Helvetica, sans-serif;
  margin: 0;
  padding: 0;
  color: #333;
  background-color: #fff;
}

.red {
  background-color: red !important;
}
</style>

 <script type="text/javascript">
 $(document).ready(function() {
	
	 //$(".btn-details").colorbox({inline:true, width:"50%"});
	 var table= $('#releaseArtifacts').DataTable({
	       	"columnDefs": [
	    	    {
	    	      "data": null,
	    	      //"defaultContent": "<input type=submit class='inline btn btn-info' value=Details />",
	    	      //"defaultContent": " <input type=button class=iframe value=Details>",
	    	      "defaultContent": "<button class=iframe>Click!</button>",
	    	      "targets": -1
	    	    }
	    	  ],
	    	  
	    	  "createdRow": function( row, data, dataIndex ) {
	              if ( data[2] != data[3] ) {        
	          $(row).addClass('red');
	      
	        }
	      }
	 
	    	
	    });
	
	 //
	    
	    $('#releaseArtifacts tbody').on('click', '.iframe', function(e){
	    	var data = table.row( $(this).closest('tr') ).data();
	    	//alert( data[0] +"'s salary is: "+ data[ 2 ] );
	    	//$.colorbox({inline:true, width:"50%",href:"SaveServerInfo.html"});
	    	//$(".iframe").colorbox({ iframe:true, innerWidth:"80%", innerHeight:"80%", href:"http://localhost:8080/ServletTest/ReleaseArtifactView.jsp"});
	    	//$(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});
	    	$(".iframe").colorbox({href:"http://localhost:8080/ServletTest/ReleaseArtifactView.jsp"});
	    	e.preventDefault();
	    	});
   
	      $("#msds-select").change(function () {
	          var x = $(this).val();	    
	    	  $('#hiddenRelease').val(x);	         
	      });		
			
	      
	      
	} ); 
 </script>
</head>
<body>

<form id="artifactForm" action="ReleaseArtifactView.jsp"  method="post">
<div>
 <h2>Release Artifacts dash</h2>
<select id="msds-select" name="test">
	<option value="7.11">7.11</option>
	<option value="7.12">7.12</option>
	<option>7.13</option>
	<option>7.14</option>
</select>
<input type="hidden" name="selectedValue" id="hiddenRelease" value=""/>
<input type="hidden" name="param" />  
<input type="submit" value="Load Release Artifacts">
</div>
<p></p>
</form>
<table id="releaseArtifacts" class="display responsive nowrap" width="100%" cellspacing="0">
       <thead>
           <tr>
               <th><u>Release</u></th>
               <th><u>Component</u></th>
               <th><u>Certified Artifact Version</u></th>  
               <th><u>Current Artifact Version</u></th>   
               <th><u>Get details</u></th>               
           </tr>
       </thead>
       <tbody> 
      		<%        
        		String selectedValue = request.getParameter("selectedValue");
				String releaseSelect = request.getParameter("test"); 
        		if (releaseSelect == "" || releaseSelect == null)
        		{
        			releaseSelect="7.11";
        		}
              for (ReleaseArtifactInfo r: queryDB.getArtifactInfoList(releaseSelect)){ %>              
                    <tr>
                    <td><%=r.getRelease()%></td>
                     <td><%=r.getComponent()%></td>
                     <td><%=r.getCertifiedArtifactVersion()%></td>   
                    <td><%=r.getCurrentArtifactVersion()%></td>                  
                  	<td></td>
                    </tr>                	
       		<% } %>
   		</tbody>
</table>

<script>
window.onload = function(){  
	    var value =<%=request.getParameter("selectedValue")%>; 	    
   		if(value !=null)  
	        {
	    	document.getElementById("msds-select").value = value;
	    	} 
	} 
 </script>
</body>
</html>