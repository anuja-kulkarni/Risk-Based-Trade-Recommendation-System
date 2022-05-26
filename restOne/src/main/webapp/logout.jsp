<%session.invalidate();%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
  <head>    
	  <%
	  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	  response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	  response.setDateHeader("Expires", 0); // Proxies., 0 ); 0 );
	%>
  	<link rel="stylesheet" type="text/css"  href="style.css">
    <title>RBTR System</title>
   
		<style>
			#first
			{
				border-radius : 4px;
				width:300px;
				margin-top: 80px;
				display: block;
				padding-bottom:28px;
				font-family : Arial;
				text-align:center;
				margin-left: auto; 
				margin-right: auto;
				background-color : #ffffff;
			}
		
			#submit{
					color : white;
            		margin-top: 16px;
            		margin-left: auto;
            		margin-right: auto;
					border-radius : 4px;
					padding : 8px;
            		text-decoration : none;
					background : #0288d1;
					font-size : 16px;
            		}		
            		
            #logouttext{
            		font-family: Arial; 
            		font-size:18px;
            		text-align:center;
            		margin: auto auto;
					padding: 16px;
					color:#212121;
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
				<p id="logouttext">You Have Been Logged Out</p>
				<br />
				<a href="/login" id="submit">Login Again</a>
		</div>
  </body>
</body>
</html>