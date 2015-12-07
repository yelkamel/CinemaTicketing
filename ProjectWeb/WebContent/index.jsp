<%@ page import="EntityBeans.Reservations"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" />
<title>Cinema - Accueil </title>
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
					<p> <a href="accueil.jsp">Accueil</a> › <a href="index.jsp">Connexion</a> </p>
				</div>
				<span class="identification" style="color: red;"> ${message} </span></br>
				<form method="post" action="Accueil">
					<input type="hidden" value="Filtrer par date de sortie" name="sortie">
					<input type="hidden" value="Filtrer par duree" name="duree">
					<input type="hidden" value="Filtrer par genre" name="genre">
					<b class="input">Login</b> <br /><input class="input" placeholder="Login" required autofocus type="text" name="login" size="30" value=""></br> </br>
					<b class="input">Mot de passe</b> <br /><input class="input" required placeholder="Mot de passe" type="password" name="password" size="30" value=""></br></br> 
					<input class="publier" type="submit" name="Connexion" value="Connexion">
				</form>
				
				<div id="index">
					<p class="inscription"><a href="inscription.jsp">Pas encore inscrit?</a>&nbsp&nbsp
					<a href="oublie.jsp"> Mot de passe oublié?</a></p>
				</div>
			</article>
		</section>
		</div>
	</div>
		
	</br></br></br></br>
		
<%@ include file="footer.jsp" %>

</body>
</html>