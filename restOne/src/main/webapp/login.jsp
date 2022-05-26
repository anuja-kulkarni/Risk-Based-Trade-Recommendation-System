<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
  <head>    
	  <%
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    response.setDateHeader("Expires", 0); // Proxies.
	%>
  	<link rel="stylesheet" type="text/css"  href="style.css">
    <title>RBTR System</title>
   
		<style>
			#first
			{
				border-radius : 4px;
				height : 240px;
				width : 320px;
				margin-top: 80px;
				display: block;
				padding: 16px;
				font-family : Arial;
				margin-left: auto; 
				margin-right: auto;
				background-color : #ffffff;
			}
		
			#submit{
					color : white;
            		margin-top: 16px;
					border-radius : 4px;
					padding : 8px;
            		text-decoration : none;
					background : #0288d1;
					font-size : 16px;
            		}		
            		
            #textbox{
					width: 100%; 
					padding: 8px; 
            		margin-top: 16px;
					border-radius: 4px; 
					border-width: thin; 
					border-color: #dedede;
            		}	
            		
        	#hmenu{
					padding :20px;
					text-align: center;
					color: #fefefe;
					font-family: "Arial", sans;
					margin: auto auto;
					font-size : 15px;
            		}				
					
		</style>
		
  </head>
  
  <body style="margin:0; background:#01579b">
  
  

	<div id="hmenu">
		<h1>Risk Based Trade Recommendation System</h1>
	</div>
  
	  <div id="first">
			<form:form method="POST" action="/dashboard">
				<p style="font-family: Arial; font-size:18px;">User Login</p>
				<input id="textbox" placeholder="Username" path="username" type="text" name="username"/>
				<input id="textbox" placeholder="Password" path="password" type="password" name="password"/>
				<input type="submit" value="Login" name="submit" id="submit"/>
				<p style="font-family: Arial; font-size:12px; color:#f44336;">
					${error}
				</p>
				
			</form:form>
		</div>
  </body>
</body>
</html>