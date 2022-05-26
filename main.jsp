<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
	<%
	  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	  response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	  response.setDateHeader("Expires", 0); // Proxies., 0 );
	%>
	    <title>RBTR System - Recommendations</title>
  
		<style>
			
			#sidemenu{
					color : white;
					padding : 16px;
					font-size: 16px;
					width:100%;
					text-align:left;
					border:none;
					background:#01579b;
            }		
		
			#submit{
					color : white;
            		margin-top: 16px;
					border-radius : 4px;
					padding : 8px;
            		text-decoration : none;
					font-size : 16px;
					background: #0288d1;
            }		
            		
            #dropdown{
					padding: 8px; 
            		margin-top: 16px;
					border-radius: 4px; 
					border-width: thin; 
					width: 320px;
					border-color: #dedede;
            		}	
            		
        	#hmenu{
					padding :20px;
					text-align: center;
					color: #fefefe;
					margin: auto auto;
					background: #01579b;
					font-family: "Arial", sans;
					font-size : 15px;
            		}				
					
		</style>
		
  	</head>
  	
	<body style="margin:0;">
	
		<div id="hmenu">
			<h1>Risk Based Trade Recommendation System</h1>
		</div>
		
		<table border="0" style="width:100%; height:100%;">
			<tr> 
				<td style="width:20%; height:100%; background:#01579b; vertical-align: top;">
					<div>
			  			
			  			<div><input style="background:#0288d1;"type="submit" value="Trade Recommendation" id="sidemenu" /></div>
			  		
						<form:form method="POST" action="/SavedStocks">
			  				<div><input type="submit" value="Saved Stocks" id="sidemenu" /></div>
			  			</form:form>
			  			
			  			<form:form method="POST" action="/logout">
			  				<div><input type="submit" value="Logout" id="sidemenu" /></div>
			  			</form:form>
			  			
					</div>
				</td>
				<td style="width:80%; height:100%; background:#fefefe; vertical-align:top; padding:16px;">
					<div>
						<form:form method="POST" action="/recommendedStocks">
							  <select id="dropdown" name="type">
							  	<option value="" disabled selected>Select Risk Appetite</option>
							    <option value="1">Low</option>
							    <option value="2">Medium</option>
							    <option value="3">High</option>
							  </select>
				  			<input id="submit" type="submit" value="Submit" />
						</form:form>
					</div>
				</td>
			</tr>
		</table>
	
		
	</body>
</html>

