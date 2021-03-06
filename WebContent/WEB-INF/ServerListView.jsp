<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.anup.webapp.ServerInfo,com.anup.webapp.queryDB" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Server List</title>
<!-- CSS  -->
<link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="https://cdn.datatables.net/1.10.15/css/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css" media="all" />
<!-- Scripts -->
<script src="https://code.jquery.com/jquery-1.12.4.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.jqueryui.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function() {
	    $('#servers').DataTable();
	   } );
 </script>
</head>
<body>
<div id="container">
<h1>Servers</h1>
<div>
<table id="servers" class="display" width="100%" cellspacing="0">
       <thead>
           <tr>
               <th><u>Host name</u></th>
               <th><u>Environment</u></th>
                          
           </tr>
       </thead>
       <tbody> 
                <% for (ServerInfo s: queryDB.getServerList()){ %>              
                    <tr>
                    <td><%=s.getHostname()%></td>
                     <td><%=s.getEnvironment()%></td>
                    
                    </tr>                
				<% } %>
   		</tbody>
</table>
</div>
 </div>
<form action="SaveServerInfo">
<h2>Add a server to DB</h2>
Host name: <input type="text" name="hostName"><br>
Environment: <input type="text" name="environment"><br>
Server Function: <input type="text" name="serverFunction"><br>
<input type="submit" value="Save Server Info">
</form>
</body>
</html>