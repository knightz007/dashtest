<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- CSS  -->
<link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<link href="https://cdn.datatables.net/1.10.15/css/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css" media="all" />
<!-- Scripts -->
<script src="https://code.jquery.com/jquery-1.12.4.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
    $('#example').DataTable( {
        "ajax": "data/servers.txt" ,
         "columns": [
            { "data": "hostname" },
            { "data": "environment" },
            { "data": "serverFunction" }
        ]
    } );
} );
</script>
</head>
<body>
<table id="example" class="display" width="100%" cellspacing="0">
        <thead>
            <tr>
                <th>Hostname</th>
                <th>Environment</th>
                <th>Server Function</th>
            </tr>
        </thead>       
    </table>
</body>
</html>