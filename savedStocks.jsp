<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.ArrayList, com.example.Stock" %> 

<html lang="en">
<head>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies., 0 );
%>
	    <title>RBTR System - Recommendations</title>
  
		<style>
		
			#thead{
				color:#e1f5fe;
				padding:16px;
				border:none;
				background:#0277bd;
			}
			
			#trow{
				color:#263238;
				border:none;
				background:#b3e5fc;
			}
			
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
					background: #01579b;
					font-family: "Arial", sans;
					margin: auto auto;
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
						<form:form method="POST" action="/recommendation">
			  				<div><input type="submit" value="Trade Recommendation" id="sidemenu" /></div>
			  			</form:form>
			  			
			  			<div><input style="background:#0288d1;" type="submit" value="Saved Stocks" id="sidemenu" /></div>
			  			
						<form:form method="POST" action="/logout">
			  				<div><input type="submit" value="Logout" id="sidemenu" /></div>
			  			</form:form>			  			

					</div>
				</td>
				<td style="width:80%; background:#fefefe; vertical-align:top; padding:16px;">
					<div style="font-family: Arial; color:#263238;">
						<p style="font-size:16px; color:#263238; padding:16px; margin-top:16px;">
				  			Saved Stocks are
				  		</p>
					</div>	
					<div style="font-family: Arial">
							<table border="0" style="width:100%; text-align: center;">
							  <tr id="thead">
							    <th style="padding:16px;">Stock</th>
							    <th style="padding:16px;">Price</th>
							    <th style="padding:16px;">Type</th>
							    <th style="padding:16px;">Risk Type</th>
							  </tr>
							  <% 
							  ArrayList<Stock> savedArray = (ArrayList<Stock>)request.getAttribute("savedArray");
							  for(int i=0; i<savedArray.size();i++){
								  String stock = savedArray.get(i).getSymbol();
								  String price = String.valueOf(savedArray.get(i).getPrice());
								  String type;
								  if(savedArray.get(i).getType() == 0)
									  type = "Small";
								  else if(savedArray.get(i).getType() == 1)
									  type = "Medium";
								  else if(savedArray.get(i).getType() == 2)
									  type = "Large";
								  else
									  type = "Unknown";
								  
								  String riskType = savedArray.get(i).getRiskType();							  
							  %>
							 <tr id="trow">
							    <td style="padding:16px;"><%=stock%></td>
							    <td style="padding:16px;"><%=price%></td>
							    <td style="padding:16px;"><%=type%></td>
							    <td style="padding:16px;"><%=riskType%></td>
							  </tr>			
							  <% } %>				
							</table>
					</div>
				</td>
			</tr>
		</table>
	
		
	</body>
</html>

