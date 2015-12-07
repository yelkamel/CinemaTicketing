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

<%@ include file="header_admin.jsp" %>

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
			<ul>
				<li class="liens" style="background-color: #CE070A;"><a href="admin.jsp">Accueil</a></li>
              	<li class="liens"><a href="Gestion_Films">Films</a></li>
               	<li class="liens"><a href="#">Seances</a></li>
               	<li class="liens"><a href="#">Commandes</a></li>
               	<li class="liens"><a href="#">Utilisateurs</a></li>
            </ul>
            </div></div>
<%
	}
%>
		</header>
		
		<div id="corps">	
		<div id="bloc_page">
		<section>
			<article>
				<div id="lien">
					<p> <a href="admin.jsp">Accueil</a> </p>
				</div>	
				
				<div id="contenu">	
					${message}
				</div>
			</article>
		</section>
		</div></div>
		
		</br></br></br></br>
		
<%@ include file="footer.jsp" %>

</body>
</html>