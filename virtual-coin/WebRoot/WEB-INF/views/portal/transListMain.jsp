<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head2.0.ftl">
  <title></title>
</head>
<body>
  <input type="hidden" name="pageNo" id="pageNo" value="1" /> 
  <input type="hidden" name="secId" id="secId" value="" /> 

	<table id="example" class="display nowrap" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>Name</th>
	                <th width="50%">Position</th>
	                <th>Office</th>
	                <th>Extn.</th>
	                <th>Start date</th>
	                <th>Salary</th>
	            </tr>
	        </thead>
	 
	        <tfoot>
	            <tr>
	                <th>Name</th>
	                <th>Position</th>
	                <th>Office</th>
	                <th>Extn.</th>
	                <th>Start date</th>
	                <th>Salary</th>
	            </tr>
	        </tfoot>
	    </table>
   

  <#include "/inc/message.ftl">
  <#include "/inc/menu.ftl">
  <script type="text/javascript" src="<@s.url '/js/jquery/jquery.dataTables.js'/>"></script>
  <script type="text/javascript" src="<@s.url '/js/jquery/dataTables.responsive.min.js'/>"></script>
  <script type="text/javascript" src="<@s.url '/js/2.0/portal/transMainList.js?time=${.now}'/>"></script>
</body>
</html>
