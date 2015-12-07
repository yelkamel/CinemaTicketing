<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.List"%>
<%@ page import="EntityBeans.*" %>
<%@ page import="com.servlet.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" />
<script type="text/javascript" src="menu.js"></script>
<title>Cinema - Mon Panier</title>
</head>
<body>

<%@ include file="header.jsp" %>

<%
	//utilsateur non connecté
	if (session.getAttribute("login") == null)
	{
%>

		<jsp:forward page="index.jsp" />
<%
	}
	//utilisateur connecté
    else
    {
%>
		<div id="menu">
			<div id="bloc_page">
			<ul id="menu2">
              	<li class="liens"><a href="accueil.jsp">Accueil</a></li>
               	<li class="liens"><a href="List_Films">Films</a></li>
               	<li class="liens"><a href="#">Mon Compte</a>
               	<ul>
        			<li class="liens"><a href="Compte?id=profil">Modifier</a></li>
            		<li class="liens"><a href="Compte?id=orders">Mes commandes</a></li>
        		</ul></li>
               	<li class="liens" style="background-color: #CE070A;"><a href="Panier">Mon Panier</a></li>
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
					<p> <a href="accueil.jsp">Accueil</a> › <a href="Panier">Mon panier</a> </p>
				</div>	
				
				<div id="contenu">
				
				<%!
					List<Films> lfilms;
					List<Cinemas> lcinemas;
					List<Seances> lseances;
					List<String> lhoraires;
					List<String> ldates;
				%>

				<%
					// si le panier n'est pas vide
					if (!panier.equals("0 article"))
					{
				%>
						<table width=95% class="table"><tr>
							<th width=15% class="celluleh" style="border-left: 1px solid #E2CCC9;">Film</th>
							<th width=10% class="celluleh">Cinéma</th>
							<th width=10% class="celluleh">Date</th>
							<th width=10% class="celluleh">Horaire</th>
							<th width=10% class="celluleh">Prix/unité</th>
							<th width=10% class="celluleh">Quantité</th>
							<th width=15% class="celluleh" style="border-right: 1px solid #E2CCC9;">Prix total</th></tr>
							<%
								int i = 0;
								BigDecimal total = new BigDecimal("0");
								double prix = 0;
								
								//affichage de la liste des réservations
								for (Reservations r : lreservations)
								{
									//on retire les accents du titre et on cacule le prix total de toutes les réservations
									Inscription inscription = new Inscription();
									String title = inscription.sansAccent(r.getFilm().getTitle());
									BigDecimal price = new BigDecimal(String.valueOf(r.getPrice()));
									total = total.add(price);
										
							%>
							<tr>
								<td class="celluled" style="border-left: 1px solid #E2CCC9;"><span class="forum"><a href="Film?id_film=<%= r.getFilm().getId() %>"><%= r.getFilm().getTitle() %></a></span></td>
								<td class="celluled"><%= r.getCinema().getName() %></td>
								<td class="celluled"><%= r.getDate() %></td>
								<td class="celluled"><%= r.getHoraire() %></td>
								<td class="celluled"><%= r.getSeance().getPrice() %> €</td>
								<td class="celluled"><%= r.getQuantite() %></td>
								<td class="celluled" style="border-right: 1px solid #E2CCC9;"><%= r.getPrice() %> € &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="forum"><a href="Modification?id=<%= r.getId() %>"><img width="18" height="18" alt="" title="Modifier" src="images/modifier.png"></a> &nbsp; <a href="Supprimer?v=panier&id=<%= r.getId() %>"><img width="18" height="18" alt="" title="Supprimer" src="images/supprimer.jpg"></a></span></td>
							</tr>
							<%
								}
								i++;
								prix = total.doubleValue();
							%>
							
							</table></br>
							<div class="forum" style="float: right; margin-right: 45px;">
								<a href="List_Films">Poursuivre mes achats</a>&nbsp;&nbsp;&nbsp;
								<a href="authentification.jsp">Valider ma commande</a>
							</div>
							<div>
								<span style="color: #7C7D7D; margin-left: 10px;">Total du panier: </span><b><%= prix %> €</b>
							</div>
				<%
					}
					//panier vide
					else
					{
				%>
						<p>
							<div style="float: right; font-size: 2.0em; margin-right: 350px; margin-top: 50px;"> Votre panier est vide...</div>
							<div><img src="images/panier_vide.png" /></div>
						</p>
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