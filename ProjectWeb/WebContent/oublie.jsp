<%@page import="EntityBeans.Reservations"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" />
<title>Cinema - Mot de passe oublié</title>
</head>
<body>
	<header>
    <div id="en_tete">
    	<div id="bloc_page">
        	<div id="titre_principal" style="margin-left: 330px; margin-top: 5px;">
            	<img alt="logo" src="images/logo.png" width="150" height="85">
            </div>
        </div>
    </div>			
	</header>
		
	<div id="corps">	
		<div id="bloc_page">
		<section>
			<article>
			<div id="lien">
				<h5> <a href="accueil.jsp">Accueil</a> › <a href="oublie.jsp">Mot de passe oublié</a> </h5>
			</div>
				
				<!-- Forumlaire de regénération de mot de passe -->
				<form method="post" action="Oublie">
					<b class="input">Login</b> <br /><input class="input" placeholder="Login" required autofocus type="text" name="login" size="30" value=""></br> </br>
					<input class="publier" type="submit" name="Valider" value="Valider">
				</form>
			</article>
		</section>
		</div>
	</div>
	</br></br></br></br>

<%@ include file="footer.jsp" %>

</body>
</html>