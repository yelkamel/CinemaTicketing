<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="EntityBeans.Reservations"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" />
<script type="text/javascript" src="menu.js"></script>
<title>Cinema - Recherche </title>
</head>
<body>

<%@ include file="header.jsp" %>

<%
	//utilisateur non connecté
	if (session.getAttribute("login") == null)
	{
%>

		<jsp:forward page="index.jsp" />
<%
	}
	//utilisateur connecté
    else if (!session.getAttribute("login").equals("root"))
    {
%>
		<div id="menu">
			<div id="bloc_page">
			<ul id="menu2">
              	<li class="liens"><a href="accueil.jsp">Accueil</a></li>
               	<li class="liens" style="background-color: #CE070A;"><a href="List_Films">Films</a></li>
               	<li class="liens"><a href="#">Mon Compte</a>
               	<ul>
        			<li class="liens"><a href="Compte?id=profil">Modifier</a></li>
            		<li class="liens"><a href="Compte?id=commande">Mes commandes</a></li>
        		</ul></li>
               	<li class="liens"><a href="Panier">Mon Panier</a></li>
            </ul>
            <form action="Recherche" name="form" method="post">
            	<label style="margin-left: 25px;">Recherche&nbsp;&nbsp;</label><input type="text" name="recherche" size="25" placeholder="Il était temps, Blood Ties ..." required>
            	<input style="cursor: pointer;" size="10" type="submit" name="Recherche" value="OK">
            </form>
            </div>
		</div>

		</header>
		
		<div id="corps">	
		<div id="bloc_page">
		<section>
			<article>
				<div id="lien">
					<p> <a href="accueil.jsp">Accueil</a> › <a href="#">Recherche</a> </p>
				</div>	
				
				<div id="contenu">	
				<%
					//recherche invalide
					if (request.getAttribute("films") == null || ((List<Films>)request.getAttribute("films")).size() == 0)
					{
				%>
				
						<p style="text-align: center;">
							<i>Désolé, aucun résultat ne correspond à votre recherche</i>
						</p>
				<%
					}
					//recherche valide
					else
					{
						Inscription inscription = new Inscription();
						List<Films> films = (List<Films>)request.getAttribute("films");
				%>
						<div class="recherche">						
						<p>
							<i>Résultats de recherche pour <b><%= request.getAttribute("recherche") %></b></i></br></br>
						</p>
						<table style="font-family:tahoma; font-size:14px; border:0px; width:100%;" cellpadding="10"><tr>
				<%
						//affichage de la liste de résultats
						for (Films film : films)
						{
							//formatage de la date de sortie au format français
							Calendar cal = Calendar.getInstance();
							cal.setTime(film.getDate());			
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							String date_sortie = df.format(cal.getTime());
							
							//formatage de la durée au format français
							DateFormat hf = new SimpleDateFormat("HH:mm");
							String duree = hf.format(film.getDuration());
						
							StringBuffer sb = new StringBuffer(duree);
							sb.setCharAt(2, 'h');
							duree = sb.toString();
							if (duree.charAt(0) == '0')
							duree = duree.substring(1, 5);
							
							//on retire les accents
							String title = inscription.sansAccent(film.getTitle());
				%>
							<td width=5% style="text-align: center; border:0px; padding-left: 10px;"><a href="Film?id_film=<%= film.getId() %>"><img width=200px; height=300px src="<%=film.getPicture()%>"/></a></td>
							<td width=95%; style="text-align: left; border:0px; padding-left: 10px;">
								<a href="Film?id_film=<%= film.getId() %>"><b> <%= title.toUpperCase() %></b></a></br></br>
								<b> Sortie : </b><%= date_sortie %></br></br>
								<b> Réalisateur : </b><%= film.getDirector() %></br></br>
								<b> Avec : </b><%= film.getActors() %></br></br>
								<b> Genre : </b><%= film.getType() %></br></br>
								<b> Durée : </b><%= duree %></br></br>
								<b>Synopis :</b></br>
								<%= film.getDescription() %></td>
							</td></tr>
				<%
						}
				%>
						</table></div>
				<%
					}
				%>
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