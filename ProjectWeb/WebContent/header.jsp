<%@ page import="com.servlet.*" %>
<%@ page import="EntityBeans.*" %>
<%@ page import="java.util.List"%>

<%!
	List<Reservations> lreservations;
	String panier = new String(); 
%>
<%
	lreservations = (List<Reservations>)session.getAttribute("ListReservations");
	if (lreservations != null)
	{
		int n = lreservations.size();
		if (n <= 1)
			panier = String.valueOf(n).concat(" article");
		else
			panier = String.valueOf(n).concat(" articles");
	}
	else
	{
		panier = "0 article";
	}
%>            
	<header>
    <div id="en_tete">
    	<div id="bloc_page">
	    	<div id="article">
	    		<%= panier %>
	    	</div>
    		<div id="panier">
    			<img width="50px" height="50px" alt="" src="images/panier.png">
    		</div>
    		<div id="client">
    			Bienvenue <%= session.getAttribute("prenom") %>&nbsp;&nbsp;</br>
    			<a href="Deconnexion">Se déconnecter</a>&nbsp;&nbsp;
    		</div>
        	<div id="titre_principal" style="margin-top: 5px;">
            	<img alt="logo" src="images/logo2.png" width="150" height="85">
            </div>
        </div>
    </div>