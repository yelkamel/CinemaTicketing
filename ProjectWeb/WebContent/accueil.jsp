<%@ page import="EntityBeans.Reservations"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" />
<script type="text/javascript" src="menu.js"></script>
<title>Cinema - Accueil </title>
</head>
<body>

<%@ include file="header.jsp" %>

<%
	if (session.getAttribute("login") == null)
	{
%>

			<jsp:forward page="index.jsp" />
<%
	}
    else
    {
%>
			<div id="menu">
			<div id="bloc_page">
			<ul id="menu2">
              	<li class="liens" style="background-color: #CE070A;"><a href="accueil.jsp">Accueil</a></li>
               	<li class="liens"><a href="List_Films">Films</a></li>
               	<li class="liens"><a href="#">Mon Compte</a>
               	<ul>
        			<li class="liens"><a href="Compte?id=profil">Modifier</a></li>
            		<li class="liens"><a href="Compte?id=orders">Mes commandes</a></li>
        		</ul></li>
               	<li class="liens"><a href="Panier">Mon Panier</a></li>
            </ul>
            <form action="Recherche" name="form" method="post">
            	<label style="margin-left: 25px;">Recherche&nbsp;&nbsp;</label><input type="text" name="recherche" size="25" placeholder="Il était temps, Blood Ties ..." required>
            	<input style="cursor: pointer;" size="10" type="submit" name="Recherche" value="OK">
            </form>
            </div></div>
            
		</header>
		
		<div id="corps">	
		<div id="bloc_page">
		<section>
			<article>
				<div id="lien">
					<p> <a href="accueil.jsp">Accueil</a> </p>
				</div>	
				
				<div id="contenu">	
					${message}
				</div>
			</article>
		</section>
		</div></div>

		</br></br></br></br>
		
<%@ include file="footer.jsp" %>
<%
	}
%>
</body>
</html>