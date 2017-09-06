<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.anup.webapp.ReleaseArtifactInfo,com.anup.webapp.queryDB"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Release Artifact List</title>
<!-- CSS  -->
<link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="https://cdn.datatables.net/1.10.15/css/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css" media="all" />
<!-- Scripts -->
<script src="https://code.jquery.com/jquery-1.12.4.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.jqueryui.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function() {
	    $('#releaseArtifacts').DataTable({
	    	"bFilter": true,
	    	"Dom":"lrtip"
	    });
	    
	  	 
	   // $("div.toolbar").html('<select id="msds-select"><option>7.11</option><option>7.12</option><option>Group 2</option></select>');
	    
	    var oTable;
	      oTable = $('#releaseArtifacts').DataTable();

	   /*   $('#releaseArtifacts').on( 'draw.dt', function () {
	    	    alert( 'Table redrawn' );
	    	} ); */
	      
	      $("#msds-select").change(function () {
	         //alert($(this).val());
	         oTable.fnFilter( $(this).val() ); 
	         
	      });
	} );
 </script>
</head>
<body>
<div>
<select id="msds-select" >
	<option>7.11</option>
	<option>7.12</option>
	<option>7.13</option>
</select>
</div>

<table id="releaseArtifacts" class="display" width="100%" cellspacing="0">
       <thead>
           <tr>
               <th><u>Release</u></th>
               <th><u>Component</u></th>
               <th><u>Certified Artifact Version</u></th>                    
           </tr>
       </thead>
       <tbody> 
                <% for (ReleaseArtifactInfo r: queryDB.getReleaseArtifactInfoList()){ %>              
                    <tr>
                    <td><%=r.getRelease()%></td>
                     <td><%=r.getComponent()%></td>
                     <td><%=r.getCertifiedArtifactVersion()%></td>
                    </tr>                
				<% } %>
   		</tbody>
</table>
</body>
</html>