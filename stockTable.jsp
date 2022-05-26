<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
					<div style="font-family: Arial; color:#263238;">
						<form:form method="POST" action="/recommendedStocks">
							  <select id="dropdown" name="type">
							  	<option value="1" disabled selected>Select Risk Appetite</option>
							    <option value="1">Low</option>
							    <option value="2">Medium</option>
							    <option value="3">High</option>
							  </select>
				  			<input id="submit" type="submit" value="Submit" />
				  			<p style="font-size:16px; color:#263238; padding:16px; margin-top:16px;">
				  				Selected Risk Appetite is ${type}
				  			</p>
						</form:form>
					</div>
					<div style="font-family: Arial">
						<form:form method="POST" action="/postSave">
							<input type="hidden" name="riskType" value="${type}" />
							<table border="0" style="width:100%; text-align: center;">
							  <tr id="thead">
							    <th style="padding:16px;">ID</th>
							    <th style="padding:16px;">Stock</th>
							    <th style="padding:16px;">Price</th>
							    <th style="padding:16px;">Type</th>
							    <th style="padding:16px;">Select</th>
							  </tr>
							 <tr id="trow">
							    <td style="padding:16px;">1</td>
							    <td style="padding:16px;">${symbols0}<input type="hidden" name="symbolSave" value="${symbols0}"/></td>
							    <td style="padding:16px;">${prices0}<input type="hidden" name="priceSave" value="${prices0}"/></td>
							    <td style="padding:16px;">${types0}<input type="hidden" name="typeSave" value="${types0}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="0"/></td>
							  </tr>
							  
							  <tr id="trow">
							    <td style="padding:16px;">2</td>
							    <td style="padding:16px;">${symbols1}<input type="hidden" name="symbolSave" value="${symbols1}"/></td>
							    <td style="padding:16px;">${prices1}<input type="hidden" name="priceSave" value="${prices1}"/></td>
							    <td style="padding:16px;">${types1}<input type="hidden" name="typeSave" value="${types1}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="1"/></td>
							  </tr>
							  
							 <tr id="trow">
							    <td style="padding:16px;">3</td>
							    <td style="padding:16px;">${symbols2}<input type="hidden" name="symbolSave" value="${symbols2}"/></td>
							    <td style="padding:16px;">${prices2}<input type="hidden" name="priceSave" value="${prices2}"/></td>
							    <td style="padding:16px;">${types2}<input type="hidden" name="typeSave" value="${types2}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="2"/></td>
							  </tr>
							  
							 <tr id="trow">
							    <td style="padding:16px;">4</td>
							    <td style="padding:16px;">${symbols3}<input type="hidden" name="symbolSave" value="${symbols3}"/></td>
							    <td style="padding:16px;">${prices3}<input type="hidden" name="priceSave" value="${prices3}"/></td>
							    <td style="padding:16px;">${types3}<input type="hidden" name="typeSave" value="${types3}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="3"/></td>
							  </tr>
							  
							 <tr id="trow">
							    <td style="padding:16px;">5</td>
							    <td style="padding:16px;">${symbols4}<input type="hidden" name="symbolSave" value="${symbols4}"/></td>
							    <td style="padding:16px;">${prices4}<input type="hidden" name="priceSave" value="${prices4}"/></td>
							    <td style="padding:16px;">${types4}<input type="hidden" name="typeSave" value="${types4}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="4"/></td>
							  </tr>
							  
							 <tr id="trow">
							    <td style="padding:16px;">6</td>
							    <td style="padding:16px;">${symbols5}<input type="hidden" name="symbolSave" value="${symbols5}"/></td>
							    <td style="padding:16px;">${prices5}<input type="hidden" name="priceSave" value="${prices5}"/></td>
							    <td style="padding:16px;">${types5}<input type="hidden" name="typeSave" value="${types5}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="5"/></td>
							  </tr>
							  
							 <tr id="trow">
							    <td style="padding:16px;">7</td>
							    <td style="padding:16px;">${symbols6}<input type="hidden" name="symbolSave" value="${symbols6}"/></td>
							    <td style="padding:16px;">${prices6}<input type="hidden" name="priceSave" value="${prices6}"/></td>
							    <td style="padding:16px;">${types6}<input type="hidden" name="typeSave" value="${types6}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="6"/></td>
							  </tr>
							  
							 <tr id="trow">
							    <td style="padding:16px;">8</td>
							    <td style="padding:16px;">${symbols7}<input type="hidden" name="symbolSave" value="${symbols7}"/></td>
							    <td style="padding:16px;">${prices7}<input type="hidden" name="priceSave" value="${prices7}"/></td>
							    <td style="padding:16px;">${types7}<input type="hidden" name="typeSave" value="${types7}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="7"/></td>
							  </tr>
							  
							 <tr id="trow">
							    <td style="padding:16px;">9</td>
							    <td style="padding:16px;">${symbols8}<input type="hidden" name="symbolSave" value="${symbols8}"/></td>
							    <td style="padding:16px;">${prices8}<input type="hidden" name="priceSave" value="${prices8}"/></td>
							    <td style="padding:16px;">${types8}<input type="hidden" name="typeSave" value="${types8}"/></td>
							    <td style="padding:16px;"><input type="checkbox" name="saveCheck" value="8"/></td>
							  </tr>
							  
							<tr id="trow">
								<td style="padding:16px;">10</td>
								<td style="padding:16px;">${symbols9}<input type="hidden" name="symbolSave" value="${symbols9}"/></td>
								<td style="padding:16px;">${prices9}<input type="hidden" name="priceSave" value="${prices9}"/></td>
								<td style="padding:16px;">${types9}<input type="hidden" name="typeSave" value="${types9}"/></td>
								<td style="padding:16px;"><input type="checkbox" name="saveCheck" value="9"/></td>
							</tr>
							
							</table>
							<br />
							<input id="submit" type="submit" value="Save Stocks">
						</form:form>
					</div>
				</td>
			</tr>
		</table>
	
		
	</body>
</html>

