<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" />
<title>Cinema - Inscription</title>
</head>
<body>
	<header>
    <div id="en_tete">
    	<div id="bloc_page">
        	<div id="titre_principal" style="margin-left: 330px; margin-top: 5px;">
            	<img alt="logo" src="images/logo.png" width="150" height="85">
            </div>
				
			<div id="titre_secondaire">
            </div>
        </div>
    </div>			
	</header>
	
	<div id="corps">	
		<div id="bloc_page">
		<section>
			<article>
			<div id="lien">
					<p> <a href="accueil.jsp">Accueil</a> › <a href="inscription.jsp">Inscription</a> </p>
				</div>
				<p style="margin-left: 330px;"> Remplissez le formulaire suivant pour vous inscrire </p>
				<form method="post" action="Inscription">
					<b class="input">Nom</b> <br /><input class="input" placeholder="Nom" required autofocus type="text" name="nom" size="30" value=""></br> </br>
					<b class="input">Prénom</b> <br /><input class="input" required placeholder="Prénom" type="text" name="prenom" size="30" value=""></br></br>
					<b class="input">Email</b> <br /><input class="input" placeholder="exemple@exemple.com" required type="email" name="email" size="30" value=""></br> </br>
					<b class="input">Adresse</b> <br /><input class="input" placeholder="Adresse Postale" required  type="text" name="adresse" size="30" value=""></br> </br>					 
					<input class="publier" type="submit" name="Valider" value="Valider">
				</form>
			</article>
		</section>
		</div>
	</div>
		
	</br></br></br></br>
		<div id="bloc_page">
		<div id="bas">	
        <footer>
        	<div id="contact">
        		<ul>
					<li><a href="contact.jsp">Nous contacter</a></li>
				</ul>
			</div>
	                    
	        <div id="partage">
	        	<ul>
					<li>Faites découvrir le site sur vos réseaux sociaux</br></li>
					<li><a href="http://twitter.com/share" class="twitter-share-button"data-text="Découvrer votre nouveau site localhost:8080/CinemaWeb/accueil.jsp"></a>
							<script src="http://platform.twitter.com/widgets.js" type="text/javascript"></script>
					</li> </br>
					<li><a name="fb_share" type="button_count"></a>
							<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script></li></br></br>
				</ul>             
	        </div>
	        <div id="droits">
        		<p>Copyright © 2013 apping2015. </br> Designed by AppIng2015Design</p>
        	</div>
    	</footer>
    	</div>
    </div>
</body>
</html>